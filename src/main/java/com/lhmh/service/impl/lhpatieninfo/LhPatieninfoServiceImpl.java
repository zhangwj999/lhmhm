package com.lhmh.service.impl.lhpatieninfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.lhpatieninfo.LhPatieninfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("lhPatieninfoService")
@Transactional
public class LhPatieninfoServiceImpl extends CommonServiceImpl implements LhPatieninfoServiceI {
	
}