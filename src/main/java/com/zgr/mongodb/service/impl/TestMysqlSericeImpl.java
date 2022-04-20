package com.zgr.mongodb.service.impl;

import com.zgr.mongodb.mapper.TestMysqlMapper;
import com.zgr.mongodb.model.UserMysql;
import com.zgr.mongodb.service.TestMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:38
 */

@Service
public class TestMysqlSericeImpl implements TestMysqlService {
    @Autowired
    private TestMysqlMapper testMysqlMapper;

    @Override
    public List<UserMysql> queryList() {
        List<UserMysql> userMysqls = testMysqlMapper.selectList(null);
        System.out.println(userMysqls);
        return userMysqls;
    }

    @Override
    public void save() {
        UserMysql userMysql = new UserMysql();
        userMysql.setUsername("ZZZ");
        userMysql.setPassword("$%#%$#");
        userMysql.setRole("RWE");
        testMysqlMapper.insert(userMysql);
    }
}
