package com.keybase.aminin.service;

import com.keybase.aminin.model.ExchangeInfoResponse;
import com.keybase.aminin.model.SymbolExchangeInfo;
import com.keybase.aminin.model.SymbolMedianData;
import com.keybase.aminin.storage.TickersHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymbolServiceImpl implements SymbolService {

    @Value("${go.api.host}")
    private String goApiHost;

    private final TickersHashMap storage;

    @Autowired
    public SymbolServiceImpl(TickersHashMap storage) {
        this.storage = storage;
    }

    @Override
    public SymbolMedianData getMedianDataBySymbol(String symbol) {
        return new SymbolMedianData(symbol, 1, storage.getMedianByTicker(symbol), 77.7);
    }

    @Override
    public List<String> getAllSymbols() {
        if (!storage.isInitialized) initializeSymbolsStorage();
        return storage.getTickers();
    }

    public void initializeSymbolsStorage() {
        RestTemplate template = new RestTemplate();
        ExchangeInfoResponse response = template.getForObject(
                goApiHost,
                ExchangeInfoResponse.class
        );
        if (response == null) throw new RuntimeException("Received empty response from ticker service");
        List<String> tickers = response.getSymbols()
                .stream()
                .map(SymbolExchangeInfo::getSymbol)
                .collect(Collectors.toList());
        storage.initializeHashMap(tickers);
    }
}
