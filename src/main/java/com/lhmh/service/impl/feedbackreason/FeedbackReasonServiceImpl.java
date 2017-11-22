package com.lhmh.service.impl.feedbackreason;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhmh.service.feedbackreason.FeedbackReasonServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("feedbackReasonService")
@Transactional
public class FeedbackReasonServiceImpl extends CommonServiceImpl implements FeedbackReasonServiceI {
	
}