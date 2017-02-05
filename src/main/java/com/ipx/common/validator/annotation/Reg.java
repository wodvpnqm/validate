package com.ipx.common.validator.annotation;

import com.ipx.common.validator.annotations.Regs;
import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.RegValidate;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 正则表达式验证
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(RegValidate.class)
@Repeatable(value = Regs.class )
public @interface Reg {

	String pattern() default "";
	
	String message() default "";
	
	public String type() default Constant.DEFAULT_TYPE;
}
