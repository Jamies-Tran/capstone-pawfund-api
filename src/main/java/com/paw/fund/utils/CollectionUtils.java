package com.paw.fund.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
    public static Boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static Boolean hasElement(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static Boolean contains(Collection<?> collection, Object element) {
        return collection != null && collection.contains(element);
    }

    public static Boolean notContains(Collection<?> collection, Object element) {
        return collection == null || !collection.contains(element);
    }

    public static <T> List<T> getDefault(Collection<T> collection) {
        if(collection == null) {
            return new ArrayList<>();
        }

        return collection.stream().toList();
    }
}
