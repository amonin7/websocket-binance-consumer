package main

import (
	"context"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/gorilla/websocket"
	"github.com/segmentio/kafka-go"
	"io/ioutil"
	"log"
	"net/http"
	"strings"
	"time"
)

type Trade struct {
	EventType     string `json:"e"`
	EventTime     int64  `json:"E"`
	TradeTime     int64  `json:"T"`
	Symbol        string `json:"s"`
	TradeId       int64  `json:"t"`
	Price         string `json:"p"`
	Quantity      string `json:"q"`
	IsMarketMaker bool   `json:"m"`
	ExchangeType  string `json:"X"`
}

type TradeMessage struct {
	Stream   string `json:"stream"`
	OneTrade Trade  `json:"data"`
}

type SymbolInfo struct {
	Symbol string `json:"symbol"`
}

type ExchInfo struct {
	Symbols []SymbolInfo `json:"symbols"`
}

var ctx = context.Background()
var infos ExchInfo

const (
	KafkaBroker  = "kafka:29092"
	KafkaTopic   = "trades"
	streamsLimit = 199
)

func initializeTradePairs() []SymbolInfo {
	resp, err := http.Get("https://api.binance.com/api/v3/exchangeInfo")
	if err != nil {
		log.Fatalln(err)
	}

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}

	exchInfo := ExchInfo{}
	err = json.Unmarshal(body, &exchInfo)
	if err != nil {
		log.Fatalln(err)
	}

	infos = exchInfo
	return exchInfo.Symbols
}

func makeCombinedStreamsUrl(symbols []SymbolInfo) []string {
	urlPrefix := "/stream?streams="
	streamPostfix := "@trade"
	oneUrl := ""
	var result []string
	for i, el := range symbols {
		if i%streamsLimit == 0 {
			if oneUrl != "" {
				oneUrl = oneUrl[:len(oneUrl)-1]
				result = append(result, oneUrl)
			}
			oneUrl = urlPrefix + strings.ToLower(el.Symbol) + streamPostfix
		} else {
			oneUrl += strings.ToLower(el.Symbol) + "@trade/"
		}
	}
	if result[len(result)-1] == oneUrl {
		return result
	} else {
		oneUrl = oneUrl[:len(oneUrl)-1]
		result = append(result, oneUrl)
		return result
	}
}

func consumeFromBinanceSocket(socketUrl string, messages chan string, w *kafka.Writer) {
	conn, _, err := websocket.DefaultDialer.Dial("wss://stream.binancefuture.com"+socketUrl, nil)
	if err != nil {
		fmt.Println("The error occurred during connecting to the stream, " + err.Error())
	}
	defer conn.Close()

	for {
		tradeMessage := TradeMessage{}
		err := conn.ReadJSON(&tradeMessage)
		if err != nil {
			fmt.Println(err)
			return
		}
		tmString, err := json.Marshal(tradeMessage.OneTrade)
		fmt.Println(string(tmString))
		if err != nil {
			fmt.Println(err)
			return
		}

		err = w.WriteMessages(ctx, kafka.Message{
			Key:   []byte(tradeMessage.OneTrade.Symbol),
			Value: tmString,
		})
		if err != nil {
			panic("could not write message " + err.Error())
		}
	}
}

func handleGetTradePairs(w http.ResponseWriter, r *http.Request) {
	if infos.Symbols == nil {
		initializeTradePairs()
	}
	rawResponse, err := json.Marshal(infos)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	_, err = w.Write(rawResponse)
	if err != nil {
		fmt.Println(err.Error())
		return
	}
}

func newServer() *http.Server {
	r := mux.NewRouter()

	r.HandleFunc("/tradePairs", handleGetTradePairs).Methods(http.MethodGet)

	return &http.Server{
		Handler:      r,
		Addr:         "0.0.0.0:8081",
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}
}

func main() {
	symbols := initializeTradePairs()

	// wait for components to be initialized
	time.Sleep(20 * time.Second)

	w := kafka.Writer{
		Addr:  kafka.TCP(KafkaBroker),
		Topic: KafkaTopic,
	}
	messages := make(chan string)

	urls := makeCombinedStreamsUrl(symbols)
	fmt.Println(urls)
	for _, el := range urls {
		go consumeFromBinanceSocket(el, messages, &w)
	}

	srv := newServer()
	log.Printf("Start serving on %s", srv.Addr)
	go srv.ListenAndServe()

	for m := range messages {
		fmt.Printf("%s\n", m)
	}
}
