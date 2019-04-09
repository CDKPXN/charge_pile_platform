package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 10:28
 **/
@Data
public class ConnectorStatusInfo {
    public static final int OFFLINE = 0;//离网
    public static final int LEISURE = 1;//空闲
    public static final int OCCUPY_UNCHARGING = 2;//占用（未充电）
    public static final int OCCUPY_CHARGING = 3;//占用（充电中）
    public static final int OCCUPY_ORDER = 4;//占用（预约）
    public static final int FAULT = 255;//故障

    //充电设备接口编
    //码
    @JSONField(name="ConnectorID")
    private String connectorID;
    //充电设备接口状
    //态
    @JSONField(name="Status")
    private Integer status;
    //车位状态
    @JSONField(name="ParkStatus")
    private Integer parkStatus;
    //地锁状态
    @JSONField(name="LockStatus")
    private Integer lockStatus;


}
