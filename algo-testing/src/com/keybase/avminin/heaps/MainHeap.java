package com.keybase.avminin.heaps;

import java.util.function.BiFunction;

public class MainHeap implements HeapADT {
    protected static final int MAX_HEAP_SIZE = Integer.MAX_VALUE / 32;

    protected double[] heapArray;
    protected final BiFunction<Double, Double, Boolean> comparator;
    protected int heapSize;

    public MainHeap(double[] heapArray, BiFunction<Double, Double, Boolean> comparator) {
        this.heapArray = heapArray;
        this.comparator = comparator;
        this.heapSize = -1;
    }

    @Override
    public boolean Insert(double e) {
        return inertsElement(e);
    }

    @Override
    public double GetTop() {
        return getTopElement();
    }

    @Override
    public double ExtractTop() {
        return delTopElement();
    }

    @Override
    public int GetCount() {
        return getElementsAmount();
    }

    protected int getLeftChild(int parent) {
        return 2 * parent + 1;
    }

    protected int getRightChild(int parent) {
        return 2 * (parent + 1);
    }

    protected int getParent(int child) {
        if (child <= 0) return -1;
        return (child - 1) / 2;
    }

    // Returns top element of heap data structure
    protected double getTopElement() {
        return heapSize >= 0 ? this.heapArray[0] : -1;
    }

    // Returns number of elements in heap
    protected int getElementsAmount() {
        return heapSize + 1;
    }

    // Heapification
    protected void heapify(int i) {
        int p = getParent(i);
        if (p >= 0 && comparator.apply(heapArray[i], heapArray[p])) {
            swap(i, p);
            heapify(p);
        }
    }

    private void swap(int i, int j) {
        double tmp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = tmp;
    }

    // Deletes root of heap
    protected double delTopElement() {
        double del = -1;
        if (heapSize >= 0) {
            del = heapArray[0];
            swap(0, heapSize);
            heapSize--;
            heapify(getParent(heapSize + 1));
        }
        return del;
    }

    // insert key into Heap
    protected boolean inertsElement(double key) {
        boolean insResult = false;
        if(heapSize < MAX_HEAP_SIZE) {
            insResult = true;
            heapSize++;
            heapArray[heapSize] = key;
            heapify(heapSize);
        }
        return insResult;
    }
}
