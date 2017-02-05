package com.ipx.common.validator.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 全局变量,用于一个线程可以访问到到
 * 跨层传递参数到
 * Created by Administrator on 2017/1/7.
 */
public class ThreadMap {


    public static final ThreadMap tm = new ThreadMap();

    /**
     * 语言信息
     */
    private static final String LANGUAGE_KEY = "language";

    /**
     * 国家Key
     */
    private static final String COUNTRY_KEY = "country";

    /**
     * 是否排序
     */
    private static final String SORT_KEY = "sort";

    /**
     * 上下文信息
     */
    private static final String CONTEXT_KEY = "context";

    /**
     * 是否动态切换语言
     */
    private static final String AOP_MAPPING_KEY = "aop_enabled";


    /**
     * 动态类型
     */
    private static final String DYNAMIC_TYPE_KEY = "dynamic_type";

    /**
     * 国际化资源
     */
    private static final String RESOURCE_BUNDLE_KEY = "resouce_bundle";


    /**
     * 第一层环境变量
     */
    private static final String INNER_CONTEXT = "inner_context";

    /**
     * 注解环境
     */
    private static final String ANNO_CONTEXT = "annotion_context";

    /**
     * 线程独立的变量
     */
    private static ThreadLocal<Map<String, Object>> globalLocal = new ThreadLocal<Map<String, Object>>() {
        @Override
        public Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };


    private ThreadMap() {
    }


    public static ThreadMap getInstance() {
        tm.setSort(true);
        return tm;
    }

    /**
     * 设置语言,每次调用都要设置
     * 后面的方法好进行获得语言信息
     * web环境下,切面自动设置
     * 根据session里面的语言来设置
     *
     * @param language
     */
    public void setLanguage(String language) {
        globalLocal.get().put(LANGUAGE_KEY, language);
    }

    /**
     * 获得语言信息
     * 如果没有设置语言,默认为""
     * 那么就直接为message里面的值
     *
     * @return
     */
    public String getLanguage() {
        return (String) globalLocal.get().get(LANGUAGE_KEY);
    }

    /**
     * 设置是否排序
     * 不同排序的消息格式略有差异
     *
     * @param sort
     */
    public void setSort(boolean sort) {
        globalLocal.get().put(SORT_KEY, sort);
    }

    /**
     * 获得排序标志
     *
     * @return
     */
    public boolean getSort() {
        return (boolean) globalLocal.get().get(SORT_KEY);
    }

    /**
     * 设置上下文信息
     * 这个是用来格式化字符串的
     * 最后的国际化消息如果有占位符
     * 还需要进一步处理
     *
     * @param context
     */
    public void setContext(Map<String, String> context) {
        globalLocal.get().put(CONTEXT_KEY, context);
    }

    /**
     * 获得上下文信息
     *
     * @return
     */
    public Map<String, String> getContext() {
        return (Map<String, String>) globalLocal.get().get(CONTEXT_KEY);
    }


    /**
     * 设置动态参数
     *
     * @param type 动态类型
     */
    public void setType(String type) {
        globalLocal.get().put(DYNAMIC_TYPE_KEY, type);
    }

    /**
     * 获得动态参数
     */
    public String getType() {
        return (String) globalLocal.get().get(DYNAMIC_TYPE_KEY);
    }


    /**
     * 国际化资源文件
     *
     * @param rs
     */
    public void setResouceBundle(ResourceBundle rs) {
        globalLocal.get().put(RESOURCE_BUNDLE_KEY, rs);
    }

    /**
     * 获得国际化资源
     *
     * @return
     */
    public ResourceBundle getResouceBundle() {
        return (ResourceBundle) globalLocal.get().get(RESOURCE_BUNDLE_KEY);
    }


    /**
     * 设置第一层环境
     *
     * @param innerContext
     */
    public void setInnerContext(Map<String, String> innerContext) {
        globalLocal.get().put(INNER_CONTEXT, innerContext);
    }

    /**
     * 返回第一层环境
     *
     * @return
     */
    public Map<String, String> getInnerContext() {
        return (Map<String, String>) globalLocal.get().get(INNER_CONTEXT);
    }


    /**
     * 设置注解环境
     *
     * @param annoContext
     */
    public void setAnnoContext(Map<String, String> annoContext) {
        globalLocal.get().put(ANNO_CONTEXT, annoContext);
    }

    /**
     * 返回注解环境
     *
     * @return
     */
    public Map<String, String> getAnnoContext() {
        return (Map<String, String>) globalLocal.get().get(ANNO_CONTEXT);
    }


}
