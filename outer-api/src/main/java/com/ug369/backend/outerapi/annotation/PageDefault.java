package com.ug369.backend.outerapi.annotation;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefault {

    //页码
    int page() default 1;

    //每页条目数
    int size() default 20;
}
