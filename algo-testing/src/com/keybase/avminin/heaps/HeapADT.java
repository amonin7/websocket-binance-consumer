package com.keybase.avminin.heaps;

public interface HeapADT {
    boolean Insert(int e);
    int  GetTop();
    int  ExtractTop();
    int  GetCount();
}
