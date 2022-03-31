package com.keybase.aminin.service;

import com.keybase.aminin.model.SymbolMedianData;

import java.util.List;

public interface SymbolService {
    SymbolMedianData getMedianDataBySymbol(String symbol);
    List<String> getAllSymbols();
    void initializeSymbolsStorage();
}
