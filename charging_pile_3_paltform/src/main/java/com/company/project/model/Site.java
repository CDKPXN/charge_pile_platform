package com.company.project.model;

import java.util.Date;
import javax.persistence.*;


//充电站

@Table(name = "tb_site")
public class Site {
    /**
     * 站点ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否属于金信源（0否1是）
     */
    @Column(name = "is_jxy")
    private Integer isJxy;

    /**
     * 删除（0否1是）
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 评论个数（废弃）
     */
    private Integer comnum;

    /**
     * 评论分数（废弃）
     */
    private Integer comsum;

    /**
     * 停车费单价  分/小时
     */
    @Column(name = "parking_fee")
    private Integer parkingFee;

    /**
     * 站点维护人
     */
    @Column(name = "linkman_phone")
    private String linkmanPhone;
    
    /**
     * 特来电站点id
     */
    private String stationId;

    /**
     * 获取站点ID
     *
     * @return id - 站点ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置站点ID
     *
     * @param id 站点ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return district - 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取详细地址
     *
     * @return addr - 详细地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 设置详细地址
     *
     * @param addr 详细地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
     * 获取是否属于金信源（0否1是）
     *
     * @return is_jxy - 是否属于金信源（0否1是）
     */
    public Integer getIsJxy() {
        return isJxy;
    }

    /**
     * 设置是否属于金信源（0否1是）
     *
     * @param isJxy 是否属于金信源（0否1是）
     */
    public void setIsJxy(Integer isJxy) {
        this.isJxy = isJxy;
    }

    /**
     * 获取删除（0否1是）
     *
     * @return is_del - 删除（0否1是）
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置删除（0否1是）
     *
     * @param isDel 删除（0否1是）
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取评论个数（废弃）
     *
     * @return comnum - 评论个数（废弃）
     */
    public Integer getComnum() {
        return comnum;
    }

    /**
     * 设置评论个数（废弃）
     *
     * @param comnum 评论个数（废弃）
     */
    public void setComnum(Integer comnum) {
        this.comnum = comnum;
    }

    /**
     * 获取评论分数（废弃）
     *
     * @return comsum - 评论分数（废弃）
     */
    public Integer getComsum() {
        return comsum;
    }

    /**
     * 设置评论分数（废弃）
     *
     * @param comsum 评论分数（废弃）
     */
    public void setComsum(Integer comsum) {
        this.comsum = comsum;
    }

    /**
     * 获取停车费单价  分/小时
     *
     * @return parking_fee - 停车费单价  分/小时
     */
    public Integer getParkingFee() {
        return parkingFee;
    }

    /**
     * 设置停车费单价  分/小时
     *
     * @param parkingFee 停车费单价  分/小时
     */
    public void setParkingFee(Integer parkingFee) {
        this.parkingFee = parkingFee;
    }

    /**
     * 获取站点维护人
     *
     * @return linkman_phone - 站点维护人
     */
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    /**
     * 设置站点维护人
     *
     * @param linkmanPhone 站点维护人
     */
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
    
    
}