package com.company.project.service.impl;

import com.company.project.dao.ChargingRecordMapper;
import com.company.project.model.ChargingRecord;
import com.company.project.service.ChargingRecordService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/04/16.
 */
@Service
@Transactional
public class ChargingRecordServiceImpl extends AbstractService<ChargingRecord> implements ChargingRecordService {
    @Resource
    private ChargingRecordMapper tbChargingRecordMapper;

}
