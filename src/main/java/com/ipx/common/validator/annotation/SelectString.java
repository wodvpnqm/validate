package com.ipx.common.validator.annotation;

import com.ipx.common.validator.annotations.SelectStrings;
import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.validator.SelectStringValidate;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/1/13.
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@ValidateClass(SelectStringValidate.class)
@Repeatable(value = SelectStrings.class )
public @interface SelectString {
    public String message() default "";

    public String type() default Constant.DEFAULT_TYPE;

    public String[] options() default {};

}
