package com.zgr.mongodb.mysqlTenant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:11
 * 使用注解切换数据源
 */


@Aspect
@Component
@Order(-1)
public class DataSourceAspect {
    @Pointcut("@annotation(com.zgr.mongodb.mysqlTenant.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object dataSourceArround(ProceedingJoinPoint proceed) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceed.getSignature();
        Method method = methodSignature.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource != null) {
            DataSourceContextHolder.setDataSource(dataSource.value().name());
        }
        try {
            return proceed.proceed();
        } finally {
            // 方法执行后销毁数据源
            DataSourceContextHolder.clearDataSource();
        }
    }
}
