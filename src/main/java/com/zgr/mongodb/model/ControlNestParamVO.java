package com.zgr.mongodb.model;

import lombok.Data;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/3 17:51
 */

@Data
public class ControlNestParamVO {
    /**
     * 飞行模式
     */

    private Float flightModel;
    /**
     * 俯仰
     */
    private Float pitch;
    /**
     * 横滚
     */
    private Float roll;
    /**
     * 偏航
     */
    private Float yaw;
    /**
     * 油门速度
     */
    private Float vt;

}
