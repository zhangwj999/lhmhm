package com.lhmh.controller.ops_room;
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

import com.lhmh.entity.ops_room.Ops_roomEntity;
import com.lhmh.service.ops_room.Ops_roomServiceI;

/**   
 * @Title: Controller
 * @Description: 手术室
 * @author zhangdaihao
 * @date 2015-08-15 18:05:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ops_roomController")
public class Ops_roomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Ops_roomController.class);

	@Autowired
	private Ops_roomServiceI ops_roomService;
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
	 * 手术室列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ops_room")
	public ModelAndView ops_room(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/ops_room/ops_roomList");
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
	public void datagrid(Ops_roomEntity ops_room,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Ops_roomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ops_room, request.getParameterMap());
		this.ops_roomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除手术室
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Ops_roomEntity ops_room, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ops_room = systemService.getEntity(Ops_roomEntity.class, ops_room.getId());
		message = "手术室删除成功";
		ops_roomService.delete(ops_room);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加手术室
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Ops_roomEntity ops_room, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ops_room.getId())) {
			message = "手术室更新成功";
			Ops_roomEntity t = ops_roomService.get(Ops_roomEntity.class, ops_room.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ops_room, t);
				ops_roomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "手术室更新失败";
			}
		} else {
			message = "手术室添加成功";
			ops_roomService.save(ops_room);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 手术室列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Ops_roomEntity ops_room, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ops_room.getId())) {
			ops_room = ops_roomService.getEntity(Ops_roomEntity.class, ops_room.getId());
			req.setAttribute("ops_roomPage", ops_room);
		}
		return new ModelAndView("com/lhmh/ops_room/ops_room");
	}
}
