package com.keybase.aminin.heaps;

import com.keybase.aminin.utils.CmpUtils;

public class MaximumHeap extends MainHeap {
    public MaximumHeap() {
        super(new double[MAX_HEAP_SIZE], CmpUtils::gt);
    }
}
