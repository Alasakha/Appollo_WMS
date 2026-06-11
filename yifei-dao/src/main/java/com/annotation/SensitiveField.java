package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标记实体类中需要脱敏的字段
 */
@Target(ElementType.FIELD) // 仅适用于字段
@Retention(RetentionPolicy.RUNTIME) // 在运行时保留注解信息
public @interface SensitiveField {
    // 字段名
    String fieldName() default "";

    // 脱敏值类型（默认为String）
    String desensitizedValue() default "****";

    // 脱敏值类型（可选：STRING, DOUBLE, INTEGER, FLOAT, LONG）
    String desensitizedValueType() default "STRING";
}