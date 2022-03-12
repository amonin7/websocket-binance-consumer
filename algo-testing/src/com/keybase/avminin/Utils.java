package com.keybase.avminin;

public class Utils {
    public static boolean gt(int a, int b) {
        return a > b;
    }

    public static boolean lt(int a, int b) {
        return a < b;
    }

    public static int mean(int a, int b) {
        return (a + b) / 2;
    }

    public static int signum(int a, int b) {
        if (a == b) return 0;
        return a < b ? -1 : 1;
    }
}
