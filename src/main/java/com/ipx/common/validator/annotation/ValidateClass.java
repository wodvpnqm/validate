package com.ipx.common.validator.annotation;

import com.ipx.common.validator.validator.NotNullVlidate;
import com.ipx.common.validator.validator.Validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证框架元注解。用于标识验证注解所使用哪个类进行验证。<br />
 * 示例:@ValidateClass(NotNullVlidate.class),标识使用NotNullValidate对非空进行验证。
 */
@Documented
@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface ValidateClass {

	Class<? extends Validate> value() default NotNullVlidate.class;
}
