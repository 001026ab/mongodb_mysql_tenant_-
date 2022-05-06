package com.zgr.mongodb.mongodbTenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: MongoProperty
 *
 * @Author: WangYiHai
 * @Date: 2022/4/14 11:11
 * @Description: TODO
 * <p>
 * 仅仅是获取默认值，实质上取的值是yml文件中mongo下的值
 * @ConfigurationProperties(prefix = "mongo")这个注解就是和yml文件关联
 */
@ConfigurationProperties(prefix = "mongo")
@Component
@Data
public class MongoProperty {

    private String host = "127.0.0.1";
    private int port = 27017;
    private String username = "";
    private String password = "";
    private String database = "default";

}