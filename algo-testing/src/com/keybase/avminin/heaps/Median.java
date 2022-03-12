package com.keybase.avminin.heaps;

import com.keybase.avminin.Utils;

public class Median {

    // Function implementing algorithm to find median so far.
    public static int getMedian(int e, int m, MainHeap l, MainHeap r) {
        // Are heaps balanced? If yes, sig will be 0
        int sig = Utils.signum(l.GetCount(), r.GetCount());
        switch(sig) {
            case 1: // There are more elements in left (max) heap
                // current element fits in left (max) heap
                if(e < m) {
                    // Remove top element from left heap and
                    // insert into right heap
                    r.Insert(l.ExtractTop());

                    // current element fits in left (max) heap
                    l.Insert(e);
                } else {
                    // current element fits in right (min) heap
                    r.Insert(e);
                }
                // Both heaps are balanced
                m = Utils.mean(l.GetTop(), r.GetTop());

                break;
            case 0: // The left and right heaps contain same number of elements
                // current element fits in left (max) heap
                if( e < m ) {
                    l.Insert(e);
                    m = l.GetTop();
                } else {
                    // current element fits in right (min) heap
                    r.Insert(e);
                    m = r.GetTop();
                }
                break;
            case -1: // There are more elements in right (min) heap
                // current element fits in left (max) heap
                if( e < m ) {
                    l.Insert(e);
                } else {
                    // Remove top element from right heap and
                    // insert into left heap
                    l.Insert(r.ExtractTop());

                    // current element fits in right (min) heap
                    r.Insert(e);
                }
                // Both heaps are balanced
                m = Utils.mean(l.GetTop(), r.GetTop());
                break;
        }
        return m;
    }
}
