package com.ipx.common.validator.parser;

import java.util.List;
import java.util.Map;

/**
 * 泛型Map
 * K中和V中都有同一个类型
 * Created by Administrator on 2017/1/5.
 */
public class GenericMap<T> {

    /**
     * 类型
     */
    private Class<T>                           clazz;
    /**
     * 字段或者方法->验证单元集合
     */
    private Map<String, List<ValidateUnit<T>>> map;

    public GenericMap(Class<T> clazz, Map<String, List<ValidateUnit<T>>> map) {
        this.clazz = clazz;
        this.map = map;
    }


    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Map<String, List<ValidateUnit<T>>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<ValidateUnit<T>>> map) {
        this.map = map;
    }
}
