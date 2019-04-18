package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Site;

public interface SiteMapper extends Mapper<Site> {

	Site fandByStationId(@Param("stationId")String stationId);

	int updateStationId(@Param("site")Site site);
}