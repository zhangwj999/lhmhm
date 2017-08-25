package com.lhmh.service.impl.fileroot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.fileroot.FilerootServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("filerootService")
@Transactional
public class FilerootServiceImpl extends CommonServiceImpl implements FilerootServiceI {
	
}