package com.ipx.common.validator.validator;

import com.ipx.common.validator.annotation.Range;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/12.
 */
public class RangeValidate extends FieldValidate {

    private static Logger logger = LoggerFactory.getLogger(RangeValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("数字范围验证抛出异常,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends  Object> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(Range.class)) {
            return null;
        }
        Range nn = (Range) anno;
        double min = nn.minValue();
        double max = nn.maxValue();
        if(obj instanceof Integer||obj instanceof Double||obj instanceof Float)
        {
            Double d = (Double)obj;
            if(d < min || d > max)
            {
                return ValidateUtil.getFormatMap(nn.message(),fieldName);
            }
        }
        return  null;
    }
}
