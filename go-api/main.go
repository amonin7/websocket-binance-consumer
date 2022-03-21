package main

import (
	"context"
	"encoding/json"
	"fmt"
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

const (
	KafkaBroker = "kafka:29092"
	KafkaTopic  = "trades"
)

func getCurrentTradePairs() []SymbolInfo {
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

	symbols := exchInfo.Symbols
	for i, el := range symbols {
		symbols[i] = SymbolInfo{strings.ToLower(el.Symbol)}
	}

	return symbols
}

func makeCombinedStreamsUrl(symbols []SymbolInfo) []string {
	urlPrefix := "/stream?streams="
	streamPostfix := "@trade"
	oneUrl := ""
	var result []string
	for i, el := range symbols {
		if i%199 == 0 {
			if oneUrl != "" {
				oneUrl = oneUrl[:len(oneUrl)-1]
				result = append(result, oneUrl)
			}
			oneUrl = urlPrefix + el.Symbol + streamPostfix
		} else {
			oneUrl += el.Symbol + "@trade/"
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

func main() {
	// wait for components to be initialized
	time.Sleep(20 * time.Second)

	w := kafka.Writer{
		Addr:  kafka.TCP(KafkaBroker),
		Topic: KafkaTopic,
	}
	messages := make(chan string)

	urls := makeCombinedStreamsUrl(getCurrentTradePairs())
	for _, el := range urls {
		go consumeFromBinanceSocket(el, messages, &w)
	}
	for m := range messages {
		fmt.Printf("%s\n", m)
	}
}
