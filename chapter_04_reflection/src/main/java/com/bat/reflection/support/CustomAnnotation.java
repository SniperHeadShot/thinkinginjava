package com.bat.reflection.support;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomAnnotation {

    String value();

    String[] valueArr();
}
