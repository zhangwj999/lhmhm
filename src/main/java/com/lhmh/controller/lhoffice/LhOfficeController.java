package com.lhmh.controller.lhoffice;
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

import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.lhoffice.LhOfficeServiceI;

/**   
 * @Title: Controller
 * @Description: 科室管理
 * @author zhangdaihao
 * @date 2017-06-11 11:29:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lhOfficeController")
public class LhOfficeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LhOfficeController.class);

	@Autowired
	private LhOfficeServiceI lhOfficeService;
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
	 * 科室管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lhOffice")
	public ModelAndView lhOffice(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/lhoffice/lhOfficeList");
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
	public void datagrid(LhOfficeEntity lhOffice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LhOfficeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhOffice, request.getParameterMap());
		this.lhOfficeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除科室管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LhOfficeEntity lhOffice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lhOffice = systemService.getEntity(LhOfficeEntity.class, lhOffice.getId());
		message = "科室管理删除成功";
		lhOfficeService.delete(lhOffice);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科室管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LhOfficeEntity lhOffice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(lhOffice.getId())) {
			message = "科室管理更新成功";
			LhOfficeEntity t = lhOfficeService.get(LhOfficeEntity.class, lhOffice.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(lhOffice, t);
				lhOfficeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "科室管理更新失败";
			}
		} else {
			message = "科室管理添加成功";
			lhOfficeService.save(lhOffice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 科室管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LhOfficeEntity lhOffice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lhOffice.getId())) {
			lhOffice = lhOfficeService.getEntity(LhOfficeEntity.class, lhOffice.getId());
			req.setAttribute("lhOfficePage", lhOffice);
		}
		return new ModelAndView("com/lhmh/lhoffice/lhOffice");
	}
}
