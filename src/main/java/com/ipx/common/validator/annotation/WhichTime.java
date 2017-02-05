package com.ipx.common.validator.annotation;

import com.ipx.common.validator.util.Constant;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证的类型和顺序
 * 这次可能是插入的验证
 * 下次可能是查询的验证
 *
 * @author wodvpn
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface WhichTime {


    /**
     * 类型
     *
     * @return
     */
    public String type() default Constant.DEFAULT_TYPE;

    /**
     * 顺序
     *
     * @return
     */
    public int order() default 0;
}
