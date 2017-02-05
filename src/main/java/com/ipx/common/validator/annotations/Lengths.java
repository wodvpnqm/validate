package com.ipx.common.validator.annotations;

import com.ipx.common.validator.annotation.Length;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wodvpn on 2017/2/5.
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Repeated
public @interface Lengths {

    Length[] value();
}
