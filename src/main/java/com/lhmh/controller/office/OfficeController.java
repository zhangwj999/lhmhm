package com.lhmh.controller.office;
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
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.office.OfficeEntity;
import com.lhmh.service.office.OfficeServiceI;

/**   
 * @Title: Controller
 * @Description: 科别
 * @author zhangdaihao
 * @date 2015-08-15 17:01:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/officeController")
public class OfficeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OfficeController.class);

	@Autowired
	private OfficeServiceI officeService;
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
	 * 科别列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "office")
	public ModelAndView office(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/office/officeList");
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
	public void datagrid(OfficeEntity office,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(OfficeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, office, request.getParameterMap());
		this.officeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除科别
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(OfficeEntity office, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		office = systemService.getEntity(OfficeEntity.class, office.getId());
		message = "科别删除成功";
		officeService.delete(office);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(OfficeEntity office, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(office.getId())) {
			message = "科别更新成功";
			OfficeEntity t = officeService.get(OfficeEntity.class, office.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(office, t);
				officeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "科别更新失败";
			}
		} else {
			message = "科别添加成功";
			officeService.save(office);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 科别列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(OfficeEntity office, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(office.getId())) {
			office = officeService.getEntity(OfficeEntity.class, office.getId());
			req.setAttribute("officePage", office);
		}
		return new ModelAndView("com/lhmh/office/office");
	}
	/**
	 * 检查ID
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkbh")
	@ResponseBody
	public ValidForm checkbh(HttpServletRequest request,HttpServletResponse response) {
		ValidForm v = new ValidForm();
		String fbh=oConvertUtils.getString(request.getParameter("fbh"));
		String id = oConvertUtils.getString(request.getParameter("id"));
		List<OfficeEntity> custList = systemService.findByProperty(OfficeEntity.class, "fbh", fbh);
		if(custList.size()>0){
			OfficeEntity cust = custList.get(0);
			if( !cust.getId().equals(id) ){
				v.setInfo("科别编号已存在");
				v.setStatus("n");
			}
		}
		return v;
	}

}
