package com.lhmh.service.impl.lhdoctor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.lhdoctor.LhdoctorServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("lhdoctorService")
@Transactional
public class LhdoctorServiceImpl extends CommonServiceImpl implements LhdoctorServiceI {
	
}