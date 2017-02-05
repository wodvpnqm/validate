package com.ipx.common.validator.util;

import com.ipx.common.validator.result.MessageManager;
import com.ipx.common.validator.threadlocal.ThreadMap;
import org.apache.commons.collections4.MapUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/6.
 */
public class ValidateUtil {

    private ValidateUtil() {
    }

    /**
     * 批量替换占位符号
     *
     * @param message 国际化之后的消息
     * @param context 上下文信息
     * @return
     */
    public static String replaceMessage(String message, Map<String, String> context) {
        if (context == null)
            return message;

        Pattern pattern = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(message);
        StringBuffer sb = new StringBuffer();
        while(matcher.find())
        {
            if(context.containsKey(matcher.group(1)))
            {
                matcher.appendReplacement(sb, context.get(matcher.group(1)));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 字符数组
     * 例子:
     * 用户名的值不能为空,您输入的值为{userName}
     * 这里userName首先去Bean里面找,如果不存在
     * 再到context中查找
     *
     * @param message
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> getFormatMap(String message, String fieldName) {
        Map<String, String> map = new HashMap<>();
        //获得当前上下文信息
        Map<String, String> context = ThreadMap.getInstance().getContext();
        Map<String, String> innerContext = ThreadMap.getInstance().getInnerContext();
        Map<String, String> annoContext = ThreadMap.getInstance().getAnnoContext();
        if (innerContext != null) {
            innerContext.putAll(annoContext);
        } else {
            innerContext = annoContext;
        }
        if (context != null) {
            context.putAll(innerContext);
        } else {
            context = innerContext;
        }
        message = getTarget(message);
        if (message != null && !MapUtils.isEmpty(context)) {
            message = replaceMessage(message, context);
        }
        map.put(getFieldName(fieldName), message);
        return map;
    }


    public static <T> Map<String, String> validateNull(String message, String fieldName, T obj) {

        if (obj == null) {
            return getFormatMap(message, fieldName);
        }
        return null;
    }

    public static <T> Map<String, String> validateLength(String message, int max,int min,String fieldName, T obj) {

        if (obj == null) {
            return null;
        }
        assert obj.getClass().equals(String.class):"长度验证只支持字符串类型";
        String target = (String)obj;
        int length = target.length();
        if(length < min || length > max)
        {
            return getFormatMap(message,fieldName);
        }
        return null;
    }

    public static Map<String, String> mergeMap(Map<String, String> map1, Map<String, String> map2) {
        if (map1 == null)
            return map2;
        if (map2 == null)
            return map1;
        map1.putAll(map2);
        return map1;
    }

    public static Map<String, String> mergeMapValue(Map<String, String> map1, Map<String, String> map2) {
        if (map1 == null)
            return map2;
        if (map2 == null)
            return map1;
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (map2.containsKey(key)) {
                map2.put(key, map2.containsKey(key) + "," + value);
            } else {
                map2.put(key, value);
            }
        }
        return map2;
    }

    public static <T> Map<String, String> validateEmpty(String message, String fieldName, T obj) {
        Map<String, String> map = null;
        if (obj == null)
            return null;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                map = getFormatMap(message, fieldName);
            }
        } else if (obj instanceof Collection) {
            Collection<?> col = (Collection<?>) obj;
            if (col.isEmpty()) {
                map = map = getFormatMap(message, fieldName);
            }
        }
        return map;
    }


    public static <T> Map<String, String> validateMaxLength(String message, int maxLength, String fieldName, T obj) {
        Map<String, String> map = null;
        if (obj == null)
            return null;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() > maxLength) {
                map = getFormatMap(message, fieldName);
            }
        }
        return map;
    }


    public static String getFieldName(String filedName) {
        boolean sort = ThreadMap.getInstance().getSort();
        if (filedName == null)
            return Constant.DEFAULT_KEY;
        return filedName;
    }

    public static <T> Map<String, String> validateMinLength(String message, int minLength, String fieldName, T obj) {
        Map<String, String> map = null;
        if (obj == null)
            return null;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() < minLength) {
                map = map = getFormatMap(message, fieldName);
            }
        }
        return map;
    }


    public static String getTarget(String message) {
        String language = ThreadMap.getInstance().getLanguage();
        if (language == null || "".equals(language))
            return message;
        return MessageManager.getInstance().getMessage(message);
    }


    /**
     * 获得该注解的<method,type>映射
     *
     * @param anno 注解
     * @return
     */
    public static Map<String, String> getAnnotationMap(Annotation anno) {
        Map<String, String> annoMap = new HashMap<>();
        Class<? extends Annotation> clazz = anno.annotationType();
        Method[] methods = clazz.getDeclaredMethods();
        String name = null;
        Object value = null;
        for (Method method : methods) {
            name = method.getName();
            try {
                value = method.invoke(anno, new Object[]{});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("获取注解key,type", e);
            }
            annoMap.put(name, value == null ? null : value.toString());
        }
        return annoMap;
    }


}
