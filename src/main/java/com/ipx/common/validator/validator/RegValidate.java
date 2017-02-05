package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.Reg;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;
import java.util.regex.Pattern;

public class RegValidate extends FieldValidate {
    private static Logger logger = LoggerFactory.getLogger(RegValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("验证正则表达式失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends  Object> validateInner(Annotation anno, String fieldName, Object value) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(Reg.class)) {
            return null;
        }
        Reg nn = (Reg) anno;
        if (value == null)
            return null;
        if (value instanceof String) {
            String str = (String) value;
            String regex = nn.pattern();
            if (!Pattern.matches(regex, str)) {
                Map<String, String> map =  ValidateUtil.getFormatMap(nn.message(),fieldName);
                return map;
            }
        }
        return null;
    }


}
