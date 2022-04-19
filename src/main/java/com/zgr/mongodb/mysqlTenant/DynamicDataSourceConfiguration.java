package com.zgr.mongodb.mysqlTenant;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:09
 * 设置所有数据源
 */


@Configuration
public class DynamicDataSourceConfiguration {
   /* @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource dataSource1() {
        return new DruidDataSource();
    }*/

    private static final Map<Object, Object> CLIENT_DB_FACTORY_MAP = new HashMap<>();

    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        //dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
         //第一个数据库
        DruidDataSource druidDataSource = new DruidDataSource();
        StringBuilder builder = new StringBuilder();
        String docker = builder.append("jdbc:mysql://")
                .append("192.168.126.133:3306/")
                .append("docker")
                .append("?serverTimezone=GMT&useSSL=false&useUnicode=true&characterEncoding=utf8").toString();
        druidDataSource.setUrl(docker);
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        //druidDataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        CLIENT_DB_FACTORY_MAP.put("docker", druidDataSource);

        //第二个数据库
        DruidDataSource druidDataSource2 = new DruidDataSource();

        StringBuilder builder2 = new StringBuilder();
        String docker2 = builder2.append("jdbc:mysql://")
                .append("192.168.126.133:3306/")
                .append("tes_security_jwt")
                .append("?serverTimezone=GMT&useSSL=false&useUnicode=true&characterEncoding=utf8").toString();
        druidDataSource2.setUrl(docker2);
        druidDataSource2.setUsername("root");
        druidDataSource2.setPassword("123456");
        //druidDataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        druidDataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //配置多数据源
        CLIENT_DB_FACTORY_MAP.put("tes_security_jwt", druidDataSource2);

        dynamicDataSource.setTargetDataSources(CLIENT_DB_FACTORY_MAP);
        //配置多数据源
      /*  HashMap<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(DataSourceEnum.PRIMARY.name(), primaryDataSource());
        dataSourceMap.put(DataSourceEnum.DATASOURCE1.name(), dataSource1());
        dynamicDataSource.setTargetDataSources(dataSourceMap);*/
        return dynamicDataSource;

    }
}
