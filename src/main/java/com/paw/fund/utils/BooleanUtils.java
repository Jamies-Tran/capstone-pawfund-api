package com.paw.fund.utils;

public class BooleanUtils {
    public static boolean isTrue(boolean b) {
        return b ? true : false;
    }

    public static boolean isNull(Boolean b) {
        return b == null;
    }

    public static boolean isNotNull(Boolean b) {
        return b != null;
    }
}
