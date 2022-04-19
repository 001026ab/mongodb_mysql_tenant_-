package com.zgr.mongodb.mysqlTenant;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:07
 * 设置使用的数据源
 */


public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * DataSourceContextHolder
     * 获取使用的数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
