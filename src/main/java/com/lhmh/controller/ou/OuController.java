package com.lhmh.controller.ou;
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

import com.lhmh.entity.ou.OuEntity;
import com.lhmh.service.ou.OuServiceI;

/**   
 * @Title: Controller
 * @Description: 验证维护
 * @author zhangdaihao
 * @date 2017-07-10 21:39:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ouController")
public class OuController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OuController.class);

	@Autowired
	private OuServiceI ouService;
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
	 * 验证维护列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ou")
	public ModelAndView ou(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/ou/ouList");
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
	public void datagrid(OuEntity ou,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(OuEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ou, request.getParameterMap());
		this.ouService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除验证维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(OuEntity ou, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ou = systemService.getEntity(OuEntity.class, ou.getId());
		message = "验证维护删除成功";
		ouService.delete(ou);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加验证维护
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(OuEntity ou, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ou.getId())) {
			message = "验证维护更新成功";
			OuEntity t = ouService.get(OuEntity.class, ou.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ou, t);
				ouService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "验证维护更新失败";
			}
		} else {
			message = "验证维护添加成功";
			ouService.save(ou);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 验证维护列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(OuEntity ou, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ou.getId())) {
			ou = ouService.getEntity(OuEntity.class, ou.getId());
			req.setAttribute("ouPage", ou);
		}
		return new ModelAndView("com/lhmh/ou/ou");
	}
}
