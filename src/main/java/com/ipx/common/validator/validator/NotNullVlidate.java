package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.NotNull;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

/**
 *非空验证
 *
 */
public class NotNullVlidate extends FieldValidate {

    private static Logger logger = LoggerFactory.getLogger(NotNullVlidate.class);



    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("非空验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, String> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(NotNull.class)) {
            return null;
        }
        NotNull nn = (NotNull) anno;
        return ValidateUtil.validateNull(nn.messageKey(), fieldName, obj);
    }



}
