package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    private Date ctime;

    /**
     * 0正常1锁定2删除
     */
    private Integer status;

    /**
     * 集团ID(bid为0为非集团用户)
     */
    private Integer bid;

    /**
     * 头像文件名
     */
    private String portrait;

    /**
     * 姓名
     */
    private String name;

    /**
     * 余额（单位;分）
     */
    private Double balance;

    /**
     * 车辆类型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;

    /**
     * 最近用户登录生成的token
     */
    private String token;

    /**
     * 活动余额
     */
    @Column(name = "hd_balance")
    private Integer hdBalance;

    /**
     * 推荐次数
     */
    private Integer recommends;

    /**
     * 退款金额
     */
    private Integer refund;

    /**
     * 微信用户唯一凭证
     */
    private String wechat;

    /**
     * 支付宝用户唯一凭证
     */
    private String alipay;

    /**
     * qq用户唯一凭证
     */
    private String qq;

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
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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
     * 获取0正常1锁定2删除
     *
     * @return status - 0正常1锁定2删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0正常1锁定2删除
     *
     * @param status 0正常1锁定2删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取集团ID(bid为0为非集团用户)
     *
     * @return bid - 集团ID(bid为0为非集团用户)
     */
    public Integer getBid() {
        return bid;
    }

    /**
     * 设置集团ID(bid为0为非集团用户)
     *
     * @param bid 集团ID(bid为0为非集团用户)
     */
    public void setBid(Integer bid) {
        this.bid = bid;
    }

    /**
     * 获取头像文件名
     *
     * @return portrait - 头像文件名
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * 设置头像文件名
     *
     * @param portrait 头像文件名
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取余额（单位;分）
     *
     * @return balance - 余额（单位;分）
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置余额（单位;分）
     *
     * @param balance 余额（单位;分）
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取车辆类型
     *
     * @return vehicle_type - 车辆类型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置车辆类型
     *
     * @param vehicleType 车辆类型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 获取最近用户登录生成的token
     *
     * @return token - 最近用户登录生成的token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置最近用户登录生成的token
     *
     * @param token 最近用户登录生成的token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取活动余额
     *
     * @return hd_balance - 活动余额
     */
    public Integer getHdBalance() {
        return hdBalance;
    }

    /**
     * 设置活动余额
     *
     * @param hdBalance 活动余额
     */
    public void setHdBalance(Integer hdBalance) {
        this.hdBalance = hdBalance;
    }

    /**
     * 获取推荐次数
     *
     * @return recommends - 推荐次数
     */
    public Integer getRecommends() {
        return recommends;
    }

    /**
     * 设置推荐次数
     *
     * @param recommends 推荐次数
     */
    public void setRecommends(Integer recommends) {
        this.recommends = recommends;
    }

    /**
     * 获取退款金额
     *
     * @return refund - 退款金额
     */
    public Integer getRefund() {
        return refund;
    }

    /**
     * 设置退款金额
     *
     * @param refund 退款金额
     */
    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    /**
     * 获取微信用户唯一凭证
     *
     * @return wechat - 微信用户唯一凭证
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信用户唯一凭证
     *
     * @param wechat 微信用户唯一凭证
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取支付宝用户唯一凭证
     *
     * @return alipay - 支付宝用户唯一凭证
     */
    public String getAlipay() {
        return alipay;
    }

    /**
     * 设置支付宝用户唯一凭证
     *
     * @param alipay 支付宝用户唯一凭证
     */
    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    /**
     * 获取qq用户唯一凭证
     *
     * @return qq - qq用户唯一凭证
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置qq用户唯一凭证
     *
     * @param qq qq用户唯一凭证
     */
    public void setQq(String qq) {
        this.qq = qq;
    }
}