package com.company.project.platform.util;

import com.alibaba.fastjson.JSON;
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
    public static String sendPost(String data,String url,String accessToken){

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
        return post;
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
    
   /* 
    public static void main(String []args) {
    	String requestParam= "{name:fds,age:123}";
    	String url = "www.baidu.com";
    	String accessToken= "accessToken";
    	String sendPost = PlatformUtil.sendPost(requestParam, url, accessToken);
    	
    }*/
}
