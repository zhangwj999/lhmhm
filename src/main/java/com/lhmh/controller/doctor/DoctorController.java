package com.lhmh.controller.doctor;
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

import com.lhmh.entity.doctor.DoctorEntity;
import com.lhmh.service.doctor.DoctorServiceI;

/**   
 * @Title: Controller
 * @Description: 医护人员
 * @author zhangdaihao
 * @date 2015-12-01 12:50:08
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/doctorController")
public class DoctorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DoctorController.class);

	@Autowired
	private DoctorServiceI doctorService;
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
	 * 医护人员列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "doctor")
	public ModelAndView doctor(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/doctor/doctorList");
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
	public void datagrid(DoctorEntity doctor,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DoctorEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, doctor, request.getParameterMap());
		this.doctorService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除医护人员
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DoctorEntity doctor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		doctor = systemService.getEntity(DoctorEntity.class, doctor.getId());
		message = "医护人员删除成功";
		doctorService.delete(doctor);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加医护人员
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DoctorEntity doctor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(doctor.getId())) {
			message = "医护人员更新成功";
			DoctorEntity t = doctorService.get(DoctorEntity.class, doctor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(doctor, t);
				doctorService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "医护人员更新失败";
			}
		} else {
			message = "医护人员添加成功";
			doctorService.save(doctor);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 医护人员列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DoctorEntity doctor, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(doctor.getId())) {
			doctor = doctorService.getEntity(DoctorEntity.class, doctor.getId());
			req.setAttribute("doctorPage", doctor);
		}
		return new ModelAndView("com/lhmh/doctor/doctor");
	}
}
