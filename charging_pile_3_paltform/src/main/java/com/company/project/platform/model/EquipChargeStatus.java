package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-18 09:52
 **/
@Data
public class EquipChargeStatus {
    //充电订单号
    @JSONField(name="StartChargeSeq")
    private String startChargeSeq;
    //充电订单
    //状态
    @JSONField(name="StartChargeSeqStat")
    private Integer startChargeSeqStat;
    //充电设备接
    //口编码(电桩num)
    @JSONField(name="ConnectorID")
    private String connectorID;
    //充电设备接
    //口状态
    @JSONField(name="ConnectorStatus")
    private Integer connectorStatus;
    //A 相电流
    @JSONField(name="CurrentA")
    private Double currentA;
    //B 相电流
    @JSONField(name="CurrentB")
    private Double currentB;
    //C 相电流
    @JSONField(name="CurrentC")
    private Double currentC;
    //A 相电压
    @JSONField(name="VoltageA")
    private Double voltageA;
    //B 相电压
    @JSONField(name="VoltageB")
    private Double voltageB;
    //C 相电压
    @JSONField(name="VoltageC")
    private Double voltageC;
    //电池剩余
    //电量
    @JSONField(name="Soc")
    private Double soc;
    //开始充电时
    //间
    @JSONField(name="StartTime")
    private String startTime;
    //启动方式
    @JSONField(name="ChargeModel")
    private Integer chargeModel;
    //本次采样时
    //间
    @JSONField(name="EndTime")
    private String endTime;
    //累计充电量
    @JSONField(name="TotalPower")
    private Double totalPower;
    //累计电费
    @JSONField(name="ElecMoney")
    private Double elecMoney;
    //累计服务费
    @JSONField(name="SeviceMoney")
    private Double seviceMoney;
    //累计总金额
    @JSONField(name="TotalMoney")
    private Double totalMoney;
    //时段数N
    @JSONField(name="SumPeriod")
    private Integer sumPeriod;
    //充电明细信
    //息
    @JSONField(name="ChargeDetails")
    private ChargeDetail[] chargeDetails;
}
