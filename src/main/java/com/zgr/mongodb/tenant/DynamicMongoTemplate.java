package com.zgr.mongodb.tenant;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

/**
 * @author DAI
 * @date 2020/5/30 12:51
 * @Description 动态mongo模板
 */
public class DynamicMongoTemplate extends MongoTemplate {

    public DynamicMongoTemplate(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public DynamicMongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public DynamicMongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }

    @Override
    protected MongoDatabase doGetDatabase() {
        MongoDatabaseFactory mongoDbFactory = MongoContext.getMongoDbFactory();
        return mongoDbFactory == null ? super.doGetDatabase() : mongoDbFactory.getMongoDatabase();
    }
}
