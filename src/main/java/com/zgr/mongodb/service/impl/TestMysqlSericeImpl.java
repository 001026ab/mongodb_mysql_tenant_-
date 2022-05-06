package com.zgr.mongodb.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zgr.mongodb.mapper.TestMysqlMapper;
import com.zgr.mongodb.model.ControlNestParamVO;
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
        //System.out.println(userMysqls);
       // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        JSONObject json1 = JSONUtil.createObj();
        JSONObject json2 = JSONUtil.createObj();
        json1.put("roll", 12L);
        json1.put("yaw", 6465L);
        json1.put("pitch", "SADASD");
       // System.out.println(json1);
        json2.put("json1",json1);
        System.out.println(json2);
        JSONObject roll = json2.get("json1", JSONObject.class);
       // System.out.println(":::::"+roll.get("roll", Integer.class));;

       // System.out.println(roll);
       // JSONObject jsonObject = JSONUtil.parseObj(roll);
        ControlNestParamVO controlNestParamVO1 = JSONUtil.toBean(roll, ControlNestParamVO.class);

        //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(controlNestParamVO1);
        return userMysqls;
    }

    @Override
    public void save() {
        UserMysql userMysql = new UserMysql();
        userMysql.setUsername("ZZZ");
        userMysql.setPassword("$%#%$#");
        userMysql.setRole("RWE");
        testMysqlMapper.insert(userMysql);
        System.out.println("%%%%5:"+userMysql);
        userMysql.setRole("测试新增后返回的主键与实体情况");
        testMysqlMapper.updateById(userMysql);
        System.out.println("%%%%$$$$$$:"+userMysql);

    }
}
