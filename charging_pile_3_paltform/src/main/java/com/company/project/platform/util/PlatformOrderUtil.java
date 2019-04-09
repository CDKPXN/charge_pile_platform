package com.company.project.platform.util;

/**
 * @Author lides
 * @Description
 * @Date 18-9-19 16:30
 **/
public class PlatformOrderUtil {
    static String fillOrder = "XXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static String fillOrderId(long orderId){
        String str = orderId+"";
        String substring = fillOrder.substring(str.length());
        return substring+str;
    }
    public static long getOrderId(String orderId){
        String x = orderId.replaceAll("X", "");
        return Long.parseLong(x);
    }

    public static void main(String[] args) {
        String s = fillOrderId(1);
        System.out.println(s.length());
        System.out.println(s);
        long orderId = getOrderId(s);
        System.out.println(orderId);
    }
}
