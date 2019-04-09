package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 10:27
 **/

@Data
public class StationStatusInfo {
    //充电站ID
    @JSONField(name="StationID")
    private String stationID;
    //充电设备接口
    //状态列表
    @JSONField(name="ConnectorStatusInfos")
    private ConnectorStatusInfo [] connectorStatusInfos;



}
