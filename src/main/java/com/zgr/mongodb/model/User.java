package com.zgr.mongodb.model;

import com.zgr.mongodb.annotation.InitTime;
import com.zgr.mongodb.consat.TimeType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 18:20
 */

@Data
public class User {
    private String userName;
    @InitTime(value = TimeType.CREATE_TIME)
    private Date createTime;


}
