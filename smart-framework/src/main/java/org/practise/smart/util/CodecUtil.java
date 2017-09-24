package org.practise.smart.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码和解码工具.
 */
public final class CodecUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码.
     *
     * @param source
     * @return
     */
    public static String encodeUrl(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("编码错误.", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * URL解码.
     *
     * @param source
     * @return
     */
    public static String decodeUrl(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解码失败.", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * MD5加密.
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        return DigestUtils.md5Hex(source);
    }
}
