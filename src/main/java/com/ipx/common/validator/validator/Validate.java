package com.ipx.common.validator.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/10.
 * 验证接口
 *
 */
public interface Validate{

    /**
     * 统一注解验证接口
     *
     * @param anno 注解
     * @param member 成员
     * @param obj 目标对象
     * @param <T> 目标类型
     * @return 错误信息
     */
    public <T> Map<String,? extends Object> validate(Annotation anno, Member member, T obj);
}
