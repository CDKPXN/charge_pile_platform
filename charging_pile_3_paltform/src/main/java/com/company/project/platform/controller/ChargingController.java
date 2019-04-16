package com.company.project.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.common.PlatformResult;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.util.CommonBh;
import com.company.project.platform.util.PlatformOrderUtil;
import com.company.project.platform.util.PlatformToken;
import com.company.project.platform.util.PlatformUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/evcs/20160701")
@Slf4j
public class ChargingController {
	

	/**
	 * 扫码判断设备状态,发送认证设备请求
	 * @param json
	 * @return
	 */
	@PostMapping("/charging/qrCode")
    public Result qrCode(@RequestBody JSONObject json) {
		System.err.println(json);
		JSONObject data=new JSONObject();
		data.put("EquipAuthSeq", PlatformConfig.OperatorID+CommonBh.getOrderIdByUUId());
		String ConnectorID=json.getString("ConnectorID");
		data.put("ConnectorID", ConnectorID);
		try {
//			String res="   {\r\n" + 
//					"        \"Ret\": 0,\r\n" + 
//					"        \"Msg\": \"\",\r\n" + 
//					"        \"Data\": \"R8D9TwEbYBlfcvkZcjCAmTgcd9Dz49QBPZAZMpeP8KIf1mNIw2p9AN7sFc60XgP+Bg9ocuMNlkXl2uIWhxUmonLWYGhrbLRAfjTpJd7PGhBqbhWV/yEMHlqxOKE9O2mkgG0KZucysFGtJn9SKYCqgpJaUldGRn66RLi7GiGN/kE=\",\r\n" + 
//					"        \"Sig\": \"09CC158DACD26737B664010716B5871A\"\r\n" + 
//					"    }";
//			PlatformResult p=PlatformUtil.decryptResult(res);
			
			
			PlatformResult p=PlatformUtil.sendPost(data.toJSONString(), "query_equip_auth ");
			JSONObject result= JSONObject.parseObject(p.getData());
			if(result.getInteger("SuccStat")==0) {
				return ResultGenerator.genSuccessResult();
			}else {
				return ResultGenerator.genFailResult("电桩不可用");
			}
			
		} catch (Exception e) {
			log.info("request failed : "+e.getMessage());
			return ResultGenerator.genFailResult("请求超时");
		}
    }
	
	/**
	 * 设置并开始充电(新增订单,修改电桩状态预约,发送开始充电请求)
	 * @param json
	 * @return
	 */
	@PostMapping("/start_charging")
    public Result set_charging(@RequestBody JSONObject json) {
		
		return null;
    }
	/**
	 * 实时充电状态(更新电桩信息,更新订单信息,新增充电记录,根据余额、充电设置判断是否停止)
	 * @param json
	 * @return
	 */
	@PostMapping("/charging")
    public String charging(@RequestBody JSONObject json) {
		return null;
    }
	
	/**
	 * 停止充电(修改订单,电桩信息初始化,用户余额结算)
	 * @param json
	 * @return
	 */
	@PostMapping("/end_charging")
    public String end_charging(@RequestBody JSONObject json) {
		return null;
    }

}
