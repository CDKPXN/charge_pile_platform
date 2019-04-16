package com.company.project.service;
import com.company.project.model.Pile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
public interface PileService extends Service<Pile> {

	void addPile(JSONArray eachPile,int id);

}
