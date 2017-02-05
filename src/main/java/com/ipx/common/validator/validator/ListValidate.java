package com.ipx.common.validator.validator;

import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.ipx.common.validator.threadlocal.ThreadMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wodvpn on 2017/2/5.
 */
public class ListValidate extends FieldValidate{

    private static Logger logger = LoggerFactory.getLogger(ListValidate.class);

    @Override
    public void handleError(Annotation anno, Member member, Object obj, Exception e) {
        logger.error("集合严重失败,参数:Annotation[anno]={},Member[member]={},T[obj]={},异常:{}", anno, member, obj, e);
    }

    @Override
    public Map<String, ? extends Object> validateInner(Annotation anno, String fieldName, Object obj) {
        Class<?> clazz = anno.annotationType();
        if (!clazz.equals(com.ipx.common.validator.annotation.ListValidate.class)) {
            return null;
        }
        com.ipx.common.validator.annotation.ListValidate nn = (com.ipx.common.validator.annotation.ListValidate) anno;
        String type = nn.type();
        Map<String,Map<Integer,? extends Object>> resultMap = new HashMap<>();
        assert java.util.List.class.isAssignableFrom(obj.getClass()):"集合验证必须是java.util.List类型";
        List<?> lst = (List<?>)obj;
        Map<Integer,ValidateResult> result = new HashMap<Integer,ValidateResult>();
        for(int i=0;i<lst.size();i++)
        {
            Object target = lst.get(i);
            ValidateResult validateResult = AnnoUtil.parse(target, type, ThreadMap.getInstance().getLanguage(),ThreadMap.getInstance().getSort());
            if(validateResult.hasErrors())
            {
                result.put(i,validateResult);
            }
        }
        resultMap.put(fieldName,result);
        return resultMap;
    }
}
