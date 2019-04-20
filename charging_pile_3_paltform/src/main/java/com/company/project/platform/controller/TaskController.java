package com.company.project.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.platform.common.PlatformResult;
import com.company.project.platform.model.EquipmentInfo;
import com.company.project.platform.model.StationInfo;
import com.company.project.platform.util.PlatformToken;
import com.company.project.platform.util.PlatformUtil;
import com.company.project.service.AdminService;
import com.company.project.service.PileService;
import com.company.project.service.RateService;
import com.company.project.service.SiteService;

import lombok.extern.slf4j.Slf4j;



/**
 * 定时任务
 * @author KPTEK
 *
 */
@Component
@Slf4j
@Transactional
public class TaskController {
	    
	    @Autowired
		private SiteService siteService;
		
	    @Autowired
		private PileService pileService;
		
	    @Autowired
		private RateService rateService;
	    
	    @Autowired
		private AdminService adminService;
		   	
	    /**  
	     * 心跳更新。启动时执行一次，之后每隔一小时执行一次  
	     */  
	    @Scheduled(fixedRate = 1000*60*60)   
	    public void getToken(){
	    	log.info("---定时任务---");
	    	PlatformToken.getToken();
	    }
	    
	    /**  
	     * 每天凌晨零点统计上一天的温湿度平均值
	     */   
	   @Scheduled(cron="0 0 0 * * ?")   
	    public void humitureAverage(){
	    	
	    }
	   /**
	    * 每天获取充电站的信息
	    */
	   @Scheduled(cron="*/5 * * * * ?")   
	    public void getSiteInfo(){
		   
		   String data = " {\r\n" + 
		   			"\r\n" + 
		   			"      \"Ret\": 0,\r\n" + 
		   			"\r\n" + 
		   			"      \"Msg\": \"\",\r\n" + 
		   			"\r\n" + 
		   			"      \"Data\": \"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\",\r\n" + 
		   			"\r\n" + 
		   			"      \"Sig\": \"F12502298D39D1274A7A368733C4BCF1\"\r\n" + 
		   			"\r\n" + 
		   			"  }";
			   
			        //解密
		   			PlatformResult decryptResult = PlatformUtil.decryptResult(data);
		   			//获取data
		   			String data1 = decryptResult.getData().replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "");
		   			//转成json对象
		   			JSONObject parseObject = JSONObject.parseObject(data1);
		   			//站点数组
		   			JSONArray stationArray = (JSONArray) parseObject.get("StationInfos");
		   			for (int i = 0; i < stationArray.size(); i++) {
		   			    //每一个站点信息
		   	   			JSONObject eachStation = (JSONObject) stationArray.get(i);
		   	   			
		   	   			//保存业主信息
		   	   			String op = (String) eachStation.get("OperatorID");
		   	   			adminService.addAdmin(op);
		   	   			
		   	   			//保存站点信息
		   	   			int id = siteService.addStation(eachStation);
		   	   			
		   	   			//保存电站费率
		   	   			String ef = (String) eachStation.get("ElectricityFee");
		   	   			String sf = (String) eachStation.get("ServiceFee");
		   	   			rateService.addRate(ef,sf,id);
		   	   			
		   	   			//充电桩数组信息
		   	   			JSONArray pileArray = (JSONArray) eachStation.get("EquipmentInfos");
		   	   			
		   	   	   	    pileService.addPile(pileArray,id);
						
		   	   			
					}
		   			
		    }	

}
