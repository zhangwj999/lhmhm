package com.lhmh.service.impl.apply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.apply.ApplyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("applyService")
@Transactional
public class ApplyServiceImpl extends CommonServiceImpl implements ApplyServiceI {
	
}