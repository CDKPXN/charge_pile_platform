package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_charging_order")
public class ChargingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电桩id
     */
    private Integer pid;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 电量
     */
    @Column(name = "electric_quantity")
    private Double electricQuantity;

    /**
     * 充电开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 充电结束完成时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 0待支付1充电中2支付成功3已完成
     */
    private Integer status;

    /**
     * 电费(单位:分)
     */
    private Double cprice;

    /**
     * 服务费(单位:分)
     */
    private Double sprice;

    private Date ctime;

    /**
     * 0正常1已删除
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 充电前账户余额（分）
     */
    private Double balance;

    /**
     * 持续时间(单位：秒)
     */
    private Integer duration;

    /**
     * 1定时2定电量3定金额4自动充满
     */
    private Integer model;

    /**
     * 暂停时间
     */
    private Date otime;

    /**
     * 充电设定值(1定时(秒)2定电量（KW/H）3定金额（分））
     */
    @Column(name = "set_value")
    private Long setValue;

    @Column(name = "parking_fee")
    private Integer parkingFee;

    private Double cz;

    private Integer hd;

    @Column(name = "cz_cprice")
    private Double czCprice;

    @Column(name = "cz_sprice")
    private Double czSprice;

    @Column(name = "cz_pprice")
    private Integer czPprice;

    private Integer discount;

    @Column(name = "hd_balance")
    private Integer hdBalance;

    private String djhm;
    
    @Column(name = "charge_seq")
    private String chargeSeq;

    public String getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(String chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取电桩id
     *
     * @return pid - 电桩id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置电桩id
     *
     * @param pid 电桩id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取电量
     *
     * @return electric_quantity - 电量
     */
    public Double getElectricQuantity() {
        return electricQuantity;
    }

    /**
     * 设置电量
     *
     * @param electricQuantity 电量
     */
    public void setElectricQuantity(Double electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    /**
     * 获取充电开始时间
     *
     * @return begin_time - 充电开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置充电开始时间
     *
     * @param beginTime 充电开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取充电结束完成时间
     *
     * @return end_time - 充电结束完成时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置充电结束完成时间
     *
     * @param endTime 充电结束完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取0待支付1充电中2支付成功3已完成
     *
     * @return status - 0待支付1充电中2支付成功3已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0待支付1充电中2支付成功3已完成
     *
     * @param status 0待支付1充电中2支付成功3已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取电费(单位:分)
     *
     * @return cprice - 电费(单位:分)
     */
    public Double getCprice() {
        return cprice;
    }

    /**
     * 设置电费(单位:分)
     *
     * @param cprice 电费(单位:分)
     */
    public void setCprice(Double cprice) {
        this.cprice = cprice;
    }

    /**
     * 获取服务费(单位:分)
     *
     * @return sprice - 服务费(单位:分)
     */
    public Double getSprice() {
        return sprice;
    }

    /**
     * 设置服务费(单位:分)
     *
     * @param sprice 服务费(单位:分)
     */
    public void setSprice(Double sprice) {
        this.sprice = sprice;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取0正常1已删除
     *
     * @return is_del - 0正常1已删除
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置0正常1已删除
     *
     * @param isDel 0正常1已删除
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取充电前账户余额（分）
     *
     * @return balance - 充电前账户余额（分）
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置充电前账户余额（分）
     *
     * @param balance 充电前账户余额（分）
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取持续时间(单位：秒)
     *
     * @return duration - 持续时间(单位：秒)
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置持续时间(单位：秒)
     *
     * @param duration 持续时间(单位：秒)
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取1定时2定电量3定金额4自动充满
     *
     * @return model - 1定时2定电量3定金额4自动充满
     */
    public Integer getModel() {
        return model;
    }

    /**
     * 设置1定时2定电量3定金额4自动充满
     *
     * @param model 1定时2定电量3定金额4自动充满
     */
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * 获取暂停时间
     *
     * @return otime - 暂停时间
     */
    public Date getOtime() {
        return otime;
    }

    /**
     * 设置暂停时间
     *
     * @param otime 暂停时间
     */
    public void setOtime(Date otime) {
        this.otime = otime;
    }

    /**
     * 获取充电设定值(1定时(秒)2定电量（KW/H）3定金额（分））
     *
     * @return set_value - 充电设定值(1定时(秒)2定电量（KW/H）3定金额（分））
     */
    public Long getSetValue() {
        return setValue;
    }

    /**
     * 设置充电设定值(1定时(秒)2定电量（KW/H）3定金额（分））
     *
     * @param setValue 充电设定值(1定时(秒)2定电量（KW/H）3定金额（分））
     */
    public void setSetValue(Long setValue) {
        this.setValue = setValue;
    }

    /**
     * @return parking_fee
     */
    public Integer getParkingFee() {
        return parkingFee;
    }

    /**
     * @param parkingFee
     */
    public void setParkingFee(Integer parkingFee) {
        this.parkingFee = parkingFee;
    }

    /**
     * @return cz
     */
    public Double getCz() {
        return cz;
    }

    /**
     * @param cz
     */
    public void setCz(Double cz) {
        this.cz = cz;
    }

    /**
     * @return hd
     */
    public Integer getHd() {
        return hd;
    }

    /**
     * @param hd
     */
    public void setHd(Integer hd) {
        this.hd = hd;
    }

    /**
     * @return cz_cprice
     */
    public Double getCzCprice() {
        return czCprice;
    }

    /**
     * @param czCprice
     */
    public void setCzCprice(Double czCprice) {
        this.czCprice = czCprice;
    }

    /**
     * @return cz_sprice
     */
    public Double getCzSprice() {
        return czSprice;
    }

    /**
     * @param czSprice
     */
    public void setCzSprice(Double czSprice) {
        this.czSprice = czSprice;
    }

    /**
     * @return cz_pprice
     */
    public Integer getCzPprice() {
        return czPprice;
    }

    /**
     * @param czPprice
     */
    public void setCzPprice(Integer czPprice) {
        this.czPprice = czPprice;
    }

    /**
     * @return discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * @return hd_balance
     */
    public Integer getHdBalance() {
        return hdBalance;
    }

    /**
     * @param hdBalance
     */
    public void setHdBalance(Integer hdBalance) {
        this.hdBalance = hdBalance;
    }

    /**
     * @return djhm
     */
    public String getDjhm() {
        return djhm;
    }

    /**
     * @param djhm
     */
    public void setDjhm(String djhm) {
        this.djhm = djhm;
    }
}