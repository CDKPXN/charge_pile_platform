package com.company.project.platform.util;

import com.alibaba.fastjson.JSON;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.utils.HttpClientUtil;
import com.company.project.utils.JsonUtil_My;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @Author lides
 * @Description
 * @Date 18-9-17 16:32
 **/
@Slf4j
public class PlatformToken {

    public static String getToken(){
        String Data = "{\"OperatorID\":\""+PlatformConfig.OperatorID+"\",\"OperatorSecret\":\""+PlatformConfig.OperatorSecret+"\"}";
        PlatformRequestBody platformRequestBody = new PlatformRequestBody(Data);
      //  Map<String, String> map = PlatformUtil.formatMap(platformRequestBody);
        String data = JSON.toJSONString(platformRequestBody);
        log.info("请求参数："+data);
        String post = HttpClientUtil.post(PlatformConfig.notificationUrl + "query_token", data);
        HashMap hashMap = JSON.parseObject(post, HashMap.class);
        Object data1 = hashMap.get("Data");
        String  token = "";
        if(data1 != null){
            token = data1+"";
        }
        token = AesCBC.decrypt(token);
        HashMap<String, Object> stringObjectHashMap = JsonUtil_My.toMap(token);
        if(stringObjectHashMap == null) return null;
       String accessToken = stringObjectHashMap.get("AccessToken")+"";
        return accessToken;
    }
}
