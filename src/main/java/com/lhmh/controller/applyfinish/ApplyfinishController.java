package com.lhmh.controller.applyfinish;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.applyfinish.ApplyfinishEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyfinish.ApplyfinishServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊完成
 * @author zhangdaihao
 * @date 2017-07-30 11:06:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyfinishController")
public class ApplyfinishController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyfinishController.class);

	@Autowired
	private ApplyfinishServiceI applyfinishService;
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
	 * 会诊完成列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyfinish")
	public ModelAndView applyfinish(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		return new ModelAndView("com/lhmh/applyfinish/applyfinishList");
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
	public void datagrid(ApplyfinishEntity applyfinish,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyfinishEntity.class, dataGrid);
		
		
		String date1 = applyfinish.getDate1();
		String applyId = applyfinish.getApplyId();
		String patientName = applyfinish.getPatientName();
		String status = applyfinish.getStatus();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			applyfinish.setDate1("*" + date1 + "*");
		}else{
			// 测试需要  暂时去掉
//			apply.setDate1(today);
		}
		if(applyId != null && !"".equals(applyId)){
			applyfinish.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyfinish.setPatientName("*" + patientName + "*");
		}
		
		if(status != null && !"".equals(status)){
			applyfinish.setStatus(status);
		}else{
			applyfinish.setStatus("33");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyfinish, request.getParameterMap());
		this.applyfinishService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 会诊申请审核通过
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "finish")
	@ResponseBody
	public AjaxJson finish(ApplyEntity apply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(apply.getId())) {
			message = "会诊完成";
			System.out.println(apply.getId());
			try {
				String sql="UPDATE LH_APPLY SET STATUS = '90' WHERE ID = '" + apply.getId() + "'";
				systemService.executeSql(sql);
				System.out.println(sql);
				// querys.executeUpdate() 更新数量
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊失败";
			}
		} 
		j.setMsg(message);
		return j;
	}
}
