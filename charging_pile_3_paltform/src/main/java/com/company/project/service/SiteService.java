package com.company.project.service;
import com.company.project.model.Site;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
public interface SiteService extends Service<Site> {

	int addStation(JSONObject eachStation);



}
