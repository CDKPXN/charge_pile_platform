package com.company.project.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.common.PlatformResult;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.model.ChargeDetail;
import com.company.project.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 17:32
 **/
@Slf4j
public class PlatformUtil {
    public static PlatformResult sendPost(String data,String url,String accessToken){

        log.info("accessToken:"+accessToken);
        HashMap<String, Object> head = new HashMap<>();
        head.put("Authorization",("Bearer "+accessToken).replaceAll("\r","").replaceAll("\n",""));
        log.info("=======推送数据=======");
        log.info(data);
        PlatformRequestBody platformRequestBody = new PlatformRequestBody(data);
        //Map<String, String> map = PlatformUtil.formatMap(platformRequestBody);
        String s1 = JSON.toJSONString(platformRequestBody);
        log.info("=======推送对象=======");
        log.info(s1);
        String post = HttpClientUtil.post(PlatformConfig.notificationUrl+url, s1,"json",head);
        return decryptResult(post);
    }
    public static boolean valid(PlatformRequestBody platformRequestBody){
        String timeStamp = platformRequestBody.getTimeStamp();
        String sig = platformRequestBody.getSig();
        //String data = platformRequestBody.getData();
        String seq = platformRequestBody.getSeq();
        String operatorID = platformRequestBody.getOperatorID();
        boolean flag =true;
        if(StringUtils.isEmpty(timeStamp) || StringUtils.isEmpty(sig)|| StringUtils.isEmpty(seq)
                || StringUtils.isEmpty(operatorID) ){
            flag = false;
        }
        return flag;
    }

    public static String getResultSign(PlatformResult platformResult){
       String  data = platformResult.getRet()+platformResult.getMsg()+platformResult.getData();
        String hmacMd5Str = HMacMD5.getHmacMd5Str(data);
        return hmacMd5Str;
    }
    public static String getRequestSign(PlatformRequestBody requestBody){
        String hmacMd5Str = null;
        try {
            String data1 = PlatformConfig.OperatorID+requestBody.getData()+requestBody.getTimeStamp()+requestBody.getSeq();
            hmacMd5Str = HMacMD5.getHmacMd5Str(data1.replaceAll("\n",""));
        } catch (Exception e) {
            log.info("获取签名失败:{}",e.getMessage());
        }
        return hmacMd5Str;
    }

    public static String getTimeStamp(){
        String dateFormat = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        return dateFormat;
    }


/*    public static Map<String,String> formatMap(PlatformRequestBody platformRequestBody){
        HashMap<String, String> map = new HashMap<>();
        map.put("Data",platformRequestBody.getData());
        map.put("Seq",platformRequestBody.getSeq());
        map.put("TimeStamp",platformRequestBody.getTimeStamp());
        map.put("OperatorID",platformRequestBody.getOperatorID());
        map.put("Sig",platformRequestBody.getSig());
        return  map;
    }*/
    
    public static PlatformResult decryptResult(String result) {
    	PlatformResult p=new PlatformResult();
    	try {
    		JSONObject json = JSONObject.parseObject(result);
    		p=(PlatformResult)JSONObject.toJavaObject(json, PlatformResult.class);
    		p.setData(AesCBC.decrypt(p.getData()));
		} catch (Exception e) {
			log.info("result decrypt failed...");
		}
		return p;
	}
    
    public static void main(String[] args) {
		String str="{\r\n" + 
				"     \"Ret\": 0,\r\n" + 
				"     \"Msg\": \"\",\r\n" + 
				"     \"Data\": \"uxeKP0ezR5yL8xSg4/ZCDh/N91/u86NXFxd2DrwZVW8zCPYcpl59Twz/yQZ3RaO4rDDrGmkvQignmNEJ+k4PGxdmIC+4fpJ8rU6osSobY+AeA0uueuQ5+eQiWBL6p6v5XMMm91brtK8yfFELYUWQzVcxABnAwK/+dyxtUhqLIxUpkwTEU/4ktN40df9IzzlLO5uvUknPGYu9yL0pp5w9vdRxmA1RiiTDNCysz6klr9bunGV3VJa2qpLcgeZMf/oG\",\r\n" + 
				"     \"Sig\": \"58E52010C7DEE87FE183B0AFA5B2BE30\"\r\n" + 
				"  }";
		JSONObject json = JSONObject.parseObject(str);
		PlatformResult p=(PlatformResult)JSONObject.toJavaObject(json, PlatformResult.class);
		p.setData(AesCBC.decrypt(p.getData()));
	    System.err.println(p);
	}
    
}
