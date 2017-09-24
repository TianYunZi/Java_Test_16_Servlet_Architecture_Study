package org.practise.smart.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.practise.smart.bean.FileParam;
import org.practise.smart.bean.FormParam;
import org.practise.smart.bean.Param;
import org.practise.smart.util.CollectionUtil;
import org.practise.smart.util.FileUtil;
import org.practise.smart.util.StreamUtil;
import org.practise.smart.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传助手类.
 */
public final class UploadHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    private static ServletFileUpload servletFileUpload;

    /**
     * 初始化.
     *
     * @param servletContext
     */
    public static void init(ServletContext servletContext) {
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD,
                repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0) {
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 判断请求是否为Multipart类型.
     *
     * @param request
     * @return
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 创建请求对象.
     *
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request) throws FileUploadException, IOException {
        List<FormParam> formParams = new ArrayList<>();
        List<FileParam> fileParams = new ArrayList<>();
        Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
        if (CollectionUtil.isNotEmpty(fileItemListMap)) {
            for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()) {
                String fieldName = fileItemListEntry.getKey();
                List<FileItem> fileItems = fileItemListEntry.getValue();
                if (CollectionUtil.isNotEmpty(fileItems)) {
                    for (FileItem fileItem : fileItems) {
                        if (fileItem.isFormField()) {
                            String filedValue = fileItem.getString("utf-8");
                            formParams.add(new FormParam(fieldName, filedValue));
                        } else {
                            String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "utf-8"));
                            if (StringUtil.isNotEmpty(fieldName)) {
                                long fileItemSize = fileItem.getSize();
                                String contentType = fileItem.getContentType();
                                InputStream inputStream = fileItem.getInputStream();
                                fileParams.add(new FileParam(fieldName, fileName, fileItemSize, contentType, inputStream));
                            }
                        }
                    }
                }
            }
        }
        return new Param(formParams, fileParams);
    }

    /**
     * 上传文件.
     *
     * @param basePath
     * @param fileParam
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            if (fileParam != null) {
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                inputStream = new BufferedInputStream(fileParam.getInputStream());
                fileOutputStream = new FileOutputStream(filePath);
                outputStream = new BufferedOutputStream(fileOutputStream);
                StreamUtil.copyStream(inputStream, outputStream);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("路径未发现", e);
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("input stream无法关闭", e);
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    LOGGER.error("file output stream无法关闭", e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("output stream无法关闭", e);
                }
            }
        }
    }

    /**
     * 批量上传文件.
     *
     * @param basePath
     * @param fileParams
     */
    public static void uploadFile(String basePath, List<FileParam> fileParams) {
        if (CollectionUtil.isNotEmpty(fileParams)) {
            for (FileParam fileParam : fileParams) {
                uploadFile(basePath, fileParam);
            }
        }
    }
}
