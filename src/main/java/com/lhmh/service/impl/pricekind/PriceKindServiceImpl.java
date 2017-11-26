package com.lhmh.service.impl.pricekind;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.pricekind.PriceKindServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("priceKindService")
@Transactional
public class PriceKindServiceImpl extends CommonServiceImpl implements PriceKindServiceI {
	
}