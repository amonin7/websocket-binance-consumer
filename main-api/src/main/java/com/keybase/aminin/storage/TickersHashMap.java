package com.keybase.aminin.storage;

import com.keybase.aminin.heaps.MaximumHeap;
import com.keybase.aminin.heaps.MedianCounter;
import com.keybase.aminin.heaps.MinimumHeap;
import com.keybase.aminin.model.SymbolMedianData;

import java.util.List;

public class TickersHashMap {
    private HashStorage hashStorage;
    private MedianCounter[] medians;
    private List<String> tickers;
    public boolean isInitialized;

    public TickersHashMap() {
        this.isInitialized = false;
    }

    public void initializeHashMap(List<String> initTickers) {
        this.hashStorage = new HashStorage(initTickers);
        this.medians = new MedianCounter[initTickers.size()];
        populateMedians();
        this.tickers = initTickers;
        this.isInitialized = true;
    }

    public void addPrice(String ticker, double price) {
        int index = hashStorage.getTickerIndex(ticker);
        if (index == -1) {
            throw new IllegalArgumentException("There is no such ticker{" + ticker + "}.");
        }
        medians[index].addPrice(price);
    }

    public SymbolMedianData getSymbolMedianData(String ticker) {
        int index = hashStorage.getTickerIndex(ticker);
        if (index == -1) {
            throw new IllegalArgumentException("There is no such ticker{" + ticker + "}.");
        }
        MedianCounter medianCounter = medians[index];
        return new SymbolMedianData(
                ticker,
                medianCounter.getObsNumber(),
                medianCounter.getMedian(),
                medianCounter.getLastPrice()
        );
    }

    public List<String> getTickers() {
        return tickers;
    }

    private void populateMedians() {
        for (int i = 0; i < medians.length; ++i) {
            medians[i] = new MedianCounter(new MaximumHeap(), new MinimumHeap());
        }
    }
}
