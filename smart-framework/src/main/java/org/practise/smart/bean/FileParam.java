package org.practise.smart.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数.
 */
public class FileParam {

    private String fileName;
    private String fieldName;
    private long fileSize;
    private String contentType;
    private InputStream inputStream;

    public FileParam(String fileName, String fieldName, long fileSize, String contentType, InputStream inputStream) {
        this.fileName = fileName;
        this.fieldName = fieldName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        return "FileParam{" +
                "fileName='" + fileName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fileSize=" + fileSize +
                ", contentType='" + contentType + '\'' +
                ", inputStream=" + inputStream +
                '}';
    }
}
