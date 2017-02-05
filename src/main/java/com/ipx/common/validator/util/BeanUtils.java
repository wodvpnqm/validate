package com.ipx.common.validator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/10.
 */
public class BeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);


    public static Map<String, String> introspect(Object obj) {
        Map<String, String> result = new HashMap<String, String>();
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(obj.getClass());
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null) {
                    Object value = null;
                    value = reader.invoke(obj);
                    if (value != null) {
                        result.put(pd.getName(), value.toString());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Bean转换为Map错误", e);
            return null;
        }
        return result;
    }


    public static Map<String, String> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(value != null)
                        map.put(key, value.toString());
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }
}
