package com.zgr.mongodb.annotation;

import com.zgr.mongodb.consat.DataSources;

import java.lang.annotation.*;


/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 18:42
 */
//注解信息会被添加到Java文档中
//@Documented

//注解作用的位置，ElementType.METHOD表示该注解仅能作用于方法上
//注解用于什么地方，ElementType类型 --METHOD--》方法
@Target({ElementType.TYPE, ElementType.METHOD})
//什么时候使用该注解
//注解的生命周期，表示注解会被保留到什么阶段，可以选择编译阶段、类加载阶段，或运行阶段
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SwitchMongoDB {
    String value() default DataSources.DEFAULT_DATA_SOURCES; //默认为主数据源
}

/*@Target(ElementType.TYPE) // 接口、类、枚举、注解

//ElementType.FIELD--注解实体属性使用这个--例子：https://www.csdn.net/tags/OtDakg4sNTQzMDgtYmxvZwO0O0OO0O0O.html
@Target(ElementType.FIELD) // 字段、枚举的常量

@Target(ElementType.METHOD) // 方法

@Target(ElementType.PARAMETER) // 方法参数

@Target(ElementType.CONSTRUCTOR) // 构造函数

@Target(ElementType.LOCAL_VARIABLE) // 局部变量

@Target(ElementType.ANNOTATION_TYPE) // 注解

@Target(ElementType.PACKAGE) // 包*/