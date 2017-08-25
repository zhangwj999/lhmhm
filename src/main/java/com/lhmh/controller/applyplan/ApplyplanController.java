package com.lhmh.controller.applyplan;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.applyplan.ApplyplanEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyplan.ApplyplanServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊安排
 * @author zhangdaihao
 * @date 2017-07-30 09:44:16
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyplanController")
public class ApplyplanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyplanController.class);

	@Autowired
	private ApplyplanServiceI applyplanService;
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
	 * 会诊安排列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyplan")
	public ModelAndView applyplan(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		return new ModelAndView("com/lhmh/applyplan/applyplanList");
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
	public void datagrid(ApplyplanEntity applyplan,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyplanEntity.class, dataGrid);

		String date1 = applyplan.getDate1();
		String applyId = applyplan.getApplyId();
		String patientName = applyplan.getPatientName();
		String status = applyplan.getStatus();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			applyplan.setDate1("*" + date1 + "*");
		}else{
			// 测试需要  暂时去掉
//			apply.setDate1(today);
		}
		if(applyId != null && !"".equals(applyId)){
			applyplan.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyplan.setPatientName("*" + patientName + "*");
		}
		
		if(status != null && !"".equals(status)){
			applyplan.setStatus(status);
		}else{
			applyplan.setStatus("20");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyplan, request.getParameterMap());
		this.applyplanService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会诊安排
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ApplyplanEntity applyplan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyplan = systemService.getEntity(ApplyplanEntity.class, applyplan.getId());
		message = "会诊安排删除成功";
		applyplanService.delete(applyplan);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会诊安排
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ApplyplanEntity applyplan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyplan.getId())) {
			message = "会诊安排更新成功";
			ApplyplanEntity t = applyplanService.get(ApplyplanEntity.class, applyplan.getId());
			try {
				t.setStatus("30");
				MyBeanUtils.copyBeanNotNull2Bean(applyplan, t);
				applyplanService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊安排更新失败";
			}
		} else {
			message = "会诊安排添加成功";
			applyplanService.save(applyplan);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会诊安排列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ApplyplanEntity applyplan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyplan.getId())) {
			applyplan = applyplanService.getEntity(ApplyplanEntity.class, applyplan.getId());
			req.setAttribute("applyplanPage", applyplan);
		}
		return new ModelAndView("com/lhmh/applyplan/applyplan");
	}
}
