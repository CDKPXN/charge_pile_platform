package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Pile;

public interface PileMapper extends Mapper<Pile> {

	void addPile(@Param("pile")Pile pile);
}