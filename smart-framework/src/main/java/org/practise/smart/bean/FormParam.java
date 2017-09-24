package org.practise.smart.bean;

/**
 * 封装表单参数.
 */
public class FormParam {

    private String filedName;
    private Object filedValue;

    public FormParam(String filedName, Object filedValue) {
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public Object getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(Object filedValue) {
        this.filedValue = filedValue;
    }

    @Override
    public String toString() {
        return "FormParam{" +
                "filedName='" + filedName + '\'' +
                ", filedValue=" + filedValue +
                '}';
    }
}
