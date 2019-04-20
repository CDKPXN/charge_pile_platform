package com.company.project.service.impl;

import com.company.project.dao.PileMapper;
import com.company.project.model.Pile;
import com.company.project.service.PileService;
import com.alibaba.fastjson.JSONArray;
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
public class PileServiceImpl extends AbstractService<Pile> implements PileService {
    @Resource
    private PileMapper tbPileMapper;

	@Override
	public void addPile(JSONArray equipmentArray,int id) {
		
		for (int i = 0; i < equipmentArray.size(); i++) {
			JSONObject eachEquipment = (JSONObject) equipmentArray.get(i);
			JSONArray ConnectorArray = (JSONArray) eachEquipment.get("ConnectorInfos");
			for (int j = 0; j < ConnectorArray.size(); j++) {
				Pile pile = new Pile();
				JSONObject eachConnector = (JSONObject) ConnectorArray.get(j);
				String connectorId = (String) eachConnector.get("ConnectorID");
				pile.setConnectorId(connectorId);
				Pile select  =  tbPileMapper.selectOne(pile);
				if(select != null) {
					Pile p1 = select;
					p1.setSid(id);
					p1.setName((String) eachConnector.get("ConnectorName"));
					int vo = (int)eachConnector.get("VoltageLowerLimits");
					p1.setVoltage((double)vo);
					
					int current = (int)eachConnector.get("Current");
					p1.setElectricity((double)current );
					
					Object p = eachConnector.get("Power");
					float power=Float.parseFloat(p.toString());
					if(power > 7.0) {
						p1.setType(0);
					}else {
						p1.setType(1);
					}
					p1.setCtype(0);
					tbPileMapper.updateByPrimaryKeySelective(pile);
					
				}else {
					Pile p2 = new Pile();
					p2.setSid(id);
					p2.setConnectorId(connectorId);
					p2.setName((String) eachConnector.get("ConnectorName"));
					
					int vo = (int)eachConnector.get("VoltageLowerLimits");
					p2.setVoltage((double)vo);
					
					int current = (int)eachConnector.get("Current");
					p2.setElectricity((double)current );
					
					Object p = eachConnector.get("Power");
					float power=Float.parseFloat(p.toString());
					if(power > 7.0) {
						p2.setType(0);
					}else {
						p2.setType(1);
					}
					p2.setCtype(0);
					tbPileMapper.insert(p2);
				}
				
				
				
				
				
				
			}
			
			
			
		}
	
	}

}
