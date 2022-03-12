package com.keybase.aminin.service;

import com.keybase.aminin.model.SymbolMedianData;
import org.springframework.stereotype.Service;

@Service
public class SymbolServiceImpl implements SymbolService {

    @Override
    public SymbolMedianData getMedianDataBySymbol(String symbol) {
        return new SymbolMedianData(symbol, 1, 0.0, 72.3);
    }
}
