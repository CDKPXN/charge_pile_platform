package com.company.project.platform.util;

import java.util.UUID;

/**
 * @author ADMIN
 * 生成18位流水号
 *
 */
public class CommonBh {
	 public static String getOrderIdByUUId() {
         int myStr  = (int)(Math.random()*900 + 100);//三位随机数
         int hashCodeV = UUID.randomUUID().toString().hashCode();
         if(hashCodeV < 0) {//有可能是负数
             hashCodeV = - hashCodeV;
         }
         return myStr+String.format("%015d", hashCodeV);
     }
     public static void main(String[] args) {
         System.out.println(getOrderIdByUUId());
     }
}
