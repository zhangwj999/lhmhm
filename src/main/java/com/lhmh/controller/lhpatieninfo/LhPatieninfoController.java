package com.lhmh.controller.lhpatieninfo;
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

import com.lhmh.entity.lhpatieninfo.LhPatieninfoEntity;
import com.lhmh.service.lhpatieninfo.LhPatieninfoServiceI;

/**   
 * @Title: Controller
 * @Description: 病人管理
 * @author zhangdaihao
 * @date 2017-06-11 11:35:03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lhPatieninfoController")
public class LhPatieninfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LhPatieninfoController.class);

	@Autowired
	private LhPatieninfoServiceI lhPatieninfoService;
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
	 * 病人管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lhPatieninfo")
	public ModelAndView lhPatieninfo(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/lhpatieninfo/lhPatieninfoList");
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
	public void datagrid(LhPatieninfoEntity lhPatieninfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LhPatieninfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhPatieninfo, request.getParameterMap());
		this.lhPatieninfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除病人管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LhPatieninfoEntity lhPatieninfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lhPatieninfo = systemService.getEntity(LhPatieninfoEntity.class, lhPatieninfo.getId());
		message = "病人管理删除成功";
		lhPatieninfoService.delete(lhPatieninfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加病人管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LhPatieninfoEntity lhPatieninfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(lhPatieninfo.getId())) {
			message = "病人管理更新成功";
			LhPatieninfoEntity t = lhPatieninfoService.get(LhPatieninfoEntity.class, lhPatieninfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(lhPatieninfo, t);
				lhPatieninfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "病人管理更新失败";
			}
		} else {
			message = "病人管理添加成功";
			lhPatieninfoService.save(lhPatieninfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 病人管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LhPatieninfoEntity lhPatieninfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lhPatieninfo.getId())) {
			lhPatieninfo = lhPatieninfoService.getEntity(LhPatieninfoEntity.class, lhPatieninfo.getId());
			req.setAttribute("lhPatieninfoPage", lhPatieninfo);
		}
		return new ModelAndView("com/lhmh/lhpatieninfo/lhPatieninfo");
	}
}
