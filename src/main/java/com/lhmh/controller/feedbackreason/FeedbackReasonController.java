package com.lhmh.controller.feedbackreason;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.feedbackreason.FeedbackReasonEntity;
import com.lhmh.service.feedbackreason.FeedbackReasonServiceI;

/**   
 * @Title: Controller
 * @Description: 驳回原因
 * @author zhangdaihao
 * @date 2017-11-22 21:26:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/feedbackReasonController")
public class FeedbackReasonController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FeedbackReasonController.class);

	@Autowired
	private FeedbackReasonServiceI feedbackReasonService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 驳回原因列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "feedbackReason")
	public ModelAndView feedbackReason(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/feedbackreason/feedbackReasonList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FeedbackReasonEntity feedbackReason,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FeedbackReasonEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, feedbackReason, request.getParameterMap());
		this.feedbackReasonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除驳回原因
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FeedbackReasonEntity feedbackReason, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		feedbackReason = systemService.getEntity(FeedbackReasonEntity.class, feedbackReason.getId());
		message = "驳回原因删除成功";
		feedbackReasonService.delete(feedbackReason);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加驳回原因
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FeedbackReasonEntity feedbackReason, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		System.out.println(feedbackReason.getId());
		System.out.println(feedbackReason.getName());
		if (StringUtil.isNotEmpty(feedbackReason.getId())) {
			message = "驳回原因更新成功";
			FeedbackReasonEntity t = feedbackReasonService.get(FeedbackReasonEntity.class, feedbackReason.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(feedbackReason, t);
				feedbackReasonService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "驳回原因更新失败";
			}
		} else {
			message = "驳回原因添加成功";
			feedbackReasonService.save(feedbackReason);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 驳回原因列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FeedbackReasonEntity feedbackReason, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(feedbackReason.getId())) {
			feedbackReason = feedbackReasonService.getEntity(FeedbackReasonEntity.class, feedbackReason.getId());
			req.setAttribute("feedbackReasonPage", feedbackReason);
		}
		return new ModelAndView("com/lhmh/feedbackreason/feedbackReason");
	}
}
