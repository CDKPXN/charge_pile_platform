package com.company.project.service.impl;

import com.company.project.dao.AdminMapper;
import com.company.project.dao.SiteMapper;
import com.company.project.model.Admin;
import com.company.project.model.Site;
import com.company.project.service.SiteService;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
@Service
@Transactional
public class SiteServiceImpl extends AbstractService<Site> implements SiteService {
    @Resource
    private SiteMapper tbSiteMapper;
    
    @Resource
    private AdminMapper adminMapper;

	@Override
	public int addStation(JSONObject e) {
		String stationId = (String)e.get("StationID");
		Site site2 = new Site();
		site2.setStationId(stationId);
		List<Site> select = tbSiteMapper.select(site2);
		if(select.size() == 1) {
			Site site = select.get(0);

			site.setStationId(stationId);
			site.setAddr((String)e.get("Address"));
			site.setLongitude(e.get("StationLng").toString());
			site.setLatitude(e.get("StationLat").toString());
			site.setName((String)e.get("StationName"));
			if(e.get("ParkFee").equals("免费")) {
				site.setParkingFee(0);
			}else {
				site.setParkingFee((Integer)e.get("ParFee"));
			}
			site.setProvince("四川省");
			site.setCity("成都市");
			site.setDistrict("高新区");
			String op = (String) e.get("OperatorID");
			Admin admin = adminMapper.findByUsername(op);
			site.setUid(admin.getId());
			
			tbSiteMapper.updateByPrimaryKeySelective(site);
			
			return site.getId();
		}else {
			Site site = new Site();

			site.setStationId(stationId);
			site.setAddr((String)e.get("Address"));
			
			site.setLongitude(e.get("StationLng").toString());
			
			site.setLatitude(e.get("StationLat").toString());
			site.setName((String)e.get("StationName"));
			if(e.get("ParkFee").equals("免费")) {
				site.setParkingFee(0);
			}else {
				site.setParkingFee((Integer)e.get("ParFee"));
			}
			
			site.setProvince("四川省");
			site.setCity("成都市");
			site.setDistrict("高新区");
			
			String op = (String) e.get("OperatorID");
			Admin admin = adminMapper.findByUsername(op);
			site.setUid(admin.getId());
			
			tbSiteMapper.insert(site);
			
			return site.getId();
		}
		
	}

}
