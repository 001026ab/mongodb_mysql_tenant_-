package com.zgr.mongodb.tenant;

/**
 * ClassName: MongoContext
 *
 * @Author: WangYiHai
 * @Date: 2022/4/14 10:31
 * @Description: TODO
 */


import cn.hutool.core.util.StrUtil;
import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author DAI
 * @date 2020/5/30 13:55
 * @Description mongo 上下文
 * 多租户
 * mongodb://admin:UFIRoot%40Root123%2EcomUFI@192.168.126.133:27017/ZHW?authSource=admin
 */
@Component
public class MongoContext {

    private static final Map<String, MongoDatabaseFactory> MONGO_CLIENT_DB_FACTORY_MAP = new HashMap<>();
    private static final ThreadLocal<MongoDatabaseFactory> MONGO_DB_FACTORY_THREAD_LOCAL = new ThreadLocal<>();
    @Autowired
    private MongoProperty mongoProperty;

    @PostConstruct
    public void afterPropertiesSet() {
        String host = mongoProperty.getHost();
        int port = mongoProperty.getPort();
        String username = mongoProperty.getUsername();
        String password = mongoProperty.getPassword();

     /*   System.out.println("*******************");
        System.out.println("*******************");
        System.out.println("*******************");
        System.out.println(mongoProperty.toString());
        System.out.println(mongoProperty.toString());
        System.out.println(mongoProperty.toString());
        System.out.println(mongoProperty.toString());*/

        URLEncoder encoder = new URLEncoder();
        StringBuilder builder = new StringBuilder();
        builder.append("mongodb://");
        if (StrUtil.isNotEmpty(username)) {
            builder.append(username);
        }
        if (StrUtil.isNotEmpty(password)) {
            builder.append(":").append(encoder.encode(password, Charset.forName("UTF-8"))).append("@");
        }
        builder.append(host).append(":").append(port);
        String uriPrefix = builder.toString();
        builder.setLength(0);
        builder.append(uriPrefix).append("/").append("ZGR").append("?").append("authSource=").append(username);
        MONGO_CLIENT_DB_FACTORY_MAP.put("ZGR", new SimpleMongoClientDatabaseFactory(builder.toString()));
        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //builder中拼接的数据样式：mongodb://admin:UFIRoot%40Root123%2EcomUFI@192.168.126.133:27017/ZHW?authSource=admin
        System.out.println(builder.toString());
        builder.setLength(0);
        builder.append(uriPrefix).append("/").append("ZHW").append("?").append("authSource=").append(username);
        MONGO_CLIENT_DB_FACTORY_MAP.put("ZHW", new SimpleMongoClientDatabaseFactory(builder.toString()));
        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(builder.toString());
        builder.setLength(0);
       /* Set<String> tenants = tenantService.getAllTenants();
        if (!CollectionUtils.isEmpty(tenants)) {
            tenants.forEach(tenant -> {
                builder.append(uriPrefix).append("/").append(tenant).append("?").append("authSource=").append(username);
                System.out.println("mongo 多租户 uri：" + builder.toString());
                MONGO_CLIENT_DB_FACTORY_MAP.put(tenant, new SimpleMongoClientDatabaseFactory(builder.toString()));
                builder.setLength(0);
            });
        }*/

    }

    /**
     * 循环需要创建的数据库，否则会只创建一个
     *
     * @return
     */
    @Bean(name = "mongoTemplate")
    public DynamicMongoTemplate dynamicMongoTemplate() {
        Iterator<MongoDatabaseFactory> iterator = MONGO_CLIENT_DB_FACTORY_MAP.values().iterator();
        return new DynamicMongoTemplate(iterator.next());
    }

    /**
     * 会创建数据库
     *
     * @return
     */
    @Bean(name = "mongoDbFactory")
    public MongoDatabaseFactory mongoDbFactory() {
        Iterator<MongoDatabaseFactory> iterator = MONGO_CLIENT_DB_FACTORY_MAP.values().iterator();
        return iterator.next();
    }

    public static void setMongoDbFactory(String name) {
        MONGO_DB_FACTORY_THREAD_LOCAL.set(MONGO_CLIENT_DB_FACTORY_MAP.get(name));
    }

    public static MongoDatabaseFactory getMongoDbFactory() {
        return MONGO_DB_FACTORY_THREAD_LOCAL.get();
    }


    public static void removeMongoDbFactory() {
        MONGO_DB_FACTORY_THREAD_LOCAL.remove();
    }

}
