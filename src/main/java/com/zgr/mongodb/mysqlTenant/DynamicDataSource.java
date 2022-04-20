package com.zgr.mongodb.mysqlTenant;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:07
 * 设置使用的数据源
 * 并且接受所有的数据源
 *
 * Spring boot提供了AbstractRoutingDataSource 根据用户定义的规则选择当前的数据源
 * 在每次数据库查询操作前执行。它的抽象方法 determineCurrentLookupKey() 决定使用哪个数据源。
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
