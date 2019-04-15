package com.company.project.service.impl;

import com.company.project.dao.RateMapper;
import com.company.project.model.Rate;
import com.company.project.service.RateService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
@Service
@Transactional
public class RateServiceImpl extends AbstractService<Rate> implements RateService {
    @Resource
    private RateMapper tbRateMapper;

	@Override
	public void addRate(String ef, String sf,int id) {
		Rate rate = new Rate();
		
		rate.setCreateTime(new Date());
		rate.seteId(id);
		String rateString = subRate(ef,sf);
		rate.setRate(rateString);
		
	    tbRateMapper.addRate(rate);
	}
	
	public String subRate(String ef,String sf) {
		
		
		HashMap<String,Integer> hm = new HashMap<>();
		Object[] a = new HashMap[48]; 
		hm.put("ePrice", 1);
		hm.put("fPrice", 2);
		hm.put("sPrice", 3);
		for (int i = 0; i < 48; i++) {
			a[i] = hm;
		}
		String json = JSON.toJSONString(a,SerializerFeature.DisableCircularReferenceDetect);
		
		return json;
	}
   /* public static void main(String[] args) {
    	int i = 1;
    	int a = i++;  
    	int c = a + i;
    	System.out.println(i);
    	System.out.println(a);
    	System.out.println(c);
	
}		*/
	
	
	

}
