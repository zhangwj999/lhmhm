package com.lhmh.service.impl.doctor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.doctor.DoctorServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("doctorService")
@Transactional
public class DoctorServiceImpl extends CommonServiceImpl implements DoctorServiceI {
	
}