package com.keybase.aminin.heaps;


import com.keybase.avminin.Utils;

public class MaximumHeap extends MainHeap {
    public MaximumHeap() {
        super(new double[MAX_HEAP_SIZE], Utils::gt);
    }
}
