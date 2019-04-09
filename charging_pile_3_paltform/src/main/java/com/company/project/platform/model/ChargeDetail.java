package com.company.project.platform.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author lides
 * @Description
 * @Date 18-9-18 09:55
 **/
@Data
public class ChargeDetail {
    //开始时间
    @JSONField(name="DetailStartTime")
    private String detailStartTime;
    //结束时间
    @JSONField(name="DetailStartTime")
    private String detailEndTime;
    //时段电价
    @JSONField(name="ElecPrice")
    private Double elecPrice;
    //时段服务费
    //价格
    @JSONField(name="SevicePrice")
    private Double sevicePrice;
    //时段充电量
    @JSONField(name="DetailPower")
    private Double detailPower;
    //时段电费
    @JSONField(name="DetailElecMoney")
    private Double detailElecMoney;
    //时段服务费
    @JSONField(name="DetailSeviceMoney")
    private Double detailSeviceMoney;

}
