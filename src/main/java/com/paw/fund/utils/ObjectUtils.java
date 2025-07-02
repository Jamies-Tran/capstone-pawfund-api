package com.paw.fund.utils;

public class ObjectUtils {
    public static Boolean isNull(Object obj) {
        return obj == null;
    }

    public static Boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static Boolean isEquals(Object obj1, Object obj2) {
        if(obj1 == null && obj2 == null) {
            return true;
        }

        if(obj1 != null && obj2 != null) {
            return obj1.equals(obj2);
        }

        return false;
    }

}
