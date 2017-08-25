package com.lhmh.controller.fileroot;
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

import com.lhmh.entity.fileroot.FilerootEntity;
import com.lhmh.service.fileroot.FilerootServiceI;

/**   
 * @Title: Controller
 * @Description: 文档上传磁盘路径
 * @author zhangdaihao
 * @date 2017-07-26 22:43:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/filerootController")
public class FilerootController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FilerootController.class);

	@Autowired
	private FilerootServiceI filerootService;
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
	 * 文档上传磁盘路径列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fileroot")
	public ModelAndView fileroot(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/fileroot/filerootList");
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
	public void datagrid(FilerootEntity fileroot,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FilerootEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fileroot, request.getParameterMap());
		this.filerootService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文档上传磁盘路径
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FilerootEntity fileroot, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fileroot = systemService.getEntity(FilerootEntity.class, fileroot.getId());
		message = "文档上传磁盘路径删除成功";
		filerootService.delete(fileroot);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加文档上传磁盘路径
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FilerootEntity fileroot, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fileroot.getId())) {
			message = "文档上传磁盘路径更新成功";
			FilerootEntity t = filerootService.get(FilerootEntity.class, fileroot.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fileroot, t);
				filerootService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "文档上传磁盘路径更新失败";
			}
		} else {
			message = "文档上传磁盘路径添加成功";
			filerootService.save(fileroot);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 文档上传磁盘路径列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FilerootEntity fileroot, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fileroot.getId())) {
			fileroot = filerootService.getEntity(FilerootEntity.class, fileroot.getId());
			req.setAttribute("filerootPage", fileroot);
		}
		return new ModelAndView("com/lhmh/fileroot/fileroot");
	}
}
