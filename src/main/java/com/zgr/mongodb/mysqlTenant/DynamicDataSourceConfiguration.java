package com.zgr.mongodb.mysqlTenant;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
/*    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;
    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username;
    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;*/

    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        //注意该类，是自己编写的类
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
       // DataSourceContextHolder dynamicDataSource = new DataSourceContextHolder();
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

        //配置默认数据源,实际的应用中不应该在这里设置，而是应该在拦截器根据租户等区分数据库的信息进行初始数据源的设置
        dynamicDataSource.setDefaultTargetDataSource(druidDataSource);
        //配置多数据源
      /*  HashMap<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(DataSourceEnum.PRIMARY.name(), primaryDataSource());
        dataSourceMap.put(DataSourceEnum.DATASOURCE1.name(), dataSource1());
        dynamicDataSource.setTargetDataSources(dataSourceMap);*/
        //连接数据查询数据库表中的其他租户的数据库连接
       /* return new AbstractJdbcDataSourceProvider(driverName, url, username, password) {
            @Override
            protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
                Map<String, DataSourceProperty> dataSourceMap = new HashMap<>();
                ResultSet resultSet = statement.executeQuery("select * from tenant");
                while (resultSet.next()) {
                    String tenant = resultSet.getString("tenant_id");
                    DataSourceProperty sourceProperty = new DataSourceProperty();
                    sourceProperty.setDriverClassName(resultSet.getString("data_source_driver"));
                    sourceProperty.setUrl(resultSet.getString("data_source_url"));
                    sourceProperty.setUsername(resultSet.getString("data_source_username"));
                    sourceProperty.setPassword(resultSet.getString("data_source_password"));
                    dataSourceMap.put(tenant, sourceProperty);
                }
                return dataSourceMap;
            }
        };*/
        return dynamicDataSource;
    }
}
