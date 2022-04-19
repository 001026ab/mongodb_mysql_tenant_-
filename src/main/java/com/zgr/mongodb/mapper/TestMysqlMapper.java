package com.zgr.mongodb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgr.mongodb.model.UserMysql;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:43
 */

@Mapper
public interface TestMysqlMapper extends BaseMapper<UserMysql> {
}
