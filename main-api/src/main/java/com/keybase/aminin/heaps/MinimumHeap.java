package com.keybase.aminin.heaps;

import com.keybase.aminin.utils.CmpUtils;

public class MinimumHeap extends MainHeap {
    public MinimumHeap() {
        super(new double[MAX_HEAP_SIZE], CmpUtils::lt);
    }
}
