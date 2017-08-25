package com.lhmh.controller.lhmeetroom;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhmeetroom.LhMeetRoomEntity;
import com.lhmh.service.lhmeetroom.LhMeetRoomServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊室管理
 * @author zhangdaihao
 * @date 2017-06-11 11:27:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lhMeetRoomController")
public class LhMeetRoomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LhMeetRoomController.class);

	@Autowired
	private LhMeetRoomServiceI lhMeetRoomService;
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
	 * 会诊室管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lhMeetRoom")
	public ModelAndView lhMeetRoom(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		return new ModelAndView("com/lhmh/lhmeetroom/lhMeetRoomList");
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
	public void datagrid(LhMeetRoomEntity lhMeetRoom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LhMeetRoomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhMeetRoom, request.getParameterMap());
		this.lhMeetRoomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会诊室管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LhMeetRoomEntity lhMeetRoom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lhMeetRoom = systemService.getEntity(LhMeetRoomEntity.class, lhMeetRoom.getId());
		message = "会诊室管理删除成功";
		lhMeetRoomService.delete(lhMeetRoom);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会诊室管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LhMeetRoomEntity lhMeetRoom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(lhMeetRoom.getId())) {
			message = "会诊室管理更新成功";
			LhMeetRoomEntity t = lhMeetRoomService.get(LhMeetRoomEntity.class, lhMeetRoom.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(lhMeetRoom, t);
				lhMeetRoomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊室管理更新失败";
			}
		} else {
			message = "会诊室管理添加成功";
			lhMeetRoomService.save(lhMeetRoom);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会诊室管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LhMeetRoomEntity lhMeetRoom, HttpServletRequest req) {
		List<LhcomEntity> comsList = systemService.getList(LhcomEntity.class);
		req.setAttribute("comsList", comsList);
		if (StringUtil.isNotEmpty(lhMeetRoom.getId())) {
			lhMeetRoom = lhMeetRoomService.getEntity(LhMeetRoomEntity.class, lhMeetRoom.getId());
			req.setAttribute("lhMeetRoomPage", lhMeetRoom);
		}
		return new ModelAndView("com/lhmh/lhmeetroom/lhMeetRoom");
	}
	@RequestMapping(params = "checkId")
	public void checkId(LhMeetRoomEntity role,HttpServletRequest request,HttpServletResponse response) {
		String pkId=oConvertUtils.getString(request.getParameter("roomId"));
		String id = oConvertUtils.getString(request.getParameter("id"));
		List<LhMeetRoomEntity> custList = systemService.findByProperty(LhMeetRoomEntity.class, "roomId", pkId);
		String rlt = "";
		if(custList.size()>0){
			LhMeetRoomEntity cust = custList.get(0);
			if( !cust.getId().equals(id) ){
				rlt = "1";
			}
		}
		try {
			response.getWriter().write(rlt);
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("checkProductId+ee=="+e.toString());
			}
		}
	}
	/**
	 * 妫�煡ID
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkName")
	public void checkName(LhMeetRoomEntity role,HttpServletRequest request,HttpServletResponse response) {
		String pkName=oConvertUtils.getString(request.getParameter("roomName"));
		try {
			pkName = URLDecoder.decode(pkName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()){
				logger.info("checkBussTypeName+ee=="+e.toString());
			}
		}
		String id = oConvertUtils.getString(request.getParameter("id"));
		List<LhMeetRoomEntity> custList = systemService.findByProperty(LhMeetRoomEntity.class, "roomName", pkName);
		String rlt = "";
		if(custList.size()>0){
			LhMeetRoomEntity cust = custList.get(0);
			if( !cust.getId().equals(id) ){
				rlt = "1";
			}
		}
		try {
			response.getWriter().write(rlt);
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("checkBussTypeName+ee=="+e.toString());
			}
		}
		System.out.println(rlt);
	}
}
