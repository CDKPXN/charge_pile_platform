package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

//费率

@Table(name = "tb_rate")
public class Rate {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电站id
     */
    @Column(name = "e_id")
    private Integer eId;

    /**
     * 费率（基础电价，快充服务费，慢充服务费）
     */
    private String rate;

    /**
     * 费率创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取电站id
     *
     * @return e_id - 电站id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 设置电站id
     *
     * @param eId 电站id
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 获取费率（基础电价，快充服务费，慢充服务费）
     *
     * @return rate - 费率（基础电价，快充服务费，慢充服务费）
     */
    public String getRate() {
        return rate;
    }

    /**
     * 设置费率（基础电价，快充服务费，慢充服务费）
     *
     * @param rate 费率（基础电价，快充服务费，慢充服务费）
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 获取费率创建时间
     *
     * @return create_time - 费率创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置费率创建时间
     *
     * @param createTime 费率创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}