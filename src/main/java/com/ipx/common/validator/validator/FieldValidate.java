package com.ipx.common.validator.validator;

import com.ipx.common.validator.threadlocal.ThreadMap;
import com.ipx.common.validator.util.AnnotationCache;
import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * 验证抽象类
 *
 */
public abstract class FieldValidate implements Validate {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 处理错误,一般来说打印错误就行了
     * 如果异常,说明约定失败或者逻辑漏洞
     * @param anno 字段上的注解
     * @param member 这里可以是Field,Method或者null(代表参数上的注解)
     * @param obj 对应的字段值
     * @param e 异常信息
     */
    public  abstract void handleError(Annotation anno, Member member, Object obj,Exception e);

    /**
     * 验证逻辑,这里都是调用ValudateUtil里面的静态方法实现的
     * @param anno 注解信息
     * @param fieldName 字段名称,当order为true当时候要用到
     * @param obj 目标对象
     * @return
     */
    public   abstract  Map<String,? extends Object> validateInner(Annotation anno, String fieldName, Object obj);


    /**
     * 验证通用流程
     * 这里首先获得message
     * 然后确定目标对象
     * 最后返回验证结果
     * @param anno 验证注解
     * @param member 成员字段或者成员参数,还有参数注解(null)
     * @param obj 目标对象
     * @param <T> 目标类型
     * @return 错误消息,如果order为true的时候,代表一次验证,一次失败就返回<_default_,messageKey>
     *     如果不要排序,那么就返回<filed1,msg1><filed2,msg2>
     */
    @Override
    public   <T> Map<String,? extends Object> validate(Annotation anno, Member member, T obj){
        Map<String,? extends Object> map;
        Object value = null;
        String fileName = null;
        if(member != null)
        {
            Field field = (Field) member;
            try {
                field.setAccessible(true);
                value = field.get(obj);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                handleError(anno,member,obj,e);
            }
            fileName = field.getName();
        }else{
            //代表参数验证
            value = obj;
        }

        //注解上下文
        Map<String,String> annoMap = null;
        annoMap = AnnotationCache.getInstance().get(anno);
        if(annoMap == null)
        {
            annoMap = ValidateUtil.getAnnotationMap(anno);
            AnnotationCache.getInstance().set(anno,annoMap);
        }
        ThreadMap.getInstance().setAnnoContext(annoMap);


        //字段上下文,参数要用外部上下文
        Map<String,String> objMap = ThreadMap.getInstance().getInnerContext();
        if(objMap != null)
        {
            if(value == null)
            {
                objMap.put(Constant.THIS_MEMBER_KEY,null);
            }else{
                objMap.put(Constant.THIS_MEMBER_KEY,value.toString());
                if(value instanceof String)
                {
                    objMap.put(Constant.THIS_LENGTH_KEY,""+((String)value).length());
                }
            }
        }
        map = validateInner(anno, fileName, value);
        return map;
    }





}
