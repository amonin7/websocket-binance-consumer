package com.keybase.aminin;

public class Utils {
    public static boolean gt(double a, double b) {
        return a > b;
    }

    public static boolean lt(double a, double b) {
        return a < b;
    }

    public static double mean(double a, double b) {
        return (a + b) / 2;
    }

    public static int signum(double a, double b) {
        if (a == b) return 0;
        return a < b ? -1 : 1;
    }
}
