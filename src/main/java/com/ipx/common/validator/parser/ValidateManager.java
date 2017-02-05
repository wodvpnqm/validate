package com.ipx.common.validator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 验证结果保存
 * Created by Administrator on 2017/1/5.
 */
public class ValidateManager {
    //验证类列表
    private static List<Class<?>>      keyList = new ArrayList<>();
    //验证结果
    private static List<GenericMap<?>> valList = new ArrayList<>();


    private ValidateManager() {
    }


    public static <T> Map<String, List<ValidateUnit<T>>> getVal(Class<T> clazz) {
        int keyIndex = keyList.indexOf(clazz);
        if (keyIndex == -1) {
            return null;
        }
        GenericMap<T> value = (GenericMap<T>) valList.get(keyIndex);
        return value.getMap();
    }

    public static <T> void setVal(Class<T> clazz, Map<String, List<ValidateUnit<T>>> map) {
        int keyIndex = keyList.indexOf(clazz);
        GenericMap<T> value;
        if (keyIndex == -1) {
            value = new GenericMap(clazz, map);
            keyList.add(clazz);
            valList.add(value);
            return;
        }
        value = (GenericMap<T>) valList.get(keyIndex);
        value.setMap(map);
    }
}
