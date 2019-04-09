package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 09:43
 **/
@Data
public class StationInfo {



    //充电站ID
    @JSONField(name="StationID")
    private String stationID;
    //运营商ID
    @JSONField(name="OperatorID")
    private String operatorID;
    //设备所属方
    //ID
    @JSONField(name="EquipmentOwnerID")
    private String equipmentOwnerID;
    //充电站名称
    @JSONField(name="StationName")
    private String stationName;
    //充电站国家
    //代码
    @JSONField(name="CountryCode")
    private String countryCode;
    //充电站省市
    //辖区编码
    @JSONField(name="AreaCode")
    private String areaCode;
    //详细地址
    @JSONField(name="Address")
    private String address;
    //站点电话
    @JSONField(name="StationTel")
    private String stationTel;
    //服务电话
    @JSONField(name="ServiceTel")
    private String serviceTel;
    //站点类型
    @JSONField(name="StationType")
    private Integer stationType;
    //站点状态
    @JSONField(name="StationStatus")
    private Integer stationStatus;
    //车位数量
    @JSONField(name="ParkNums")
    private Integer parkNums;
    //经度
    @JSONField(name="StationLng")
    private Double stationLng;
    //纬度
    @JSONField(name="StationLat")
    private Double stationLat;
    //站点引导
    @JSONField(name="SiteGuide")
    private String siteGuide;
    //建设场所
    @JSONField(name="Construction")
    private Integer construction;
    //站点照片
    @JSONField(name="Pictures")
    private String[] pictures;
    //使用车型描
    //述
    @JSONField(name="MatchCars")
    private String matchCars;
    //车位楼层及
    //数量描述
    @JSONField(name="ParkInfo")
    private String parkInfo;
    //营业时间
    @JSONField(name="BusineHours")
    private String busineHours;
    //充电电费率
    @JSONField(name="ElectricityFee")
    private String electricityFee;
    //服务费率
    @JSONField(name="ServiceFee")
    private String serviceFee;
    //停车费
    @JSONField(name="ParkFee")
    private String parkFee;
    //支付方式
    @JSONField(name="Payment")
    private String payment;
    //是否支持预
    //约
    @JSONField(name="SupportOrder")
    private Integer supportOrder;
    //备注
    @JSONField(name="Remark")
    private String remark;
    //充电设备信
    //息列表
    @JSONField(name="EquipmentInfos")
    private EquipmentInfo[] equipmentInfos;


}
