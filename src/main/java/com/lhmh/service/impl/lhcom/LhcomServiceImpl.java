package com.lhmh.service.impl.lhcom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.lhcom.LhcomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("lhcomService")
@Transactional
public class LhcomServiceImpl extends CommonServiceImpl implements LhcomServiceI {
	
}