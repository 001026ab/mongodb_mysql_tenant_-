package com.zgr.mongodb.mysqlTenant;

import java.lang.annotation.*;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:10
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.docker;
}
