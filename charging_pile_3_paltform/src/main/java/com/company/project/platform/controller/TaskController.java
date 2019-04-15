package com.company.project.platform.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.project.platform.util.PlatformToken;

import lombok.extern.slf4j.Slf4j;



/**
 * 定时任务
 * @author KPTEK
 *
 */
@Component
@Slf4j
public class TaskController {
		
    	
    	
	    /**  
	     * 心跳更新。启动时执行一次，之后每隔一小时执行一次  
	     */  
	    @Scheduled(fixedRate = 1000*60*60)   
	    public void getToken(){
	    	log.info("---定时任务---");
	    	//PlatformToken.getToken();
	    }
	    
	    /**  
	     * 每天凌晨零点统计上一天的温湿度平均值
	     */   
	   @Scheduled(cron="0 0 0 * * ?")   
	    public void humitureAverage(){
	    	
	    }
	  

	
}
