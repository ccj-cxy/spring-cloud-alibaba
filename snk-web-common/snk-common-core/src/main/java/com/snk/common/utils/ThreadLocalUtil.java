package com.snk.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ThreadLocalUtil
 * @Description TODO:
 * @Date: 2020-2-15 14:07
 * @Author: Caicj
 */
public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal() {
        protected Map<String, Object> initialValue() {
            return new HashMap();
        }
    };

    public static Map<String, Object> getThreadLocal(){
        return threadLocal.get();
    }
    public static <T> T get(String key) {
        Map map = (Map)threadLocal.get();
        return (T)map.get(key);
    }

    public static <T> T get(String key,T defaultValue) {
        Map map = (Map)threadLocal.get();
        return (T)map.get(key) == null ? defaultValue : (T)map.get(key);
    }

    public static void set(String key, Object value) {
        Map map = (Map)threadLocal.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        Map map = (Map)threadLocal.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static <T> T remove(String key) {
        Map map = (Map)threadLocal.get();
        return (T)map.remove(key);
    }
}
