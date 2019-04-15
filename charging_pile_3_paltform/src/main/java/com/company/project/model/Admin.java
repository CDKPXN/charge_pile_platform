package com.company.project.model;

import java.util.Date;
import javax.persistence.*;


//业主
@Table(name = "tb_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    /**
     * 1管理员 2业主
     */
    private Integer role;

    private String name;

    private String phone;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取1管理员 2业主
     *
     * @return role - 1管理员 2业主
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置1管理员 2业主
     *
     * @param role 1管理员 2业主
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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