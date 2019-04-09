package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 10:21
 **/
@Data
public class EquipmentInfo {
    //设备编码(存的num)
    @JSONField(name="EquipmentID")
    private String equipmentID;
    //设备生产商组
    //织机构代码
    @JSONField(name="ManufacturerID")
    private String manufacturerID;
    //设备生产商名
    //称
    @JSONField(name="ManufacturerName")
    private String manufacturerName;
    //设备型号
    @JSONField(name="EquipmentModel")
    private String equipmentModel;
    //设备生产日期
    @JSONField(name="ProductionDate")
    private String productionDate;
    //设备类型
    @JSONField(name="EquipmentType")
    private Integer equipmentType;
    //充电设备接口
    //列表
    @JSONField(name="ConnectorInfos")
    private ConnectorInfo connectorInfos;
    //充电设备经度
    @JSONField(name="EquipmentLng")
    private Double equipmentLng;
    //充电设备纬度
    @JSONField(name="EquipmentLat")
    private Double equipmentLat;
    //充电设备总功
    //率
    @JSONField(name="Power")
    private Double power;
    //充电设备名称
    @JSONField(name="EquipmentName")
    private String equipmentName;
}
