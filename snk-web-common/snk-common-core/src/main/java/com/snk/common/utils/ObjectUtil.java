package com.snk.common.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 对象操作工具类
 * @Date : 2021/6/10
 */
@Slf4j
public class ObjectUtil extends cn.hutool.core.util.ObjectUtil {

    /**
     *  方法使用说明： 在redis中存储hash结构数据时对象直接传入不行，我们可以先将对象转为map传入
     * @author Cai.ChangJun
     * @param o: 需要被转为map的对象
     * @return Map<String,Object>: 对象转换后的map对象
     * @version 1.0.0
     * @Date 2021/6/10 10:35
     */
    @SneakyThrows
    public static Map<String,Object> toMap(Object o) {
        Map<String, Object> map = new LinkedHashMap();
        Class<?> clazz = o.getClass();
        log.info("获取到对象地址{}", clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(o);
            if (value == null){
                value = "";
            }
            map.put(fieldName, value);
        }
        return map;

    }

    /**
     * map转对象
     * @author Cai.ChangJun
     * @param map: map对象
     * @param beanClass: 转化的真实对象
     * @return Object: 真是对象
     * @version 1.0.0
     * @Date 2021/6/10 10:46
     */
    public static Object mapToObject(Map<Object, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.containsKey(field.getName())) {
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }
}
