package com.paw.fund.utils;

public class StringUtils {
    public static String empty() {
        return "";
    }

    public static Boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
