package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.NotEmptyValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(NotEmptyValidate.class)
public @interface NotEmpty {

	public String message() default "";
	
	String types() default Constant.DEFAULT_TYPE;
	
}
