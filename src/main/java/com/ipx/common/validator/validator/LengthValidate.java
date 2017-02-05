package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.Length;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * @author wonder
 * @create 2017-01-22 19:43
 **/
public class LengthValidate extends FieldValidate {


    private static Logger logger = LoggerFactory.getLogger(LengthValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("长度验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends  Object> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(Length.class)) {
            return null;
        }
        Length nn = (Length) anno;
        Map<String, ? extends Object> map = ValidateUtil.validateLength(nn.message(),nn.max(),nn.min(), fieldName, obj);
        return map;
    }
}
