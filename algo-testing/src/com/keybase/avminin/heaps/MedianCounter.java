package com.keybase.avminin.heaps;

import com.keybase.avminin.Utils;

public class MedianCounter {
    
    private final MainHeap left;
    private final MainHeap right;
    private int m;

    public MedianCounter(MainHeap left, MainHeap right) {
        this.left = left;
        this.right = right;
        this.m = 0;
    }

    // Function implementing algorithm to find median so far.
    public int getMedian(int e) {
        int sig = Utils.signum(left.GetCount(), right.GetCount());
        switch(sig) {
            case 1:
                // current element fits in left (max) heap
                if (e < m) {
                    // Remove top element from left heap and
                    // insert into right heap
                    right.Insert(left.ExtractTop());

                    // current element fits in left (max) heap
                    left.Insert(e);
                } else {
                    // current element fits in right (min) heap
                    right.Insert(e);
                }
                // Both heaps are balanced
                m = Utils.mean(left.GetTop(), right.GetTop());

                break;
            case 0:
                // current element fits in left (max) heap
                if (e < m) {
                    left.Insert(e);
                    m = left.GetTop();
                } else {
                    // current element fits in right (min) heap
                    right.Insert(e);
                    m = right.GetTop();
                }
                break;
            case -1:
                // current element fits in left (max) heap
                if (e < m) {
                    left.Insert(e);
                } else {
                    // current element fits in right (min) heap

                    // Remove top element from right heap and
                    // insert into left heap
                    left.Insert(right.ExtractTop());
                    right.Insert(e);
                }
                // Both heaps are balanced
                m = Utils.mean(left.GetTop(), right.GetTop());
                break;
        }
        return m;
    }
}
