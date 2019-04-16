package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Rate;

public interface RateMapper extends Mapper<Rate> {

	void addRate(@Param("rate")Rate rate);
}