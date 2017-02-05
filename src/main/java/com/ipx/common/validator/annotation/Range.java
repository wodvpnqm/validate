package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.RangeValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 目标到值只能在这个范围之内
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(RangeValidate.class)
public @interface Range{
	public String message() default "";

	public double minValue() default Double.MIN_VALUE;
	
	public double maxValue() default Double.MAX_VALUE;
	
	public String type() default Constant.DEFAULT_TYPE;
}
