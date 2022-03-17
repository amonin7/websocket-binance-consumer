package com.keybase.aminin;

import com.keybase.aminin.handler.BinanceStompSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@EnableWebSocket
@SpringBootApplication
@EnableScheduling
public class App {
    private static final String URL = "wss://stream.binance.com:9443/";

    @Autowired
    BinanceStompSessionHandler sessionHandler;

    @Autowired
    WebSocketStompClient stompClient;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(fixedDelay=3000)
    public void sendMessageToServer() {
        stompClient.connect(URL, sessionHandler);
    }

}
