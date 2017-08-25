package com.lhmh.service.impl.applyaudit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.applyaudit.ApplyauditServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("applyauditService")
@Transactional
public class ApplyauditServiceImpl extends CommonServiceImpl implements ApplyauditServiceI {
	
}