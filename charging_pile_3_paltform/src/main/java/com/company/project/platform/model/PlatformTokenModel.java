package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.platform.config.PlatformConfig;
import lombok.Data;

@Data
public class PlatformTokenModel {

    @JSONField(name = "OperatorID")
    private String operatorID;
    @JSONField(name = "SuccStat")
    private Integer succStat;
    @JSONField(name = "AccessToken")
    private String accessToken;
    @JSONField(name = "TokenAvailableTime")
    private Integer tokenAvailableTime;
    @JSONField(name = "FailReason")
    private Integer failReason;

    public PlatformTokenModel(String accessToken) {
        this.accessToken = accessToken;
        this. operatorID = PlatformConfig.OperatorID;
        this. succStat = 0;
        this. tokenAvailableTime = 3600000;
        this. failReason = 0;
    }
}
