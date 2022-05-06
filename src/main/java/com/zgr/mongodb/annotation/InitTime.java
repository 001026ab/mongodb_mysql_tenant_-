package com.zgr.mongodb.annotation;

import java.lang.annotation.*;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 11:14
 */
//实体属性使用
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
//@Inherited //可以被子类继承
@Documented

public @interface InitTime {
      String value() ;
}
