package com.whz.annotation;

import com.whz.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 14:51
 */
@Documented // 元注解，可以将注解的元数据信息包含在生成的文档中
@Target({ FIELD })   // 元注解，注解的作用目标。此处表示注解只能用于字段上
@Retention(RUNTIME) // 元注解，注解的保留策略。此处表示注解在运行时保留
@Constraint(validatedBy = {StateValidation.class})  // 元注解，指定提供校验规则的类
public @interface State {

    // 提供校验失败的提示消息
    String message() default "state的参数只能是【已发布】或【草稿】";

    // 指定校验分组
    Class<?>[] groups() default { };

    // 负载，获取到state注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
