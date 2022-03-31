package com.keybase.aminin.storage;

import java.util.List;

public class HashStorage {
    private final boolean isInitialized;
    private final Node root;

    public HashStorage(List<String> initTickers) {
        root = new Node();
        int tickerIndex = 0;
        isInitialized = true;
        for (String ticker : initTickers) {
            this.addTickerIntoTree(ticker, tickerIndex);
            tickerIndex += 1;
        }
    }

    private void addTickerIntoTree(String ticker, int tickerIndex) {
        char[] chars = ticker.toCharArray();
        Node cur = root;
        for (char aChar : chars) {
            Node curLetter = root.getFieldByIndex(aChar - 'A');
            if (curLetter == null) {
                curLetter = new Node();
            }
            cur = cur.setChildByIndex(aChar - 'A', curLetter);
        }
        cur.setValue(tickerIndex);
    }

    public int getTickerIndex(String ticker) {
        char[] chars = ticker.toCharArray();
        Node cur = root;
        for (char aChar : chars) {
            cur = cur.getFieldByIndex(aChar - 'A');
            if (cur == null) {
                return -1;
            }
        }
        return cur.getValue();
    }

    public boolean isInitialized() {
        return isInitialized;
    }
}
