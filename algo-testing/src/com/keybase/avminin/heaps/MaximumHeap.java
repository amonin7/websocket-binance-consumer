package com.keybase.avminin.heaps;


import com.keybase.avminin.Utils;

public class MaximumHeap extends MainHeap {
    public MaximumHeap() {
        super(new int[MAX_HEAP_SIZE], Utils::gt);
    }
}
