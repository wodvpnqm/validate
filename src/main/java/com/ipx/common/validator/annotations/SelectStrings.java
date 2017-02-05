package com.ipx.common.validator.annotations;

import com.ipx.common.validator.annotation.SelectString;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/1/13.
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface SelectStrings {

    SelectString[] value();
}
