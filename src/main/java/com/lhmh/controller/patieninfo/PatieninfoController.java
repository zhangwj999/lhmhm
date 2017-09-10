package com.lhmh.controller.patieninfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.office.OfficeEntity;
import com.lhmh.entity.patieninfo.PatieninfoEntity;
import com.lhmh.service.patieninfo.PatieninfoServiceI;

/**   
 * @Title: Controller
 * @Description: 病人信息
 * @author zhangdaihao
 * @date 2015-08-15 18:01:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/patieninfoController")
public class PatieninfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PatieninfoController.class);

	@Autowired
	private PatieninfoServiceI patieninfoService;
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
	 * 病人信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "patieninfo")
	public ModelAndView patieninfo(HttpServletRequest request) {
		List<OfficeEntity> OfficeList = systemService.getList(OfficeEntity.class);
		request.setAttribute("OfficesReplace", RoletoJson.listToReplaceStr(OfficeList, "fname", "fbh"));		
		return new ModelAndView("com/lhmh/patieninfo/patieninfoList");
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
	public void datagrid(PatieninfoEntity patieninfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PatieninfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, patieninfo, request.getParameterMap());
		this.patieninfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除病人信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PatieninfoEntity patieninfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		patieninfo = systemService.getEntity(PatieninfoEntity.class, patieninfo.getId());
		message = "病人信息删除成功";
		patieninfoService.delete(patieninfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加病人信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PatieninfoEntity patieninfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		// 上级部门
		TSDepart parentdept = dept.getTSPDepart();

		if (StringUtil.isNotEmpty(patieninfo.getId())) {
			message = "病人信息更新成功";
			PatieninfoEntity t = patieninfoService.get(PatieninfoEntity.class, patieninfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(patieninfo, t);
				if(parentdept.getId() != null){
					t.setComId( parentdept.getId() );
				}
				patieninfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "病人信息更新失败";
			}
		} else {
			message = "病人信息添加成功";
			if(parentdept.getId() != null){
				patieninfo.setComId( parentdept.getId() );
			}
			patieninfoService.save(patieninfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 病人信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PatieninfoEntity patieninfo, HttpServletRequest req) {

		List<OfficeEntity> officeList = new ArrayList<OfficeEntity>();
		String type = oConvertUtils.getString(patieninfo.getFtype());
		if(!StringUtil.isEmpty(type)){
			officeList.add((OfficeEntity)systemService.getEntity(OfficeEntity.class,type));
		}else {
			officeList.addAll((List)systemService.getList(OfficeEntity.class));
		}
		req.setAttribute("officesList", officeList);
		if (StringUtil.isNotEmpty(patieninfo.getId())) {
			patieninfo = patieninfoService.getEntity(PatieninfoEntity.class, patieninfo.getId());

		}else{
			SimpleDateFormat formatter; 
		    formatter = new SimpleDateFormat ("yyyyMMdd"); 
			//增加业务流水号
			if(null==patieninfo.getFid()||"".equals(patieninfo.getFid())){
				patieninfo.setFid(getBillMaxId("", formatter.format(Calendar.getInstance().getTime())));
				System.out.println(patieninfo.getFid());
			}
		}
		req.setAttribute("patieninfoPage", patieninfo);
		return new ModelAndView("com/lhmh/patieninfo/patieninfo");
	}
	/*
	 * 最大号为１０位，前６位为日期格式，后４位为顺序号 当前传入系统登录日期，银行编号先不传
	 * 如果当日已月201506260001的记录，本方法返回值加１，为201506260002 如果当日无记录，则返回201506260001
	 */
	public String getBillMaxId(String bankId, String date1) {
		String tableId = "";
		String sql = "select max(f_id) fid  from patieninfo where f_id like'"
				+ date1 + "%' ";

		List<String> roles = systemService.findListbySql(sql);
		if (roles != null) {
			if (roles.get(0) == null || roles.get(0) == "null") {
				tableId = "1";
			} else {
				tableId = roles.get(0).substring(8);
				tableId = (Integer.parseInt(tableId) + 1) + "";
			}
		} else {
			tableId = "1";
		}
		String result = date1 + String.format("%1$4s", tableId);
		result = result.replace(" ", "0");
		return result;
	}
}
