package com.ipx.common.validator.annotation;


import com.ipx.common.validator.util.Constant;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  只能放到参数前面,value表示验证类型
 *  目前一个bean支持多种类型验证
 * Created by Administrator on 2017/1/7.
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(com.ipx.common.validator.validator.BeanValidate.class)
public @interface BeanValidate {

     String type() default Constant.DEFAULT_TYPE;
}
