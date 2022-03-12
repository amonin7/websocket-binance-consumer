package com.keybase.aminin.controller;

import com.keybase.aminin.model.SymbolMedianData;
import com.keybase.aminin.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final SymbolService symbolService;

    @Autowired
    public MainController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @GetMapping("/symbols")
    public ResponseEntity<List<String>> getAllSymbols() {
        List<String> symbols = symbolService.getAllSymbols();
        return symbols != null
                ? ResponseEntity.ok(symbols)
                : ResponseEntity.internalServerError().body(null);
    }

    @GetMapping("/{symbol}")
    public SymbolMedianData getMedianData(@PathVariable String symbol) {
        return symbolService.getMedianDataBySymbol(symbol);
    }
}
