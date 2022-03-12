package com.keybase.aminin.service;

import com.keybase.aminin.model.SymbolMedianData;

public interface SymbolService {
    SymbolMedianData getMedianDataBySymbol(String symbol);
}
