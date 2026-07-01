package com.mgkj.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Debounce {
    // 防抖间隔时间（默认 30 秒）
    long interval() default 30;

    // 时间单位（默认秒）
    TimeUnit unit() default TimeUnit.SECONDS;
}
