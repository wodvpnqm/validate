package com.ipx.common.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/1/11.
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Repeated {
}
