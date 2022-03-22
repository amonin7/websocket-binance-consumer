package com.keybase.aminin.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeInfoResponse {
    @JsonRawValue
    private List<SymbolExchangeInfo> symbols;
}
