package com.lhmh.controller.lhcom;
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

import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.service.lhcom.LhcomServiceI;

/**   
 * @Title: Controller
 * @Description: 主键
 * @author zhangdaihao
 * @date 2017-07-03 21:15:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lhcomController")
public class LhcomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LhcomController.class);

	@Autowired
	private LhcomServiceI lhcomService;
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
	 * 主键列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lhcom")
	public ModelAndView lhcom(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/lhcom/lhcomList");
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
	public void datagrid(LhcomEntity lhcom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LhcomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhcom, request.getParameterMap());
		this.lhcomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除主键
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LhcomEntity lhcom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lhcom = systemService.getEntity(LhcomEntity.class, lhcom.getId());
		message = "主键删除成功";
		lhcomService.delete(lhcom);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加主键
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LhcomEntity lhcom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(lhcom.getId())) {
			message = "主键更新成功";
			LhcomEntity t = lhcomService.get(LhcomEntity.class, lhcom.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(lhcom, t);
				lhcomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "主键更新失败";
			}
		} else {
			message = "主键添加成功";
			lhcomService.save(lhcom);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 主键列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LhcomEntity lhcom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lhcom.getId())) {
			lhcom = lhcomService.getEntity(LhcomEntity.class, lhcom.getId());
			req.setAttribute("lhcomPage", lhcom);
		}
		return new ModelAndView("com/lhmh/lhcom/lhcom");
	}
}
