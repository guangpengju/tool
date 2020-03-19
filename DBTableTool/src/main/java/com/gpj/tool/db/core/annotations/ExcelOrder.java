package com.gpj.tool.db.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className ExcelOrder
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 9:53
 * @version 1.0
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelOrder {
    int value();
    boolean required() default false;
}
