package com.snk.common.utils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: 给bean赋值工具类
 * @Date: 2020/6/15
 * @author：Cai.ChangJun
 */
@Component
public class BeanAddAttributesUtil {

    /**
     *  向上造型为传入对象设置属性的value version 1.2
     * @Author Cai.ChangJun
     * @param model : 需要被设值的对象
     * @return java.lang.Object
     * @throws
     * @Date 2020/6/16 10:31
     */
    public static Object addAttributes(Object model, Map<String,Object> map) throws  IllegalAccessException {
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            Object value = map.get(key);
            setAttributes(model,key,value);
        }
        return model;
    }

    /**
     *  向上造型为传入对象设置属性的value version 1.2
     * @Author Cai.ChangJun
     * @param model : 需要被设值的对象
     * @param key : 需要被设置的属性
     * @param value : 被设置属性的值
     * @return java.lang.Object
     * @throws
     * @Date 2020/6/16 10:31
     */
    public static Object setAttributes(Object model,String key,Object value) throws IllegalAccessException{
        // 获取该对象的propertyName成员变量
        Field field = null;
        try {
            //出现没有的字段不赋值直接返回
            field = model.getClass().getDeclaredField(key);
        } catch (NoSuchFieldException e) {
            return model;
        }
        // 取消访问检查
        field.setAccessible(true);
        // 给对象的成员变量赋值为指定的值--->value
        field.set(model, value);
        return model;
    }
    //version 1.0
    @Deprecated
    public static Object Attribute(Object model,String key,String value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        HashMap<String,Class> fieldHashMap=new HashMap<>();
        Field[] fields = model.getClass().getDeclaredFields();
        for (int i = 0; i <fields.length ; i++) {
            Field fld = fields[i];
            fieldHashMap.put(fld.getName(), fld.getType());
            String name = fields[i].getName();
            Class type = fields[i].getType();
            //获取属性的set方法
            Method method = model.getClass().getMethod("set" + name.replaceFirst(name.substring(0, 1),
                    name.substring(0, 1).toUpperCase()), type);
            if (key.equals(name)){
                //将值设为传入值
                method.invoke(model, new Object[]{value});
            }
        }
        return model;
    }

    /*public static void main(String[] args) throws Exception {
        *//*OriginWritUrlDTO originWritUrlDTO = new OriginWritUrlDTO();
        Map<String,Object> map = new HashMap<>();
        map.put("filePath","123");
        map.put("seq","123");
        map.put("originWritId","123");
        addAttributes(originWritUrlDTO, map);
        System.out.println(originWritUrlDTO);
        ClearBeanUtil.reflectClassValueToNull(originWritUrlDTO);
        System.out.println(originWritUrlDTO);*//*
        *//* HashMap<String,Class> fieldHashMap=new HashMap<>();
        Field[] fields = OriginWritUrlDTO.class.getDeclaredFields();
        for (int i = 0; i <fields.length ; i++) {
            Field fld = fields[i];
            fieldHashMap.put(fld.getName(), fld.getType());
            String name = fields[i].getName();
            Class type = fields[i].getType();
            //获取属性的set方法
            Method method = OriginWritUrlDTO.class.getMethod("set" + name.replaceFirst(name.substring(0, 1),
                    name.substring(0, 1).toUpperCase()), type);
            String name1 = method.getName();
            System.out.println(name1);
            //将值设为null
            method.invoke(new OriginWritUrlDTO(), new Object[]{"123"});

           *//**//* System.out.println("name = " + fld.getName());
            System.out.println("decl class = " + fld.getDeclaringClass());
            System.out.println("type = " + fld.getType());
            System.out.println("-----");*//**//*
        }*//*
//        Class cls = Class.forName(OriginWritUrlDTO.class.getProtectionDomain().getCodeSource().getLocation().getFile());
        *//*Method[] declaredMethods = OriginWritUrlDTO.class.getDeclaredMethods();

        for (int i = 0; i < declaredMethods.length; i++) {
            Method method = declaredMethods[i];

            System.out.println(method.getName());
            if (method.getName().equals("setFilePath")){
                OriginWritUrlDTO.class.newInstance().setFilePath("aaa");
                Object invoke = method.invoke(OriginWritUrlDTO.class.newInstance().getFilePath());
                System.out.println(invoke);
            }

        }*//*

    }*/


}
