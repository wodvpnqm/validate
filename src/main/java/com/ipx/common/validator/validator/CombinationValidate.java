package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.Combination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;


public class CombinationValidate extends FieldValidate {

    private static Logger logger = LoggerFactory.getLogger(CombinationValidate.class);


    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("组合验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends  Object> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(Combination.class)) {
            return null;
        }
        if (obj == null)
            return null;
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        return null;
    }


}
