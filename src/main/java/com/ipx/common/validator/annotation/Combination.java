package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.CombinationValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 组合验证。一次性验证多个字段。
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
@ValidateClass(CombinationValidate.class)
public @interface Combination {

	String type() default Constant.DEFAULT_TYPE;
}
