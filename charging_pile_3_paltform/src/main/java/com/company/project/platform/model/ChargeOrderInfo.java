package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-18 11:52
 **/
@Data
public class ChargeOrderInfo {
    //充电订单号
    @JSONField(name="StartChargeSeq")
    private String startChargeSeq;
    //充电设备接
    //口编码
    @JSONField(name="ConnectorID")
    private String connectorID;
    //开始充电时
    //间
    @JSONField(name="StartTime")
    private String startTime;
    //结束充电时
    //间
    @JSONField(name="EndTime")
    private String endTime;
    //启动方式
    @JSONField(name="ChargeModel")
    private Integer chargeModel;
    //累计充电量
    @JSONField(name="TotalPower")
    private Double totalPower;
    //总电费T
    @JSONField(name="TotalElecMoney")
    private Double totalElecMoney;
    //总服务费To
    @JSONField(name="TotalSeviceMoney")
    private Double totalSeviceMoney;
    //累计总金额
    @JSONField(name="TotalMoney")
    private Double totalMoney;
    //充电结束原
    //因
    @JSONField(name="StopReason")
    private Integer stopReason;
    //时段数N
    @JSONField(name="SumPeriod")
    private Integer sumPeriod;
    //充电明细信
    //息
    @JSONField(name="ChargeDetails")
    private ChargeDetail []chargeDetails;
    //车辆VIN
    @JSONField(name="Vin")
    private String vin;
}
