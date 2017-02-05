package com.ipx.common.validator.annotation;

import com.ipx.common.validator.annotations.Lengths;
import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.LengthValidate;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 数据长度验证。验证字符长度个数。
 *
 * @author wonder
 * @create 2017-01-22 11:33
 **/
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(LengthValidate.class)
@Repeatable(value = Lengths.class )
public @interface Length {
    /**
     * 最大字符个数.默认0表示不验证，最大长度无限制。
     * @return
     */
    int max()  default 0;

    /**
     * 最小字符个数。默认0表示不验证，最小长度无限制。
     * @return
     */
    int min() default 0;


    public String message() default "";

    public String type() default Constant.DEFAULT_TYPE;
}
