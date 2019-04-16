package com.company.project.service.impl;

import com.company.project.dao.PileMapper;
import com.company.project.model.Pile;
import com.company.project.service.PileService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
@Service
@Transactional
public class PileServiceImpl extends AbstractService<Pile> implements PileService {
    @Resource
    private PileMapper tbPileMapper;

	@Override
	public void addPile(JSONArray equipmentArray,int id) {
		
		Pile pile = new Pile();
		
		for (int i = 0; i < equipmentArray.size(); i++) {
			JSONObject eachEquipment = (JSONObject) equipmentArray.get(i);
			JSONArray ConnectorArray = (JSONArray) eachEquipment.get("ConnectorInfos");
			
			for (int j = 0; j < ConnectorArray.size(); j++) {
				JSONObject eachConnector = (JSONObject) ConnectorArray.get(i);
				
				pile.setId(id);
				pile.setConnectorId((String) eachConnector.get("ConnectorId"));
				pile.setName((String) eachConnector.get("ConnectorName"));
				pile.setVoltage((Double) eachConnector.get("VoltageLowerLimits"));
				pile.setElectricity((Double) eachConnector.get("Current"));
				
				float power = (float) eachConnector.get("Power");
				if(power > 7.0) {
					pile.setType(0);
				}else {
					pile.setType(1);
				}
				
				tbPileMapper.addPile(pile);
				
			}
			
			
			
		}
	
	}

}
