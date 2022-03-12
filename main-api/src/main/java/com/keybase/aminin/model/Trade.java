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
    @JsonAlias("s")
    private String symbol;
    @JsonAlias("t")
    private long tradeId;
    @JsonAlias("p")
    private String price;
    @JsonAlias("q")
    private String quantity;
    @JsonAlias("b")
    private long buyerId;
    @JsonAlias("a")
    private long sellerId;
    @JsonAlias("T")
    private long tradeTime;
    @JsonAlias("m")
    private boolean isMarketMaker;
    @JsonAlias("M")
    private boolean ignoredProperty;
}
