/*
package com.zgr.mongodb.annotation;

*/
/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/28 11:19
 *//*


import java.lang.reflect.Field;
import java.util.Date;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.zgr.mongodb.consat.TimeType;
import lombok.extern.slf4j.Slf4j;

*/
/**
 * 在fastjson中使用此过滤器进行脱敏操作
 * @author ufi
 *//*

@Slf4j
public class ValueDefaultFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        InitTime desensitization;
        Field field = null;
        System.out.println("%%%%%%%%%%%%%%%%:"+value);
        try {
            field = object.getClass().getDeclaredField(name);
            if ((desensitization = field.getAnnotation(InitTime.class)) == null) {
                return value;
            }
        } catch (NoSuchFieldException e) {
            log.error("当前数据类型为{},值为{}", object.getClass(), value);
            return value;
        }

        String value1 = desensitization.value();
        if (value1.equals(TimeType.CREATE_TIME)) {
            return new Date();
        }
        if (value1.equals(TimeType.UPDATE_TIME)) {
            return new Date();
        }
        return value;
    }
}*/
