package com.zgr.mongodb.annotation;


import com.zgr.mongodb.consat.DataSources;
import com.zgr.mongodb.mongodbTenant.MongoContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 19:01
 * 切换操作切换数据库注解@SwitchMongoDB
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(com.zgr.mongodb.annotation.SwitchMongoDB)")
    public void serviceTasks() {
    }
   // @Before("serviceTasks() && @annotation(logger)")
    //@Before("@annotation(com.zgr.mongodb.annotation.SwitchMongoDB)")
    @Before(value = "serviceTasks()")
    public void beforeSwitchDS(JoinPoint point) {
        //类名
        //String clsName=point.getSignature().getDeclaringType().getSimpleName();
        //方法名
       //String modName= point.getSignature().getName();

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(SwitchMongoDB.class)) {
                SwitchMongoDB annotation = method.getAnnotation(SwitchMongoDB.class);
                // 取出注解中的数据源名,默认数据源为ZGR数据库
                String dataSource = annotation.value();
                // 切换数据源
                MongoContext.setMongoDbFactory(dataSource);
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    //@After("@annotation(com.zgr.mongodb.annotation.SwitchMongoDB)")
    @After(value = "serviceTasks()")
    public void afterSwitchDS(JoinPoint point) {
        //清除数据源连接
        MongoContext.removeMongoDbFactory();
        //将数据源重新设置为默认数据源
        MongoContext.setMongoDbFactory(DataSources.DEFAULT_DATA_SOURCES);

    }
}
