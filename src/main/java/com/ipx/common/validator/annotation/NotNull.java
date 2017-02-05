package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.NotNullVlidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 非空验证
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(NotNullVlidate.class)
public @interface NotNull {

	public String messageKey() default "";

	public String type() default Constant.DEFAULT_TYPE;

}
