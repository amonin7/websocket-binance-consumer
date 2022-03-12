package com.keybase.aminin.controller;

import com.keybase.aminin.model.ExchangeInfoResponse;
import com.keybase.aminin.model.SymbolExchangeInfo;
import com.keybase.aminin.model.SymbolMedianData;
import com.keybase.aminin.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    private final SymbolService symbolService;

    @Autowired
    public MainController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @GetMapping("/symbols")
    public ResponseEntity<List<String>> getAllSymbols() {
        RestTemplate template = new RestTemplate();
        ExchangeInfoResponse response = template.getForObject(
                "https://api.binance.com/api/v3/exchangeInfo",
                ExchangeInfoResponse.class
        );
        if (response == null) return ResponseEntity.badRequest().body(null);
        List<String> symbols = response.getSymbols()
                .stream()
                .map(SymbolExchangeInfo::getSymbol)
                .collect(Collectors.toList());
        return ResponseEntity.ok(symbols);
    }

    @GetMapping("/{symbol}")
    public SymbolMedianData getMedianData(@PathVariable String symbol) {
        return symbolService.getMedianDataBySymbol(symbol);
    }
}
