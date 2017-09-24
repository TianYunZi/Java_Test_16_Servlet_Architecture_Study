package org.practise.smart.helper;

import org.practise.smart.bean.FormParam;
import org.practise.smart.bean.Param;
import org.practise.smart.util.ArrayUtil;
import org.practise.smart.util.CodecUtil;
import org.practise.smart.util.StreamUtil;
import org.practise.smart.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RequestHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

    /**
     * 创建请求对象.
     *
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        List<FormParam> formParams = new ArrayList<>();
        formParams.addAll(parseParameterNames(request));
        formParams.addAll(parseInputStream(request));
        return new Param(formParams);
    }

    private static List<FormParam> parseParameterNames(HttpServletRequest request) {
        List<FormParam> formParams = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String fieldName = parameterNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder builder = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; i++) {
                        builder.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            builder.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = builder.toString();
                }
                formParams.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParams;
    }

    private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<FormParam> formParams = new ArrayList<>();
        String body = CodecUtil.decodeUrl(StreamUtil.getString(request.getInputStream()));
        if (StringUtil.isNotEmpty(body)) {
            String[] splitString = StringUtil.splitString(body, "&");
            if (ArrayUtil.isNotEmpty(splitString)) {
                for (String kv : splitString) {
                    String[] array = StringUtil.splitString(kv, "=");
                    if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                        String fieldName = array[0];
                        String fieldValu = array[1];
                        formParams.add(new FormParam(fieldName, fieldValu));
                    }
                }
            }
        }
        return formParams;
    }
}
