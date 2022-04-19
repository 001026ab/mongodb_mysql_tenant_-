package com.zgr.mongodb.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/18 18:20
 */

@Data
public class User {
    private String userName;

    private Date createTime;

}
