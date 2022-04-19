package com.zgr.mongodb.service.impl;

import com.zgr.mongodb.service.TestService;
import com.zgr.mongodb.tenant.MongoContext;
import com.zgr.mongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 18:22
 */

@Service
public class ServerImpl implements TestService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override

    public void save() {
       // MongoContext.setMongoDbFactory("zgr");
        User user = new User();
        user.setUserName("ERERE");
        //user.setCreateTime(new Date());
        mongoTemplate.save(user);
    }
}
