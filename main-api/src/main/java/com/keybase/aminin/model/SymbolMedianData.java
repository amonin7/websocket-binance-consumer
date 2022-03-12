package com.keybase.aminin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SymbolMedianData {
    private String symbol;
    private long observationsNumber;
    private double infiniteMedian;
    private double mostRecentPrice;
}
