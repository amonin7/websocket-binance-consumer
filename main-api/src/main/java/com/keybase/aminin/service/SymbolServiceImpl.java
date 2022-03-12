package com.keybase.aminin.service;

import com.keybase.aminin.model.ExchangeInfoResponse;
import com.keybase.aminin.model.SymbolExchangeInfo;
import com.keybase.aminin.model.SymbolMedianData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymbolServiceImpl implements SymbolService {

    @Override
    public SymbolMedianData getMedianDataBySymbol(String symbol) {
        return new SymbolMedianData(symbol, 1, 0.0, 72.3);
    }

    @Override
    public List<String> getAllSymbols() {
        RestTemplate template = new RestTemplate();
        ExchangeInfoResponse response = template.getForObject(
                "https://api.binance.com/api/v3/exchangeInfo",
                ExchangeInfoResponse.class
        );
        if (response == null) return null;
        return response.getSymbols()
                .stream()
                .map(SymbolExchangeInfo::getSymbol)
                .collect(Collectors.toList());
    }
}
