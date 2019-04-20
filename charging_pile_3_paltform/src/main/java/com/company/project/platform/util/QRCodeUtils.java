package com.company.project.platform.util;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QRCodeUtils {
	
	public static String getConnectorID(String qr) {
		String ConnectorID="";
    	try {
    		String ss=qr.replace("hlht://", "");
    		ConnectorID=StringUtils.split(ss,".")[0];
		} catch (Exception e) {
			log.info("二维码ConnectorID解析失败 : "+qr);
		}
    	return ConnectorID;
	}
	
    public static String getOperatorID(String qr) {
    	String OperatorID="";
    	try {
    		String ss=qr.replace("hlht://", "");
    		OperatorID=StringUtils.split(ss,".")[1].split("/")[0];
		} catch (Exception e) {
			log.info("二维码运营商ID解析失败 : "+qr);
		}
    	return OperatorID;
		
	}
    
    public static String getQRCode(String qr) {
    	String QRCode="";
    	try {
    		String ss=qr.replace("hlht://", "");
    		QRCode=StringUtils.split(ss,".")[1].split("/")[1];
		} catch (Exception e) {
			log.info("二维码自定义部分解析失败 : "+qr);
		}
    	return QRCode;
		
	}
     public static void main(String[] args) {
		String qr="hlht://1.123456789/qrcode";
        System.err.println(getConnectorID(qr));
        System.err.println(getOperatorID(qr));
        System.err.println(getQRCode(qr));
	}
}
