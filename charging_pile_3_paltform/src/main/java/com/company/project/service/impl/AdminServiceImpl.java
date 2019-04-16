package com.company.project.service.impl;

import com.company.project.dao.AdminMapper;
import com.company.project.model.Admin;
import com.company.project.service.AdminService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/12.
 */
@Service
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    @Resource
    private AdminMapper tbAdminMapper;

	@Override
	public void addAdmin(String op) {
			Admin ad = new Admin();
			Admin a = tbAdminMapper.findByUsername(op);
			if(a == null ) {
				ad.setUsername(op);
				ad.setPassword(op);
				ad.setRole(2);
				ad.setCtime(new Date());
				tbAdminMapper.addAdmin(ad);
			}
			return;
	}

}
