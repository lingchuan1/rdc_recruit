package com.rdc.recruit.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IpRequest {

    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default 10;

    /**
     *存储到数据库的过期时间
     */
    int timeout() default 30;
}