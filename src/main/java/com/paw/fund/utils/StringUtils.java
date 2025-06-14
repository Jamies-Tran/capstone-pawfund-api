package com.paw.fund.utils;

public class StringUtils {
    public static String empty() {
        return "";
    }

    public Boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public Boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
