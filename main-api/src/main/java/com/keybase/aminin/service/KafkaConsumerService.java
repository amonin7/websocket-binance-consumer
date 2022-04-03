package com.keybase.aminin.service;

import com.keybase.aminin.model.Trade;
import com.keybase.aminin.storage.TickersHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final TickersHashMap storage;
    private final SymbolService symbolService;

    @Autowired
    public KafkaConsumerService(TickersHashMap storage, SymbolService symbolService) {
        this.storage = storage;
        this.symbolService = symbolService;
    }

    @KafkaListener(
            topics = "${message.topic.name}",
            groupId = "javaListener",
            containerFactory = "kafkaListenerContainerFactory"
    ) public void listenGroupFoo(Trade trade) {
        if (!storage.isInitialized) symbolService.initializeSymbolsStorage();
        storage.addPrice(trade.getSymbol(), trade.getPrice());
    }

}
