package com.lhmh.controller.filepathsave;
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

import com.lhmh.entity.filepathsave.FilepathsaveEntity;
import com.lhmh.service.filepathsave.FilepathsaveServiceI;

/**   
 * @Title: Controller
 * @Description: 上传资料路径信息
 * @author zhangdaihao
 * @date 2017-08-05 16:15:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/filepathsaveController")
public class FilepathsaveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FilepathsaveController.class);

	@Autowired
	private FilepathsaveServiceI filepathsaveService;
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
	 * 上传资料路径信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "filepathsave")
	public ModelAndView filepathsave(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/filepathsave/filepathsaveList");
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
	public void datagrid(FilepathsaveEntity filepathsave,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FilepathsaveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, filepathsave, request.getParameterMap());
		this.filepathsaveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除上传资料路径信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FilepathsaveEntity filepathsave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		filepathsave = systemService.getEntity(FilepathsaveEntity.class, filepathsave.getId());
		message = "上传资料路径信息删除成功";
		filepathsaveService.delete(filepathsave);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加上传资料路径信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FilepathsaveEntity filepathsave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		filepathsaveService.save(filepathsave);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 上传资料路径信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FilepathsaveEntity filepathsave, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(filepathsave.getId())) {
			filepathsave = filepathsaveService.getEntity(FilepathsaveEntity.class, filepathsave.getId());
			req.setAttribute("filepathsavePage", filepathsave);
		}
		return new ModelAndView("com/lhmh/filepathsave/filepathsave");
	}
}
