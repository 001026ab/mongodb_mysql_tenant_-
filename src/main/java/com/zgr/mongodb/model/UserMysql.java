package com.zgr.mongodb.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/19 17:39
 */

@Data
@TableName("user")
public class UserMysql {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}
