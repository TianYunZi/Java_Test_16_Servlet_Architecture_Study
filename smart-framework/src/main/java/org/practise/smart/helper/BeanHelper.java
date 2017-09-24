package org.practise.smart.helper;

import org.practise.smart.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类.
 */
public final class BeanHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClass();
        for (Class<?> beanClass : beanClassSet) {
            BEAN_MAP.put(beanClass, ReflectionUtil.newInstance(beanClass));
        }
    }

    /**
     * 获取Bean映射.
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 根据Class实例获取Bean实例.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz)) {
            LOGGER.error("不能通过该Class实例获取Bean实例");
            throw new RuntimeException("不能通过该Class实例获取Bean实例" + clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }

    /**
     * 设置Bean实例.
     *
     * @param clazz
     * @param object
     */
    public static void setBean(Class<?> clazz, Object object) {
        BEAN_MAP.put(clazz, object);
    }
}
