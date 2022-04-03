package com.keybase.aminin.storage;

import java.util.List;

public class HashStorage {
    private final boolean isInitialized;
    private final Node root;
    private int tickerCounter = 0;

    public HashStorage(List<String> initTickers) {
        root = new Node();
        isInitialized = true;
        for (String ticker : initTickers) {
            this.addTickerIntoTree(ticker, tickerCounter);
            tickerCounter += 1;
        }
    }

    public int getTickerCounter() {
        return tickerCounter;
    }

    private void addTickerIntoTree(String ticker, int tickerIndex) {
        char[] chars = ticker.toCharArray();
        Node cur = root;
        for (char aChar : chars) {
            int index = getIndexByChar(aChar);
            Node curLetter = cur.getFieldByIndex(index);
            if (curLetter == null) {
                curLetter = new Node();
            }
            cur = cur.setChildByIndex(index, curLetter);
        }
        cur.setValue(tickerIndex);
    }

    private int getIndexByChar(char aChar) {
        if (Character.isDigit(aChar)) {
            return 26 + aChar - '0';
        } else if (Character.isLetter(aChar)) {
            return Character.toUpperCase(aChar) - 'A';
        }
        throw new IllegalArgumentException("Illegal character=" + aChar);
    }

    public int getTickerIndex(String ticker) {
        char[] chars = ticker.toCharArray();
        Node cur = root;
        for (char aChar : chars) {
            cur = cur.getFieldByIndex(getIndexByChar(aChar));
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
