package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_charging_record")
public class ChargingRecord {
    /**
     * 订单id
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    /**
     * 电压
     */
    private Double voltage;

    /**
     * 电流
     */
    private Double current;

    /**
     * 充电电量
     */
    private Double amount;

    /**
     * 电池工作模式
     */
    @Column(name = "battery_mode")
    private String batteryMode;

    /**
     * 电池类型
     */
    @Column(name = "battery_type")
    private String batteryType;

    /**
     * 整车动力蓄电池系统额定容量
     */
    @Column(name = "r_capacity")
    private Double rCapacity;

    /**
     * 整车动力蓄电池系统额定总电压
     */
    @Column(name = "r_voltage")
    private Double rVoltage;

    /**
     * BMS最高允许充电总电压
     */
    @Column(name = "max_bms_voltage")
    private Double maxBmsVoltage;

    /**
     * BMS最高允许充电总电流
     */
    @Column(name = "max_bms_current")
    private Double maxBmsCurrent;

    /**
     * 最高允许动力蓄电池温度
     */
    @Column(name = "battery_tmax")
    private Integer batteryTmax;

    /**
     * 整车动力蓄电池当前电池电压(开始充电前)
     */
    @Column(name = "battery_voltage")
    private Double batteryVoltage;

    /**
     * 需求电压
     */
    @Column(name = "v_need")
    private Double vNeed;

    /**
     * 需求电流
     */
    @Column(name = "c_need")
    private Double cNeed;

    /**
     * 充电电压测量值
     */
    @Column(name = "charging_v")
    private Double chargingV;

    /**
     * 充电电流测量值
     */
    @Column(name = "charging_c")
    private Double chargingC;

    /**
     * 充电机电压输出值
     */
    private Double vout;

    /**
     * 充电机电流输出值
     */
    private Double cout;

    /**
     * 当前荷电状态（SOC）
     */
    private String soc;

    /**
     * 估算剩余充电时间
     */
    @Column(name = "r_time")
    private Integer rTime;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 获取订单id
     *
     * @return oid - 订单id
     */
    public Long getOid() {
        return oid;
    }

    /**
     * 设置订单id
     *
     * @param oid 订单id
     */
    public void setOid(Long oid) {
        this.oid = oid;
    }

    /**
     * 获取电压
     *
     * @return voltage - 电压
     */
    public Double getVoltage() {
        return voltage;
    }

    /**
     * 设置电压
     *
     * @param voltage 电压
     */
    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    /**
     * 获取电流
     *
     * @return current - 电流
     */
    public Double getCurrent() {
        return current;
    }

    /**
     * 设置电流
     *
     * @param current 电流
     */
    public void setCurrent(Double current) {
        this.current = current;
    }

    /**
     * 获取充电电量
     *
     * @return amount - 充电电量
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置充电电量
     *
     * @param amount 充电电量
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获取电池工作模式
     *
     * @return battery_mode - 电池工作模式
     */
    public String getBatteryMode() {
        return batteryMode;
    }

    /**
     * 设置电池工作模式
     *
     * @param batteryMode 电池工作模式
     */
    public void setBatteryMode(String batteryMode) {
        this.batteryMode = batteryMode;
    }

    /**
     * 获取电池类型
     *
     * @return battery_type - 电池类型
     */
    public String getBatteryType() {
        return batteryType;
    }

    /**
     * 设置电池类型
     *
     * @param batteryType 电池类型
     */
    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    /**
     * 获取整车动力蓄电池系统额定容量
     *
     * @return r_capacity - 整车动力蓄电池系统额定容量
     */
    public Double getrCapacity() {
        return rCapacity;
    }

    /**
     * 设置整车动力蓄电池系统额定容量
     *
     * @param rCapacity 整车动力蓄电池系统额定容量
     */
    public void setrCapacity(Double rCapacity) {
        this.rCapacity = rCapacity;
    }

    /**
     * 获取整车动力蓄电池系统额定总电压
     *
     * @return r_voltage - 整车动力蓄电池系统额定总电压
     */
    public Double getrVoltage() {
        return rVoltage;
    }

    /**
     * 设置整车动力蓄电池系统额定总电压
     *
     * @param rVoltage 整车动力蓄电池系统额定总电压
     */
    public void setrVoltage(Double rVoltage) {
        this.rVoltage = rVoltage;
    }

    /**
     * 获取BMS最高允许充电总电压
     *
     * @return max_bms_voltage - BMS最高允许充电总电压
     */
    public Double getMaxBmsVoltage() {
        return maxBmsVoltage;
    }

    /**
     * 设置BMS最高允许充电总电压
     *
     * @param maxBmsVoltage BMS最高允许充电总电压
     */
    public void setMaxBmsVoltage(Double maxBmsVoltage) {
        this.maxBmsVoltage = maxBmsVoltage;
    }

    /**
     * 获取BMS最高允许充电总电流
     *
     * @return max_bms_current - BMS最高允许充电总电流
     */
    public Double getMaxBmsCurrent() {
        return maxBmsCurrent;
    }

    /**
     * 设置BMS最高允许充电总电流
     *
     * @param maxBmsCurrent BMS最高允许充电总电流
     */
    public void setMaxBmsCurrent(Double maxBmsCurrent) {
        this.maxBmsCurrent = maxBmsCurrent;
    }

    /**
     * 获取最高允许动力蓄电池温度
     *
     * @return battery_tmax - 最高允许动力蓄电池温度
     */
    public Integer getBatteryTmax() {
        return batteryTmax;
    }

    /**
     * 设置最高允许动力蓄电池温度
     *
     * @param batteryTmax 最高允许动力蓄电池温度
     */
    public void setBatteryTmax(Integer batteryTmax) {
        this.batteryTmax = batteryTmax;
    }

    /**
     * 获取整车动力蓄电池当前电池电压(开始充电前)
     *
     * @return battery_voltage - 整车动力蓄电池当前电池电压(开始充电前)
     */
    public Double getBatteryVoltage() {
        return batteryVoltage;
    }

    /**
     * 设置整车动力蓄电池当前电池电压(开始充电前)
     *
     * @param batteryVoltage 整车动力蓄电池当前电池电压(开始充电前)
     */
    public void setBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    /**
     * 获取需求电压
     *
     * @return v_need - 需求电压
     */
    public Double getvNeed() {
        return vNeed;
    }

    /**
     * 设置需求电压
     *
     * @param vNeed 需求电压
     */
    public void setvNeed(Double vNeed) {
        this.vNeed = vNeed;
    }

    /**
     * 获取需求电流
     *
     * @return c_need - 需求电流
     */
    public Double getcNeed() {
        return cNeed;
    }

    /**
     * 设置需求电流
     *
     * @param cNeed 需求电流
     */
    public void setcNeed(Double cNeed) {
        this.cNeed = cNeed;
    }

    /**
     * 获取充电电压测量值
     *
     * @return charging_v - 充电电压测量值
     */
    public Double getChargingV() {
        return chargingV;
    }

    /**
     * 设置充电电压测量值
     *
     * @param chargingV 充电电压测量值
     */
    public void setChargingV(Double chargingV) {
        this.chargingV = chargingV;
    }

    /**
     * 获取充电电流测量值
     *
     * @return charging_c - 充电电流测量值
     */
    public Double getChargingC() {
        return chargingC;
    }

    /**
     * 设置充电电流测量值
     *
     * @param chargingC 充电电流测量值
     */
    public void setChargingC(Double chargingC) {
        this.chargingC = chargingC;
    }

    /**
     * 获取充电机电压输出值
     *
     * @return vout - 充电机电压输出值
     */
    public Double getVout() {
        return vout;
    }

    /**
     * 设置充电机电压输出值
     *
     * @param vout 充电机电压输出值
     */
    public void setVout(Double vout) {
        this.vout = vout;
    }

    /**
     * 获取充电机电流输出值
     *
     * @return cout - 充电机电流输出值
     */
    public Double getCout() {
        return cout;
    }

    /**
     * 设置充电机电流输出值
     *
     * @param cout 充电机电流输出值
     */
    public void setCout(Double cout) {
        this.cout = cout;
    }

    /**
     * 获取当前荷电状态（SOC）
     *
     * @return soc - 当前荷电状态（SOC）
     */
    public String getSoc() {
        return soc;
    }

    /**
     * 设置当前荷电状态（SOC）
     *
     * @param soc 当前荷电状态（SOC）
     */
    public void setSoc(String soc) {
        this.soc = soc;
    }

    /**
     * 获取估算剩余充电时间
     *
     * @return r_time - 估算剩余充电时间
     */
    public Integer getrTime() {
        return rTime;
    }

    /**
     * 设置估算剩余充电时间
     *
     * @param rTime 估算剩余充电时间
     */
    public void setrTime(Integer rTime) {
        this.rTime = rTime;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}