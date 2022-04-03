package com.keybase.aminin.heaps;

import com.keybase.aminin.utils.CmpUtils;

public class MaximumHeap extends MainHeap {
    public MaximumHeap() {
        this(MAX_HEAP_SIZE);
    }

    public MaximumHeap(int maxHeapSize) {
        super(new double[maxHeapSize], CmpUtils::gt);
    }
}
