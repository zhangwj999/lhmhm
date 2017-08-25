package com.lhmh.service.impl.patieninfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.patieninfo.PatieninfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("patieninfoService")
@Transactional
public class PatieninfoServiceImpl extends CommonServiceImpl implements PatieninfoServiceI {
	
}