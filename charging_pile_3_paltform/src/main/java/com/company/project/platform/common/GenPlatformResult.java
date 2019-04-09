package com.company.project.platform.common;

import com.company.project.platform.util.AesCBC;
import com.company.project.platform.util.PlatformUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lides
 * @Description
 * @Date 18-9-15 15:56
 **/
@Slf4j
public class GenPlatformResult {


    public static PlatformResult genSuccessResult(String data){
        log.info("========================返回数据：=====================");
        log.info(data);
        PlatformResult platformResult = new PlatformResult();
        platformResult.setRet(0);
        platformResult.setMsg(PlatformResultCode.SUCCESS_STR);
        try {
            String encrypt = AesCBC.encrypt(data);
            log.info("加密数据："+encrypt);
            platformResult.setData(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        platformResult.setSig(PlatformUtil.getResultSign(platformResult));
        return platformResult;
    }
    public static PlatformResult genFailResult(){
        String msg = PlatformResultCode.REQUEST_SERVICE_PARAMETER_ERROR_STR;
        return genFailResult(msg);
    }
    public static PlatformResult genFailResult(String msg){
        PlatformResult platformResult = new PlatformResult();
        platformResult.setRet(PlatformResultCode.REQUEST_SERVICE_PARAMETER_ERROR);
        platformResult.setMsg(msg);
        platformResult.setData(null);
        platformResult.setSig("");
        return platformResult;
    }

}
