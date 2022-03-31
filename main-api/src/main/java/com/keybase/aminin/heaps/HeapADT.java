package com.keybase.aminin.heaps;

public interface HeapADT {
    boolean Insert(double e);
    double  GetTop();
    double  ExtractTop();
    int  GetCount();
}
