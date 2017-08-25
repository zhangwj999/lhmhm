package com.lhmh.service.impl.ou;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.ou.OuServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ouService")
@Transactional
public class OuServiceImpl extends CommonServiceImpl implements OuServiceI {
	
}