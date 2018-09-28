package com.ldh.poxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理
 * 为各种接口，没有继承关系的provider提供代理功能
 * retrofit类似原理
 * Create by ldh on 2017/11/10.
 */
public class CachedProviderHandler implements InvocationHandler {

    private Map<String, Object> cached = new HashMap<>();
    private Object target;

    /**
     * @param target 任意需要实现代理功能的类
     */
    public CachedProviderHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Type[] types = method.getParameterTypes();
        if (method.getName().matches("get.+") && (types.length == 1) && (types[0] == String.class)) {
            String key = (String) args[0];
            Object value = cached.get(key);
            if (value == null) {
                value = method.invoke(target, args);
                cached.put(key, value);
            }
            return value;
        }
        return method.invoke(target, args);
    }
}
