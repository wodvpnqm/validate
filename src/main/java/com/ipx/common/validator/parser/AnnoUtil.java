package com.ipx.common.validator.parser;

import com.ipx.common.validator.annotation.ValidateClass;
import com.ipx.common.validator.annotation.WhichTime;
import com.ipx.common.validator.annotations.Repeated;
import com.ipx.common.validator.result.ValidateResult;
import com.ipx.common.validator.threadlocal.ThreadMap;
import com.ipx.common.validator.util.BeanUtils;
import com.ipx.common.validator.util.Constant;
import com.ipx.common.validator.util.ReflectionUtil;
import com.ipx.common.validator.validator.Validate;
import com.ipx.common.validator.validator.ValidateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.*;

public class AnnoUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AnnoUtil.class);


    /**
     *
     */
    private AnnoUtil() {
    }


    /**
     * 根据字段,参数或者方法上的注解获得实际要验证的数组
     *
     * @param annos
     * @return
     * @throws Exception
     */
    public static List<Annotation> getMemberAnnotationList(Annotation[] annos) {
        List<Annotation> lst = new ArrayList<>();
        for (int i = 0; i < annos.length; i++) {
            Class<? extends Annotation> ano = annos[i].annotationType();
            boolean isRepeated = ano.isAnnotationPresent(Repeated.class);
            if (isRepeated) {
                Method valueMethod = null;
                try {
                    valueMethod = ano.getDeclaredMethod("value", new Class<?>[]{});
                    Annotation[] annoArray = (Annotation[]) valueMethod.invoke(annos[i], new Object[]{});
                    for (Annotation item : annoArray) {
                        lst.add(item);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("获取实际验证注解异常", e);
                }
                //Annotation [] annoArray = (Annotation []) valueMethod.invoke(anno, new Object[]{});
            } else {
                if (ano.isAnnotationPresent(ValidateClass.class))
                    lst.add(annos[i]);
            }
        }
        return lst;

    }


    /**
     * 这个注解这次是否执行验证
     *
     * @param anno 对应字段或者方法或者方法参数上的注解
     * @param time type参数
     * @return 验证
     */
    public static boolean isThisTimeValidate(Annotation anno, String time) {
        Class<? extends Annotation> ano = anno.annotationType();
        Method myMethod = null;
        try {
            //这里支持[s1,s2,s3...sn],n个值过滤
            //每个si都可以是*,!str,str
            //可以整个type就是*表示匹配所有的
            //![*,s1,s2]
            //利用反射获得注解的值type
            myMethod = ano.getDeclaredMethod("type", new Class<?>[]{});
            String type = (String) myMethod.invoke(anno, new Object[]{});
            if ("*".equals(type) || type.equals(time)) {
                return true;
            } else {
                String[] timeList = time.split(",");
                String[] typeList = type.split(",");
                if (timeList.length != typeList.length)
                    return false;
                boolean add = false;
                int i = 0;
                //每一个有三种形式:*,str,!str,![str1:str2],[str1:str2]
                for (i = 0; i < timeList.length; i++) {
                    //不包含的情况
                    if (typeList[i].startsWith("!")) {
                        //不包含集合,就是当前的这一过滤遍历,必须不在当前集合才能进行验证
                        //比如type="*,![zh:en]",表示不是中文和英文的其他所有语言的所有操作都要进行验证
                        if (typeList[i].charAt(1) == '[') {
                            String arrayStr = typeList[i].substring(2, typeList.length - 1);
                            List<String> tempList = Arrays.asList(arrayStr.split(":"));
                            if (tempList.contains(timeList[i])) {
                                return false;
                            }
                            continue;
                            //不包含单个的情况,只要不是这个变量的这个类型都可以
                            //比如type="*,!zh",那么不是中文的所有的操作都对这个字段的这个注解进行验证
                        } else {
                            if (typeList[i].substring(1).equals(timeList[i])) {
                                return false;
                            }
                            continue;
                        }
                        //在集合中
                    } else if (typeList[i].startsWith("[")) {
                        String arrayStr = typeList[i].substring(1, typeList.length - 1);
                        List<String> tempList = Arrays.asList(arrayStr.split(":"));
                        if (!tempList.contains(timeList[i])) {
                            return false;
                        }
                        continue;
                        //相等进入下一个字段比较
                    } else if ("*".equals(typeList[i]) || timeList[i].equals(typeList[i])) {
                        continue;
                    } else {
                        return false;
                    }
                }

            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error("验证异常", e);
            throw new RuntimeException("解析验证过滤抛出异常", e);
        }
        return true;

    }

    /**
     * 获得方法对应的注解
     *
     * @param target 目标类
     * @param time   哪一次
     * @return
     */
    private static <T> List<ValidateUnit<T>> getValidate(T target, final String time) {
        List<ValidateUnit<T>> thisList = new ArrayList<>();
        ValidateUnit<T> vu;
        List<Annotation> annoList;
        List<Method> methods = ReflectionUtil.getAllMethod(target.getClass());
        List<Field> fields = ReflectionUtil.getAllField(target.getClass());
        List<Member> members = new ArrayList<>();
        members.addAll(methods);
        members.addAll(fields);
        boolean isField;
        Field field = null;
        Method method = null;
        List<Annotation> memberAnnoList = null;
        //对每个方法都检查是否有对应的注解
        for (Member member : members) {
            Annotation[] annos;
            annoList = new ArrayList<>();
            if (member instanceof Field) {
                isField = true;
                field = (Field) member;
                annos = field.getAnnotations();
            } else {
                method = (Method) member;
                isField = false;
                annos = method.getAnnotations();
            }
            try {
                memberAnnoList = getMemberAnnotationList(annos);
            } catch (Exception e) {
                throw new RuntimeException("获取成员注解异常", e);
            }

            //对每个注解,看type是否和time相等,或者type=*
            for (Annotation anno : memberAnnoList) {
                boolean isAdd = isThisTimeValidate(anno, time);
                if (isAdd) {
                    annoList.add(anno);
                }
            }
            //如果这个字段上没有注解在这次要进行验证,那么就继续下一个字段
            if (annoList.isEmpty()) {
                continue;
            }
            int order = Constant.DEFAULT_ORDER;
            boolean sort = ThreadMap.getInstance().getSort();
            if (sort) {
                //获得方法上的WhichTime注解,该注解可以有多个,一个对应了 验证类别和该字段在这次验证中的验证顺序
                //顺序越大越往后
                WhichTime[] times;
                if (isField) {
                    times = field.getDeclaredAnnotationsByType(WhichTime.class);
                } else {
                    times = method.getDeclaredAnnotationsByType(WhichTime.class);
                }
                for (WhichTime wt : times) {
                    String type = wt.type();
                    //*表示该字段的所有注解的是这个顺序
                    if ("*".equals(wt.type()) || wt.type().equals(time)) {
                        order = wt.order();
                        break;
                    }
                }
            }
            vu = new ValidateUnit(member, order, annoList, target);
            thisList.add(vu);
        }
        return thisList;
    }


    public static <T> ValidateResult parse(T obj) {
        return parse(obj, Constant.DEFAULT_TIME, null, false);
    }


    public static <T> ValidateResult parse(T obj, String language) {
        return parse(obj, Constant.DEFAULT_TIME, language, false);
    }


    /**
     * 验证总入口
     *
     * @param obj      目标对象
     * @param time     哪一次
     * @param language 语言
     * @param sort
     * @param <T>
     * @return
     */
    public static <T> ValidateResult parse(T obj, String time, String language, boolean sort) {
        if (language != null) {
            ThreadMap.getInstance().setLanguage(language);
        }
        //当前直接转换为Map,但是嵌套Bean的时候有问题
        Map<String, String> objMap = BeanUtils.transBean2Map(obj);
        ThreadMap.getInstance().setInnerContext(objMap);
        String otherType = ThreadMap.getInstance().getType();
        if (otherType != null) {
            if (time == null || time.split(",").length == 0) {
                time = otherType;
            } else {
                time = time + Constant.TYPE_DELIMITER + otherType;
            }
        }
        ThreadMap.getInstance().setSort(sort);
        Class<? extends Validate> validateClass;
        Validate validate;
        Map<String, ? extends  Object> validateResult;
        List<ValidateUnit<T>> list = getValidateList(obj, time);
        if (sort) {
            Collections.sort(list);
        }
        Map<Member, Boolean> cache = new HashMap<Member, Boolean>();
        ValidateResult result = new ValidateResult();
        for (ValidateUnit<?> unit : list) {
            List<Annotation> annos = unit.getAnnos();
            for (Annotation anno : annos) {
                Boolean isValidated = cache.get(unit.getMember());
                if (isValidated != null)
                    continue;
                Annotation annoanno = anno.annotationType().getAnnotation(ValidateClass.class);
                ValidateClass vc = (ValidateClass) annoanno;
                validateClass = vc.value();
                validate = ValidateBuilder.getInstanceOf(validateClass);
                validateResult = validate.validate(anno, unit.getMember(), obj);
                if (validateResult != null && !validateResult.isEmpty()) {
                    cache.put(unit.getMember(), true);
                    result.addErrors(validateResult);
                    //排序的情况只返回一个错误
                    if (sort)
                        return result;
                }
            }

        }
        return result;
    }


    /**
     * 获得要验证的列表
     *
     * @param obj  目标值
     * @param time 哪一次
     * @param <T>
     * @return
     */
    public static <T> List<ValidateUnit<T>> getValidateList(T obj, String time) {
        Class clazz = obj.getClass();
        //获得验证的<字段/方法名,验证单元>
        Map<String, List<ValidateUnit<T>>> thisMap = ValidateManager.getVal(clazz);
        if (thisMap == null) {
            thisMap = new HashMap<>();
            ValidateManager.setVal(clazz, thisMap);
        }
        List<ValidateUnit<T>> thisList = thisMap.get(time);
        if (thisList == null) {
            thisList = getValidate(obj, time);
            thisMap.put(time, thisList);
        }
        return thisList;
    }

}
