package com.ipx.common.validator.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证类构建器
 */
public class ValidateBuilder {


    private static final Logger logger = LoggerFactory.getLogger(ValidateBuilder.class);

    //验证类集合
    private static final Map<Class<? extends Validate>, Validate> map = new HashMap<>();

    private ValidateBuilder() {
    }


    /**
     * 获取验证对应验证类
     *
     * @param clazz 类名
     * @return 验证类
     */
    public static Validate getInstanceOf(Class<? extends Validate> clazz) {
        Validate validate = map.get(clazz);
        if (validate == null) {
            try {
                validate = clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                logger.error("获得验证类异常,参数clazz={},异常{}", clazz, e);
            }
            map.put(clazz, validate);
        }
        return validate;
    }


}
