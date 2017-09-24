package org.practise.smart;

import org.practise.smart.helper.BeanHelper;
import org.practise.smart.helper.ClassHelper;
import org.practise.smart.helper.ControllerHelper;
import org.practise.smart.helper.IocHelper;
import org.practise.smart.util.ClassUtil;

/**
 * 加载相应的Helper类.
 */
public class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class
        };
        for (Class<?> clazz : classList) {
            ClassUtil.loadClass(clazz.getName());
        }
    }
}
