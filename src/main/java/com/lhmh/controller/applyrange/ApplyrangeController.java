package com.lhmh.controller.applyrange;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.applyrange.ApplyrangeEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.service.applyrange.ApplyrangeServiceI;

/**   
 * @Title: Controller
 * @Description: 远程会诊
 * @author zhangdaihao
 * @date 2017-07-30 10:10:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyrangeController")
public class ApplyrangeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyrangeController.class);

	@Autowired
	private ApplyrangeServiceI applyrangeService;
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
	 * 远程会诊列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyrange")
	public ModelAndView applyrange(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		
		return new ModelAndView("com/lhmh/applyrange/applyrangeList");
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
	public void datagrid(ApplyrangeEntity applyrange,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyrangeEntity.class, dataGrid);
		
		String date1 = applyrange.getDate1();
		String applyId = applyrange.getApplyId();
		String patientName = applyrange.getPatientName();
		String status = applyrange.getStatus();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			applyrange.setDate1("*" + date1 + "*");
		}else{
			// 测试需要  暂时去掉
//			apply.setDate1(today);
		}
		if(applyId != null && !"".equals(applyId)){
			applyrange.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			applyrange.setPatientName("*" + patientName + "*");
		}
		
		if(status != null && !"".equals(status)){
			applyrange.setStatus(status);
		}else{
			applyrange.setStatus("3*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyrange, request.getParameterMap());
		this.applyrangeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 远程会诊开始
	 * 
	 * @return
	 */
	@RequestMapping(params = "beginDate")
	@ResponseBody
	public AjaxJson beginDate(ApplyrangeEntity applyrange, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyrange.getId())) {
			message = "开始";
			try {
				String id = request.getParameter("id");
				SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				String today = sdf.format(Calendar.getInstance().getTime());
				String sql="UPDATE LH_APPLY SET BEGIN_DATE = '" + today + "' , STATUS = '31' WHERE ID = '" + id + "'";
				System.out.println(sql);
				systemService.executeSql(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "失败";
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 远程会诊结束
	 * 
	 * @return
	 */
	@RequestMapping(params = "endDate")
	@ResponseBody
	public AjaxJson endDate(ApplyrangeEntity applyrange, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyrange.getId())) {
			message = "结束";
			try {
				String id = request.getParameter("id");
				SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				String today = sdf.format(Calendar.getInstance().getTime());
				String sql="UPDATE LH_APPLY SET END_DATE = '" + today + "' , STATUS = '32' WHERE ID = '" + id + "'";
				systemService.executeSql(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "失败";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加远程会诊
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ApplyrangeEntity applyrange, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(applyrange.getId())) {
			message = "结果录入更新成功";
			ApplyrangeEntity t = applyrangeService.get(ApplyrangeEntity.class, applyrange.getId());
			try {
//				t.setStatus("33");
				MyBeanUtils.copyBeanNotNull2Bean(applyrange, t);
				applyrangeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "结果录入更新失败";
			}
		} else {
			message = "远程会诊添加成功";
			applyrangeService.save(applyrange);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 远程会诊列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ApplyrangeEntity applyrange, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyrange.getId())) {
			applyrange = applyrangeService.getEntity(ApplyrangeEntity.class, applyrange.getId());
			req.setAttribute("applyrangePage", applyrange);
		}
		return new ModelAndView("com/lhmh/applyrange/applyrange");
	}
	
	/**
	 * 会诊申请列表页面跳转资料上传
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploading")
	public ModelAndView uploading(ApplyrangeEntity apply, HttpServletRequest request) {
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		
		String sql = "SELECT ROOT_ID FROM FILEROOT WHERE DEPT_ID = '"+dept.getId()+"'";
		List<String> fileId = systemService.findListbySql(sql);
		
		String id = request.getParameter("id"); 
		apply = applyrangeService.getEntity(ApplyrangeEntity.class, id);

		String sql1 = "SELECT MAX(SEQ) SEQ FROM HI_SHARE_ATTACH WHERE INFO_ID = '"+apply.getApplyId()+"'";
		List<String> seqList = systemService.findListbySql(sql1);

		int seq = 0;
		if(seqList != null && seqList.size() > 0 && seqList.get(0) != null && !"null".equals(seqList.get(0))){
			seq = Integer.parseInt(seqList.get(0)) + 1;
		}
		
		
		// 会诊申请ID
		request.setAttribute("applyId", apply.getApplyId());
		// 公司编码
		request.setAttribute("comId", apply.getComId());
		// 程序路径编码
		request.setAttribute("fileId", fileId.get(0));
		// 文件序号
		request.setAttribute("seq", seq+"");
		// 文件类型  1：申请资料  2：完成资料
		request.setAttribute("fileType", "2");
		
		return new ModelAndView("com/lhmh/apply/uploading");
	}
}
