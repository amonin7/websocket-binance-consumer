package com.keybase.aminin.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @JsonAlias("e")
    private String eventType;
    @JsonAlias("E")
    private long eventTime;
    @JsonAlias("T")
    private long tradeTime;
    @JsonAlias("s")
    private String symbol;
    @JsonAlias("t")
    private long tradeId;
    @JsonAlias("p")
    private Double price;
    @JsonAlias("q")
    private Double quantity;
    @JsonAlias("m")
    private boolean isMarketMaker;
    @JsonAlias("X")
    private String ExchangeType;
}
