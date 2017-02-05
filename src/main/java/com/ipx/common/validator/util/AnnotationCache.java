package com.ipx.common.validator.util;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/12.
 */
public class AnnotationCache {

    private static final AnnotationCache ac = new AnnotationCache();

    private Map<Annotation, Map<String, String>> annoMap = new HashMap<>();

    public static AnnotationCache getInstance() {
        return ac;
    }

    public Map<String, String> get(Annotation anno) {
        return annoMap.get(anno);
    }

    public void set(Annotation anno, Map<String, String> map) {
        annoMap.put(anno, map);
    }
}
