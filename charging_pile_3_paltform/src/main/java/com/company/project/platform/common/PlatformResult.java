package com.company.project.platform.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author lides
 * @Description
 * @Date 18-9-15 15:39
 **/
@Data
@Accessors(chain = true)
public class PlatformResult {

    @JSONField(name="Ret")
    private int ret;
    @JSONField(name="Msg")
    private String msg;
    @JSONField(name="Data")
    private String data;
    @JSONField(name="Sig")
    private String sig;

    public PlatformResult(){

    }




}
