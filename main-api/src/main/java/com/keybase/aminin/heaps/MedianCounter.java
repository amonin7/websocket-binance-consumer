package com.keybase.aminin.heaps;

import com.keybase.aminin.utils.CmpUtils;

public class MedianCounter {
    
    private final MainHeap left;
    private final MainHeap right;
    private double m;
    private double lastPrice;
    private long obsNumber;

    public MedianCounter(MainHeap left, MainHeap right) {
        this.left = left;
        this.right = right;
        this.m = 0.0;
        this.lastPrice = 0.0;
        this.obsNumber = 0;
    }

    // Function implementing algorithm to find median so far.
    public void addPrice(double price) {
        this.lastPrice = price;
        this.obsNumber += 1;
        int sig = CmpUtils.signum(left.GetCount(), right.GetCount());
        switch(sig) {
            case 1:
                // current element fits in left (max) heap
                if (price < m) {
                    // Remove top element from left heap and
                    // insert into right heap
                    right.Insert(left.ExtractTop());

                    // current element fits in left (max) heap
                    left.Insert(price);
                } else {
                    // current element fits in right (min) heap
                    right.Insert(price);
                }
                // Both heaps are balanced
                m = CmpUtils.mean(left.GetTop(), right.GetTop());

                break;
            case 0:
                // current element fits in left (max) heap
                if (price < m) {
                    left.Insert(price);
                    m = left.GetTop();
                } else {
                    // current element fits in right (min) heap
                    right.Insert(price);
                    m = right.GetTop();
                }
                break;
            case -1:
                // current element fits in left (max) heap
                if (price < m) {
                    left.Insert(price);
                } else {
                    // current element fits in right (min) heap

                    // Remove top element from right heap and
                    // insert into left heap
                    left.Insert(right.ExtractTop());
                    right.Insert(price);
                }
                // Both heaps are balanced
                m = CmpUtils.mean(left.GetTop(), right.GetTop());
                break;
        }
    }

    // Function implementing algorithm to find median so far.
    public double getMedian() {
        int sig = CmpUtils.signum(left.GetCount(), right.GetCount());
        switch (sig) {
            case 1 -> m = left.getTopElement();
            case 0 -> m = CmpUtils.mean(left.GetTop(), right.GetTop());
            case -1 -> m = right.getTopElement();
        }
        return m;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public long getObsNumber() {
        return obsNumber;
    }
}
