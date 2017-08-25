package com.lhmh.service.impl.office;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.office.OfficeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("officeService")
@Transactional
public class OfficeServiceImpl extends CommonServiceImpl implements OfficeServiceI {
	
}