package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.SelectString;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13.
 */
public class SelectStringValidate extends FieldValidate {

    private static final Logger logger = LoggerFactory.getLogger(SelectStringValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("选择字符串验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
        throw new RuntimeException("选择字符串验证失败",e);
    }

    @Override
    public Map<String, String> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(SelectString.class)) {
            return null;
        }
        SelectString nn = (SelectString) anno;
        if (obj == null)
            return null;
        if (obj instanceof String) {
            boolean inIn = false;
            String str = (String) obj;
            String [] options = nn.options();
            for (String option : options) {
                if (option.equals(str)) {
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
