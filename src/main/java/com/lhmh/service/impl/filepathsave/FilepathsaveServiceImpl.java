package com.lhmh.service.impl.filepathsave;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.filepathsave.FilepathsaveServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("filepathsaveService")
@Transactional
public class FilepathsaveServiceImpl extends CommonServiceImpl implements FilepathsaveServiceI {
	
}