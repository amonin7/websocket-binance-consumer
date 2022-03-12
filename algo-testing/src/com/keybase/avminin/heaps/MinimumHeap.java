package com.keybase.avminin.heaps;

import com.keybase.avminin.Utils;

public class MinimumHeap extends MainHeap {
    public MinimumHeap() {
        super(new int[MAX_HEAP_SIZE], Utils::lt);
    }
}
