package com.company.project.platform.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.util.AesCBC;
import com.company.project.platform.util.PlatformUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PlatformRequestBody {
    @JSONField(name="OperatorID")
    private String operatorID;
    @JSONField(name="Data")
    private String data;
    @JSONField(name="TimeStamp")
    private String timeStamp;
    @JSONField(name="Seq")
    private String seq;
    @JSONField(name="Sig")
    private String sig;

    public PlatformRequestBody() {
    }

    public PlatformRequestBody(String data) {
        try {
            this.data = AesCBC.encrypt(data).replaceAll("\r","").replaceAll("\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.operatorID = PlatformConfig.OperatorID;
        this.timeStamp = PlatformUtil.getTimeStamp();
        this.seq = "0001";
        this.sig = PlatformUtil.getRequestSign(this);
    }
}
