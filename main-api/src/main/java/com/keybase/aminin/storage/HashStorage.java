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
            int index = getIndexByChar(aChar);
            Node curLetter = root.getFieldByIndex(index);
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
        return 73;
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
