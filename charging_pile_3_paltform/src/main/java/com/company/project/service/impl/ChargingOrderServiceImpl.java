package com.company.project.service.impl;

import com.company.project.dao.ChargingOrderMapper;
import com.company.project.model.ChargingOrder;
import com.company.project.service.ChargingOrderService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/15.
 */
@Service
@Transactional
public class ChargingOrderServiceImpl extends AbstractService<ChargingOrder> implements ChargingOrderService {
    @Resource
    private ChargingOrderMapper tbChargingOrderMapper;

}
