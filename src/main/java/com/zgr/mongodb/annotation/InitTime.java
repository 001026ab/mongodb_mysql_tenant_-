package com.zgr.mongodb.annotation;

import com.zgr.mongodb.consat.TimeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 11:14
 */
//实体属性使用
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InitTime {
      String value() default TimeType.CREATE_TIME;
}
