package main

import (
	"context"
	"encoding/json"
	"fmt"
	"github.com/gorilla/websocket"
	"github.com/segmentio/kafka-go"
	"time"
)

type TradeMessage struct {
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

var ctx = context.Background()

const (
	KAFKA_BROKER = "kafka:29092"
	KAFKA_TOPIC  = "trades"
)

func main() {
	// wait for components to be initialized
	time.Sleep(20 * time.Second)

	// initialize the writer with the broker addresses, and the topic
	w := kafka.Writer{
		Addr:  kafka.TCP(KAFKA_BROKER),
		Topic: KAFKA_TOPIC,
	}

	conn, _, err := websocket.DefaultDialer.Dial("wss://stream.binancefuture.com/ws/btcusdt@trade", nil)
	if err != nil {
		fmt.Println("The error occurred during connecting to the stream")
	}

	for {
		tradeMessage := TradeMessage{}
		err := conn.ReadJSON(&tradeMessage)
		if err != nil {
			fmt.Println(err)
			return
		}
		tmString, err := json.Marshal(tradeMessage)
		fmt.Println(string(tmString))
		if err != nil {
			fmt.Println(err)
			return
		}
		err = w.WriteMessages(ctx, kafka.Message{
			Key:   []byte(tradeMessage.Symbol),
			Value: tmString,
		})
		if err != nil {
			panic("could not write message " + err.Error())
		}
	}
}
