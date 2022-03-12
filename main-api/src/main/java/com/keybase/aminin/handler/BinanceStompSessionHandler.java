package com.keybase.aminin.handler;

import com.keybase.aminin.model.Trade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class BinanceStompSessionHandler extends StompSessionHandlerAdapter {
    private final Logger logger = LogManager.getLogger(BinanceStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/ws/btcusdt@trade", this);
        logger.info("Subscribed to /topic/messages");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Trade.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Trade trade = (Trade) payload;
        logger.info("Received : " + trade.getPrice() + " from : " + trade.getSymbol());
    }

}
