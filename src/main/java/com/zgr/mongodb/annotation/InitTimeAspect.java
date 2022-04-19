package com.zgr.mongodb.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 11:28
 */

@Aspect
@Component
public class InitTimeAspect {
    @Pointcut("@annotation(com.zgr.mongodb.annotation.InitTime)")
    public void initTime() {
    }

    @Before("initTime()")
    public void AfterInitTime(JoinPoint point) throws Exception {
        Field[] declaredFields = point.getThis().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&" + declaredField);
            if (declaredField.isAnnotationPresent(InitTime.class)) {
                InitTime annotation = declaredField.getAnnotation(InitTime.class);
               // if (annotation.value()) {
                    //为创建时间
                    // 拿到该属性的getet方法
                    Method m = this.getClass().getMethod("get" + getMethodName(declaredField.getName()));
                    System.out.println("属性名称："+m);
                    String invoke = (String) m.invoke(this);
                    System.out.println("属性值："+invoke);
             //   } else {
                    //为更新时间
              //  }
            }
        }
    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的
     */
    private String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
