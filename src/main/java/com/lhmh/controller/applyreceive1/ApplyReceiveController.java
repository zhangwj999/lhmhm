package com.lhmh.controller.applyreceive1;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lhmh.entity.applyreceive1.ApplyReceiveEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhmeetroom.LhMeetRoomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyreceive1.ApplyReceiveServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊接收
 * @author zhangdaihao
 * @date 2017-09-10 21:59:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyReceiveController")
public class ApplyReceiveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyReceiveController.class);

	@Autowired
	private ApplyReceiveServiceI applyReceiveService;
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
	 * 会诊接收列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyReceive")
	public ModelAndView applyReceive(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		request.setAttribute( "date1", today);
		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		
		return new ModelAndView("com/lhmh/applyreceive1/applyReceiveList");
	}

	/**
	 * 会诊接收列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyReceiveFileUpload")
	public ModelAndView applyReceiveFileUpload(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		request.setAttribute( "date1", today);
		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		
		return new ModelAndView("com/lhmh/applyreceive1/applyReceiveFileUpload");
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
	public void datagrid(ApplyReceiveEntity applyReceive,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyReceiveEntity.class, dataGrid);
		
		String date1Begin = request.getParameter( "date1_begin" );
		// 为null是第一次访问，第二次请求是个空串
		if( request.getParameter( "searchColums" ) == null ){
			SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
			date1Begin = sdf.format(Calendar.getInstance().getTime());
		}
		if( StringUtil.isNotEmpty( date1Begin ) ){
			cq.ge( "date1", date1Begin );
		}
		String date1End = request.getParameter( "date1_end" );
		if( StringUtil.isNotEmpty( date1End ) ){
			cq.le( "date1", date1End);
		}
		String applyId = applyReceive.getApplyId();
		String patientName = applyReceive.getPatientName();
		String status = applyReceive.getStatus();
		
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		String key = user.getUserKey();
		List<LhMeetRoomEntity> list = systemService.findByProperty( LhMeetRoomEntity.class, "roomIp", key );
		if( list != null && list.size()>0 ){
			LhMeetRoomEntity e = list.get( 0 );
			applyReceive.setRoomId( e.getRoomId() );
		}
		
		if(applyId != null && !"".equals(applyId)){
			applyReceive.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyReceive.setPatientName("*" + patientName + "*");
		}
		
		if(status != null && !"".equals(status)){
			applyReceive.setStatus(status);
		}else{
			applyReceive.setStatus("3*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyReceive, request.getParameterMap());
		this.applyReceiveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会诊接收
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ApplyReceiveEntity applyReceive, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyReceive = systemService.getEntity(ApplyReceiveEntity.class, applyReceive.getId());
		message = "会诊接收删除成功";
		applyReceiveService.delete(applyReceive);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 接收，修改报告状态为待录入
	 * 
	 * @return
	 */
	@RequestMapping(params = "receive")
	@ResponseBody
	public AjaxJson receive(ApplyReceiveEntity applyReceive, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyReceive.getId())) {
			message = "接收成功";
			try {
				String id = request.getParameter("id");
				String sql="UPDATE LH_APPLY SET REPORT_STATUS = '1' WHERE ID = '" + id + "'";
				systemService.executeSql( sql );
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "接收失败";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 接收，修改报告状态为已录入
	 * 
	 * @return
	 */
	@RequestMapping(params = "receiveDone")
	@ResponseBody
	public AjaxJson receiveDone(ApplyReceiveEntity applyReceive, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyReceive.getId())) {
			message = "接收成功";
			try {
				String id = request.getParameter("id");
				String sql="UPDATE LH_APPLY SET REPORT_STATUS = '2' WHERE ID = '" + id + "'";
				systemService.executeSql( sql );
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "接收失败";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加会诊接收
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ApplyReceiveEntity applyReceive, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyReceive.getId())) {
			message = "会诊接收更新成功";
			ApplyReceiveEntity t = applyReceiveService.get(ApplyReceiveEntity.class, applyReceive.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(applyReceive, t);
				applyReceiveService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊接收更新失败";
			}
		} else {
			message = "会诊接收添加成功";
			applyReceiveService.save(applyReceive);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会诊接收列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ApplyReceiveEntity applyReceive, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyReceive.getId())) {
			applyReceive = applyReceiveService.getEntity(ApplyReceiveEntity.class, applyReceive.getId());
			req.setAttribute("applyReceivePage", applyReceive);
		}
		return new ModelAndView("com/lhmh/applyreceive1/applyReceive");
	}
}
