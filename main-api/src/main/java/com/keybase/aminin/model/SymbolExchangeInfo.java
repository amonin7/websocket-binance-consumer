package com.keybase.aminin.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SymbolExchangeInfo {
    @JsonRawValue
    private String symbol;
    @JsonRawValue
    private String status;
    @JsonRawValue
    private String baseAsset;
    @JsonRawValue
    private String baseAssetPrecision;
    @JsonRawValue
    private String quoteAsset;
    @JsonRawValue
    private String quotePrecision;
    @JsonRawValue
    private String quoteAssetPrecision;
}
