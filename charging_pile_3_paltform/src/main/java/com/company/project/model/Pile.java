package com.company.project.model;

import java.util.Date;
import javax.persistence.*;


//充电桩

@Table(name = "tb_pile")
public class Pile {
    /**
     * 充电桩id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电桩编码（由四位id组成（不够前面补0）+充电机类型组成：1位）
     */
    private String num;

    /**
     * 站点ID
     */
    private Integer sid;

    /**
     * 二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 状态(0空闲1收到预约2预约中3充电中4故障5锁定6暂停)
     */
    private Integer status;

    /**
     * 二维码图片位置
     */
    private String image;

    /**
     * 广告图片位置
     */
    private String adv;

    /**
     * 故障代码
     */
    private String fid;

    /**
     * 名称
     */
    private String name;

    private Date ctime;

    /**
     * 类型（0快充1慢充）
     */
    private Integer type;

    /**
     * 己充电电量
     */
    @Column(name = "electric_quantity")
    private Double electricQuantity;

    /**
     * 电压
     */
    private Double voltage;

    /**
     * 电流
     */
    private Double electricity;

    /**
     * 删除（0否1是)
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 惠光的ID
     */
    private Integer hgid;

    /**
     * 充电电费(单位:分)
     */
    private Integer cprice;

    /**
     * 服务费(单位:分)
     */
    private Integer sprice;

    /**
     * 设定值（定时 （秒）定电量（kw/h） 定金额（分） 自动充满）
     */
    @Column(name = "set_value")
    private Long setValue;

    /**
     * 实时充电时长(秒)
     */
    private Integer duration;

    /**
     * 充电机功率类型（0:交流 1:30kw 2:40kw 3:60kw  4:120kw ）
     */
    private Integer ctype;
    
    /**
     * 特来电电桩id
     */
    private String connectorId;

    /**
     * 获取充电桩id
     *
     * @return id - 充电桩id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置充电桩id
     *
     * @param id 充电桩id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取电桩编码（由四位id组成（不够前面补0）+充电机类型组成：1位）
     *
     * @return num - 电桩编码（由四位id组成（不够前面补0）+充电机类型组成：1位）
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置电桩编码（由四位id组成（不够前面补0）+充电机类型组成：1位）
     *
     * @param num 电桩编码（由四位id组成（不够前面补0）+充电机类型组成：1位）
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取站点ID
     *
     * @return sid - 站点ID
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置站点ID
     *
     * @param sid 站点ID
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取二维码
     *
     * @return qr_code - 二维码
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置二维码
     *
     * @param qrCode 二维码
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 获取状态(0空闲1收到预约2预约中3充电中4故障5锁定6暂停)
     *
     * @return status - 状态(0空闲1收到预约2预约中3充电中4故障5锁定6暂停)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(0空闲1收到预约2预约中3充电中4故障5锁定6暂停)
     *
     * @param status 状态(0空闲1收到预约2预约中3充电中4故障5锁定6暂停)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取二维码图片位置
     *
     * @return image - 二维码图片位置
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置二维码图片位置
     *
     * @param image 二维码图片位置
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取广告图片位置
     *
     * @return adv - 广告图片位置
     */
    public String getAdv() {
        return adv;
    }

    /**
     * 设置广告图片位置
     *
     * @param adv 广告图片位置
     */
    public void setAdv(String adv) {
        this.adv = adv;
    }

    /**
     * 获取故障代码
     *
     * @return fid - 故障代码
     */
    public String getFid() {
        return fid;
    }

    /**
     * 设置故障代码
     *
     * @param fid 故障代码
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取类型（0快充1慢充）
     *
     * @return type - 类型（0快充1慢充）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型（0快充1慢充）
     *
     * @param type 类型（0快充1慢充）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取己充电电量
     *
     * @return electric_quantity - 己充电电量
     */
    public Double getElectricQuantity() {
        return electricQuantity;
    }

    /**
     * 设置己充电电量
     *
     * @param electricQuantity 己充电电量
     */
    public void setElectricQuantity(Double electricQuantity) {
        this.electricQuantity = electricQuantity;
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
     * @return electricity - 电流
     */
    public Double getElectricity() {
        return electricity;
    }

    /**
     * 设置电流
     *
     * @param electricity 电流
     */
    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    /**
     * 获取删除（0否1是)
     *
     * @return is_del - 删除（0否1是)
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置删除（0否1是)
     *
     * @param isDel 删除（0否1是)
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取惠光的ID
     *
     * @return hgid - 惠光的ID
     */
    public Integer getHgid() {
        return hgid;
    }

    /**
     * 设置惠光的ID
     *
     * @param hgid 惠光的ID
     */
    public void setHgid(Integer hgid) {
        this.hgid = hgid;
    }

    /**
     * 获取充电电费(单位:分)
     *
     * @return cprice - 充电电费(单位:分)
     */
    public Integer getCprice() {
        return cprice;
    }

    /**
     * 设置充电电费(单位:分)
     *
     * @param cprice 充电电费(单位:分)
     */
    public void setCprice(Integer cprice) {
        this.cprice = cprice;
    }

    /**
     * 获取服务费(单位:分)
     *
     * @return sprice - 服务费(单位:分)
     */
    public Integer getSprice() {
        return sprice;
    }

    /**
     * 设置服务费(单位:分)
     *
     * @param sprice 服务费(单位:分)
     */
    public void setSprice(Integer sprice) {
        this.sprice = sprice;
    }

    /**
     * 获取设定值（定时 （秒）定电量（kw/h） 定金额（分） 自动充满）
     *
     * @return set_value - 设定值（定时 （秒）定电量（kw/h） 定金额（分） 自动充满）
     */
    public Long getSetValue() {
        return setValue;
    }

    /**
     * 设置设定值（定时 （秒）定电量（kw/h） 定金额（分） 自动充满）
     *
     * @param setValue 设定值（定时 （秒）定电量（kw/h） 定金额（分） 自动充满）
     */
    public void setSetValue(Long setValue) {
        this.setValue = setValue;
    }

    /**
     * 获取实时充电时长(秒)
     *
     * @return duration - 实时充电时长(秒)
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置实时充电时长(秒)
     *
     * @param duration 实时充电时长(秒)
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取充电机功率类型（0:交流 1:30kw 2:40kw 3:60kw  4:120kw ）
     *
     * @return ctype - 充电机功率类型（0:交流 1:30kw 2:40kw 3:60kw  4:120kw ）
     */
    public Integer getCtype() {
        return ctype;
    }

    /**
     * 设置充电机功率类型（0:交流 1:30kw 2:40kw 3:60kw  4:120kw ）
     *
     * @param ctype 充电机功率类型（0:交流 1:30kw 2:40kw 3:60kw  4:120kw ）
     */
    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

	public String getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(String equipmentId) {
		this.connectorId = equipmentId;
	}
    
    
}