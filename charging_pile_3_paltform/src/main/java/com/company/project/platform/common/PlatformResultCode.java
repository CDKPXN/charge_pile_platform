package com.company.project.platform.common;

/**
 * @Author lides
 * @Description
 * @Date 18-9-15 14:42
 **/
public class PlatformResultCode {

    //系统繁忙
    public static final int SYSTEM_BUSY = -1;
    public static final String SYSTEM_BUSY_STR = "系统繁忙";
    //成功
    public static final int SUCCESS = 0;
    public static final String SUCCESS_STR = "SUCCESS";
    //签名错误
    public static final int SIGN_ERROR = 4001;
    public static final String SIGN_ERROR_STR = "签名错误";
    //令牌错误
    public static final int TOKEN_ERROR = 4002;
    public static final String TOKEN_ERROR_STR = "令牌错误";
    //必要参数不合法错误
    public static final int REQUEST_MUST_PARAMETER_ERROR = 4003;
    public static final String REQUEST_MUST_PARAMETER_ERROR_STR = "必要参数不合法错误";
    //业务参数不合法错误
    public static final int REQUEST_SERVICE_PARAMETER_ERROR = 4004;
    public static final String REQUEST_SERVICE_PARAMETER_ERROR_STR = "业务参数不合法错误";
    //系统错误
    public static final int SYSTEM_ERROR = 500;
    public static final String SYSTEM_ERROR_STR = "系统错误";
}
