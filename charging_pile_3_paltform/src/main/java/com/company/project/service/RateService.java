package com.company.project.service;
import com.company.project.model.Rate;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/04/11.
 */
public interface RateService extends Service<Rate> {

	void addRate(String ef, String sf,int id);

}
