package com.example.mhmdreza_j.xproject.utils;

public class Utils {

    public static String getStringOfInt(Integer integer) {
        return "" + getInt(integer);
    }

    public static int getInt(Integer integer) {
        if (integer == null) return 0;
        return integer;
    }
}
