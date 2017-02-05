package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.SelectIntegerValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 整数集合验证
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(SelectIntegerValidate.class)
public @interface SelectInteger {

	public String message() default "";
	
	public String type() default Constant.DEFAULT_TYPE;
	
	public int[] options() default {};
	
	
}
