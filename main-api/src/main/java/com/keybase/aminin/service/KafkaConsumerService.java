package com.keybase.aminin.service;

import com.keybase.aminin.model.Trade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "${message.topic.name}",
            groupId = "javaListener",
            containerFactory = "kafkaListenerContainerFactory"
    ) public void listenGroupFoo(Trade trade) {
        System.out.println("Received Message in group 'foo': " + trade.getPrice() + " key: " + trade.getSymbol());
    }

}
