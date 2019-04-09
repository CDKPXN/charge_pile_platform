package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 10:25
 **/
@Data
public class ConnectorInfo {
    //充电设备接口
    //编码
    @JSONField(name = "ConnectorID")
    private String connectorID;
    //充电设备接口
    //名称
    @JSONField(name = "ConnectorName")
    private String connectorName;
    //充电设备接口
    //类型
    @JSONField(name = "ConnectorType")
    private Integer connectorType;
    //额定电压上限
    @JSONField(name = "VoltageUpperLimits")
    private Integer voltageUpperLimits;
    //额定电压下限
    @JSONField(name = "VoltageLowerLimits")
    private Integer voltageLowerLimits;
    //额定电流
    @JSONField(name = "Current")
    private Integer current;
    //额定功率
    @JSONField(name = "Power")
    private Double power;
    //车位号
    @JSONField(name = "ParkNo")
    private Double parkNo;
    //国家标准
    @JSONField(name = "NationalStandard")
    private Integer nationalStandard;
}
