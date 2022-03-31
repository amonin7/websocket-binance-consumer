package com.keybase.avminin.heaps;

public interface HeapADT {
    boolean Insert(double e);
    double  GetTop();
    double  ExtractTop();
    int  GetCount();
}
