package com.company.project.platform.config;

/**
 * @Author lides
 * @Description
 * @Date 18-9-15 17:10
 **/
public class PlatformConfig {
    public static final   String  OperatorID = "123456789";
    //签名秘钥
    public static final   String  SigSecret = "1234567890abcdef";
    //秘钥：申请认证使用
    public static final   String  OperatorSecret = "1234567890abcdef";
    //消息秘钥，对数据加秘用
    public static String sKey="1234567890abcdef";
    //初始化向量
    public static String ivParameter="1234567890abcdef";
    public static final   String notificationUrl = "http://cdhlht.evxian.com:9000/evcs/20160701/";
   // public static final   String notificationUrl = "http://171.221.172.87:19000/evcs/";
}
