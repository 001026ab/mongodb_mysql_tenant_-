package com.zgr.mongodb.mysqlTenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:06
 * 操作工具类，可以使用setDataSource方法切换数据源
 *
 * extends AbstractRoutingDataSource
 */


public class DataSourceContextHolder  {
    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    // 使用ThreadLocal线程安全的使用变量副本
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    /**
     * 设置数据源
     * */
    public static void setDataSource(String dataSource) {
        logger.info("切换到数据源：{}", dataSource);
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 获取数据源
     * */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源
     * */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

    /*@Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }*/
}
