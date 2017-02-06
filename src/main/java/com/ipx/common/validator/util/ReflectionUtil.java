/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ipx.common.validator.util;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 */
public abstract class ReflectionUtil {

    /**
     * 递归获得所有的方法,包必须以com.ipx开头
     * @param clazz
     * @return
     */
    public static List<Method> getAllMethod(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();
        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {

                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        final int modifiers = method.getModifiers();
                        // no static fields please
                        return !Modifier.isStatic(modifiers);
                    }
                }
        );
        return methods;
    }

    /**
     * 递归获得所有的字段,报名必须以com.ipx开头
     * @param clazz
     * @return
     */
    public static List<Field> getAllField(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz,
                new ReflectionUtils.FieldCallback() {
                    @Override
                    public void doWith(final Field field) throws IllegalArgumentException,
                            IllegalAccessException {
                        fields.add(field);
                    }
                },
                new ReflectionUtils.FieldFilter() {
                    @Override
                    public boolean matches(final Field field) {
                        final int modifiers = field.getModifiers();
                        // no static fields please
                        return !Modifier.isStatic(modifiers);
                    }
                });
        return fields;
    }


}
