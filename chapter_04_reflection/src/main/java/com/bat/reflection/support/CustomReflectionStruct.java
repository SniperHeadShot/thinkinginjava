package com.bat.reflection.support;

/**
 * 测试反射 结构体
 *
 * @author ZhengYu
 * @version 1.0 2020/9/14 10:23
 **/
@CustomAnnotation(value = "strValue", valueArr = {"arrEle1, arrEle2"})
public class CustomReflectionStruct {

    private String name;

    private int age;

    private String[] hobbies;

    @Override
    public String toString() {
        return super.toString();
    }
}
