package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.SelectInteger;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

public class SelectIntegerValidate extends FieldValidate {

    private static Logger logger = LoggerFactory.getLogger(SelectIntegerValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("选择值验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, String> validateInner(Annotation anno, String fieldName, Object value) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(SelectInteger.class)) {
            return null;
        }
        SelectInteger nn = (SelectInteger) anno;
        if (value == null)
            return null;
        if (value instanceof Integer) {
            boolean inIn = false;
            Integer integer = (Integer) value;
            int[] options = nn.options();
            for (int option : options) {
                if (integer.intValue() == option) {
                    inIn = true;
                    break;
                }
            }
            if (!inIn) {
                return ValidateUtil.getFormatMap(nn.message(), fieldName);
            }
        }
        return null;
    }


}
