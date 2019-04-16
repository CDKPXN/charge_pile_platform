package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Admin;

public interface AdminMapper extends Mapper<Admin> {

	Admin findByUsername(@Param("op")String op);

	void addAdmin(@Param("ad")Admin ad);
}