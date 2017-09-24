package org.practise.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类.
 */
public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例.
     *
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("创建实例失败.", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            LOGGER.error("创建实例失败.", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 创建实例（根据类名）.
     *
     * @param className
     * @return
     */
    public static Object newInstance(String className) {
        Class<?> loadClass = ClassUtil.loadClass(className);
        return newInstance(loadClass);
    }

    /**
     * 根据类的实例，参数调用类的方法.
     *
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("反射调用方法失败.", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值.
     *
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("反射设置成员变量的值失败.", e);
            throw new RuntimeException(e);
        }
    }
}
