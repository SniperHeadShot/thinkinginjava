package com.bat.thinkinginjava.common.enums;

/**
 * 文件目录枚举
 *
 * @author ZhengYu
 * @version 1.0 2020/8/21 16:58
 **/
public enum FileDirectoryTypeEnum {

    FILE(1, "file", "文件"),
    DIRECTORY(1, "directory", "目录"),
    ;

    FileDirectoryTypeEnum(Integer order, String code, String value) {
        this.order = order;
        this.code = code;
        this.value = value;
    }

    private Integer order;

    private String code;

    private String value;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
