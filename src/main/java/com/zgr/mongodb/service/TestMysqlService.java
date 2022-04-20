package com.zgr.mongodb.service;

import com.zgr.mongodb.model.UserMysql;

import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:37
 */


public interface TestMysqlService {
    List<UserMysql> queryList();
    void save();
}
