package com.ipx.common.validator.util;

/**
 * 验证的常量
 * Created by Administrator on 2017/1/10.
 */
public interface  Constant {

    /**
     *哪一次验证
     */
    public static final String DEFAULT_TIME = "insert";

    /**
     *默认的验证类型
     */
    public static final String DEFAULT_TYPE = "*";

    /**
     *默认的email正则
     */
    public static final String EMAIL_PATTERN = "";

    /**
     *默认的序号
     */
    public static final int DEFAULT_ORDER = 999;


    /**
     *验证过滤变量分割符号
     */
    public static final String TYPE_DELIMITER = ",";


    /**
     * 最小长度key
     */
    public static final String MIN_LENGHT_KEY = "minLength";


    /**
     * 最大长度key
     */
    public static final String MAX_LENGTH_KEY = "maxLength";


    /**
     * 当前字段key
     */
    public static final String THIS_MEMBER_KEY = "this";


    /**
     * 当前字段长度key
     */
    public static final String THIS_LENGTH_KEY = "thisLength";


    /**
     * 默认的key,不排序的时候只返回一个错误,key为这个
     */
    public static final String DEFAULT_KEY = "_default_";


    /**
     * 默认的邮箱格式
     */
    public static final String DEFAULT_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    /**
     *中国电话
     */
    public static final String CHINA_PHONE = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$";


    /**
     *美国电话
     */
    public static final String US_PHONE = "^\\\\([4-6]{1}[0-9]{2}\\\\) [0-9]{3}\\\\-[0-9]{4}$";


    /**
     * 澳大利亚电话
     */
    public static final String AUSTRALIA_PHONE = "^\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}$";


    /**
     *英国电话号码
     */
    public static final String UK_PHONE = "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$";








}
