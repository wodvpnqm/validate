package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.NotEmpty;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NotEmptyValidate extends FieldValidate {

    private static Logger logger = LoggerFactory.getLogger(NotEmptyValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("空串或者空集合串长度失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);


    }

    @Override
    public Map<String, String> validateInner(Annotation anno, String fieldName, Object value) {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(NotEmpty.class)) {
            return null;
        }
        NotEmpty nn = (NotEmpty) anno;
        Map<String, String> nullMap = ValidateUtil.validateNull(nn.message(), fieldName, value);
        if(nullMap != null)
            return nullMap;
        assert value instanceof String||value instanceof Collection:"Empty必须在String或者Collection字段上使用";
        if (value instanceof String) {
            String str = (String) value;
            if (str.length() == 0)
                map.put(fieldName, nn.message());
        } else if (value instanceof Collection) {
            Collection<?> col = (Collection<?>) value;
            if (col.isEmpty())
                map.put(fieldName, nn.message());
        }
        return map;
    }


}
