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
}
