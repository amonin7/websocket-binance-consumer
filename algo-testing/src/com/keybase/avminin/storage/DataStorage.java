package com.keybase.avminin.storage;

import java.util.HashSet;

public class DataStorage {
    private boolean isInitialized;
    private HashSet<String> tickers;

    public DataStorage() {
        isInitialized = false;
        tickers = new HashSet<>();
    }

    public void initializeStorage() {
        isInitialized = true;

    }
}
