package org.practise.smart.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类.
 */
public class ArrayUtil {

    /**
     * 判断数组为空
     *
     * @param arrays
     * @return
     */
    public static boolean isEmpty(Object[] arrays) {
        return ArrayUtils.isEmpty(arrays);
    }

    /**
     * 判断数组不为空
     *
     * @param arrays
     * @return
     */
    public static boolean isNotEmpty(Object[] arrays) {
        return !ArrayUtils.isEmpty(arrays);
    }
}
