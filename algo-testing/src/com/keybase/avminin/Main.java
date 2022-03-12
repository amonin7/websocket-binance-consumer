package com.keybase.avminin;

import com.keybase.avminin.heaps.MainHeap;
import com.keybase.avminin.heaps.MaximumHeap;
import com.keybase.avminin.heaps.MinimumHeap;

import java.util.Scanner;

import static com.keybase.avminin.heaps.Median.getMedian;

public class Main {

    public static void main(String[] args) {
        MainHeap left = new MaximumHeap();
        MainHeap right = new MinimumHeap();
        readInputAndCalculateMedian(left, right);
    }

    private static void readInputAndCalculateMedian(MainHeap left, MainHeap right) {
        int m = 0;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 100; ++i) {
            m = getMedian(in.nextInt(), m, left, right);
            System.out.println(m);
        }
    }
}
