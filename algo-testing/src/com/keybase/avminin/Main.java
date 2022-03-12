package com.keybase.avminin;

import com.keybase.avminin.heaps.MaximumHeap;
import com.keybase.avminin.heaps.MedianCounter;
import com.keybase.avminin.heaps.MinimumHeap;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        readInputAndCalculateMedian(new MedianCounter(new MaximumHeap(), new MinimumHeap()));
    }

    private static void readInputAndCalculateMedian(MedianCounter medianCounter) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 100; ++i) {
            System.out.println(medianCounter.getMedian(in.nextInt()));
        }
    }
}
