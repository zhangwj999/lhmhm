package com.lhmh.service.impl.lhoffice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.lhoffice.LhOfficeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("lhOfficeService")
@Transactional
public class LhOfficeServiceImpl extends CommonServiceImpl implements LhOfficeServiceI {
	
}