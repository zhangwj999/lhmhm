package com.lhmh.controller.applyquery;
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
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.applyquery.ApplyqueryEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyquery.ApplyqueryServiceI;

/**   
 * @Title: Controller
 * @Description: 申请单查询
 * @author zhangdaihao
 * @date 2017-08-03 21:31:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyqueryController")
public class ApplyqueryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyqueryController.class);

	@Autowired
	private ApplyqueryServiceI applyqueryService;
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
	 * 申请单查询列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyquery")
	public ModelAndView applyquery(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		return new ModelAndView("com/lhmh/applyquery/applyqueryList");
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
	public void datagrid(ApplyqueryEntity applyquery,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyqueryEntity.class, dataGrid);
		
		String date1 = applyquery.getDate1();
		String applyId = applyquery.getApplyId();
		String patientName = applyquery.getPatientName();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			applyquery.setDate1("*" + date1 + "*");
		}
		if(applyId != null && !"".equals(applyId)){
			applyquery.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyquery.setPatientName("*" + patientName + "*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyquery, request.getParameterMap());
		this.applyqueryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除申请单查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ApplyqueryEntity applyquery, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyquery = systemService.getEntity(ApplyqueryEntity.class, applyquery.getId());
		message = "申请单查询删除成功";
		applyqueryService.delete(applyquery);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加申请单查询
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ApplyqueryEntity applyquery, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyquery.getId())) {
			message = "申请单查询更新成功";
			ApplyqueryEntity t = applyqueryService.get(ApplyqueryEntity.class, applyquery.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(applyquery, t);
				applyqueryService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "申请单查询更新失败";
			}
		} else {
			message = "申请单查询添加成功";
			applyqueryService.save(applyquery);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 申请单查询列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ApplyqueryEntity applyquery, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyquery.getId())) {
			applyquery = applyqueryService.getEntity(ApplyqueryEntity.class, applyquery.getId());
			req.setAttribute("applyqueryPage", applyquery);
		}
		return new ModelAndView("com/lhmh/applyquery/applyquery");
	}
}
