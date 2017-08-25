package com.lhmh.controller.applyaudit;
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
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.applyaudit.ApplyauditEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyaudit.ApplyauditServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊申请审核
 * @author zhangdaihao
 * @date 2017-07-30 08:51:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyauditController")
public class ApplyauditController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyauditController.class);

	@Autowired
	private ApplyauditServiceI applyauditService;
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
	 * 会诊申请审核列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyaudit")
	public ModelAndView applyaudit(HttpServletRequest request) {
		
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		
		return new ModelAndView("com/lhmh/applyaudit/applyauditList");
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
	public void datagrid(ApplyauditEntity applyaudit,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyauditEntity.class, dataGrid);
		
		String date1 = applyaudit.getDate1();
		String applyId = applyaudit.getApplyId();
		String patientName = applyaudit.getPatientName();
		String status = applyaudit.getStatus();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			applyaudit.setDate1("*" + date1 + "*");
		}else{
			// 测试需要  暂时去掉
//			apply.setDate1(today);
		}
		if(applyId != null && !"".equals(applyId)){
			applyaudit.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyaudit.setPatientName("*" + patientName + "*");
		}
		
		if(status != null && !"".equals(status)){
			applyaudit.setStatus(status);
		}else{
			applyaudit.setStatus("01");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyaudit, request.getParameterMap());
		this.applyauditService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 会诊申请审核通过
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "present")
	@ResponseBody
	public AjaxJson present(ApplyEntity apply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(apply.getId())) {
			message = "会诊申请审核成功";
			System.out.println(apply.getId());
			try {
				String sql="UPDATE LH_APPLY SET STATUS = '20' WHERE ID = '" + apply.getId() + "'";
				systemService.executeSql(sql);
				System.out.println(sql);
				// querys.executeUpdate() 更新数量
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊申请审核失败";
			}
		} 
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除会诊申请审核
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ApplyauditEntity applyaudit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyaudit = systemService.getEntity(ApplyauditEntity.class, applyaudit.getId());
		message = "会诊申请审核删除成功";
		applyauditService.delete(applyaudit);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会诊申请审核
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ApplyauditEntity applyaudit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyaudit.getId())) {
			message = "会诊申请退回成功";
			ApplyauditEntity t = applyauditService.get(ApplyauditEntity.class, applyaudit.getId());
			try {
				t.setStatus("21");
				MyBeanUtils.copyBeanNotNull2Bean(applyaudit, t);
				applyauditService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊申请退回失败";
			}
		} else {
			message = "会诊申请审核添加成功";
			applyauditService.save(applyaudit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会诊申请审核列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ApplyauditEntity applyaudit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyaudit.getId())) {
			applyaudit = applyauditService.getEntity(ApplyauditEntity.class, applyaudit.getId());
			String PbeginDate = applyaudit.getPbeginDate();
			String PendDate = applyaudit.getPendDate();
			if(PbeginDate == null || "".equals(PbeginDate) || "0".equals(PbeginDate)){
				applyaudit.setPbeginDate("");
			}
			if(PendDate == null || "".equals(PendDate) || "0".equals(PendDate)){
				applyaudit.setPendDate("");
			}
			req.setAttribute("applyauditPage", applyaudit);
		}
		return new ModelAndView("com/lhmh/applyaudit/applyaudit");
	}
}
