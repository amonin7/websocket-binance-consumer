package com.keybase.aminin.heaps;

import com.keybase.aminin.utils.CmpUtils;

public class MinimumHeap extends MainHeap {
    public MinimumHeap() {
       this(MAX_HEAP_SIZE);
    }

    public MinimumHeap(int maxHeapSize) {
        super(new double[maxHeapSize], CmpUtils::lt);
    }
}
