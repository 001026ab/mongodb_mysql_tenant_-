package com.zgr.mongodb.controller;

import com.zgr.mongodb.annotation.SwitchMongoDB;
import com.zgr.mongodb.consat.DataSources;
import com.zgr.mongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 18:18
 */

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("test222")
    @SwitchMongoDB(value = DataSources.ZHW)
    public  void test2() {
        testService.save();
    }

}
