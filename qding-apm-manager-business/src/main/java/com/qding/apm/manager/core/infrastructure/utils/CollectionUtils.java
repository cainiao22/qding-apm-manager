package com.qding.apm.manager.core.infrastructure.utils;

import java.util.List;

public class CollectionUtils {
    public static <T> int size(List<T> list) {
        return list != null ? list.size() : 0;
    }
}
