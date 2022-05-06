package com.zgr.mongodb.controller;

import com.zgr.mongodb.mysqlTenant.DataSource;
import com.zgr.mongodb.mysqlTenant.DataSourceContextHolder;
import com.zgr.mongodb.mysqlTenant.DataSourceEnum;
import com.zgr.mongodb.service.TestMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:37
 * 需要写拦截器进行初始数据源的配置
 */

@RestController
public class TestMysqlController {
    @Autowired
    private TestMysqlService testMysqlService;

    @PostMapping("test")
    // @DataSource(DataSourceEnum.docker)
    public void test2() {
        testMysqlService.queryList();
    }

    @PostMapping("test33")
    // @DataSource(DataSourceEnum.tes_security_jwt)
    public void test3() {
        DataSourceContextHolder.setDataSource("tes_security_jwt");
        testMysqlService.queryList();
    }

    @PostMapping("test55")
    @DataSource( DataSourceEnum.docker)
    public void test5() {
        ArrayList<String> strings = new ArrayList<>();
        if (strings.size() > 0) {
            System.out.println("测试");
        }
        System.out.println("测试22");
        testMysqlService.save();
    }
}
