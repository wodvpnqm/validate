package com.ipx.common.validator.validator;

import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.ipx.common.validator.threadlocal.ThreadMap;
import com.ipx.common.validator.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数Bean验证
 * Created by Administrator on 2017/1/7.
 */
public class BeanValidate extends FieldValidate {


    private static Logger logger = LoggerFactory.getLogger(BeanValidate.class);


    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("bean验证失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends Object> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(com.ipx.common.validator.annotation.BeanValidate.class)) {
            return null;
        }
        com.ipx.common.validator.annotation.BeanValidate nn = (com.ipx.common.validator.annotation.BeanValidate) anno;
        String type = nn.type();
        ValidateResult validateResult = AnnoUtil.parse(obj, type, ThreadMap.getInstance().getLanguage(),ThreadMap.getInstance().getSort());
        if(!validateResult.hasErrors())
            return null;
        Map<String,ValidateResult> result = new HashMap<>();
        if(fieldName != null)
        {
            result.put(fieldName,validateResult);
        }else{
            result.put(Constant.DEFAULT_KEY,validateResult);
        }
        return result;
    }



}

