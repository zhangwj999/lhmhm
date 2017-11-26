package com.lhmh.controller.lhdoctor;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhdoctor.LhdoctorEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.entity.pricekind.PriceKindEntity;
import com.lhmh.service.lhdoctor.LhdoctorServiceI;

/**   
 * @Title: Controller
 * @Description: 专家库管理
 * @author zhangdaihao
 * @date 2017-07-18 20:17:01
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lhdoctorController")
public class LhdoctorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LhdoctorController.class);

	@Autowired
	private LhdoctorServiceI lhdoctorService;
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
	 * 专家库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lhdoctor")
	public ModelAndView lhdoctor(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		
		List<PriceKindEntity> priceList = systemService.getList(PriceKindEntity.class);
		request.setAttribute("payKindReplace", RoletoJson.listToReplaceStr(priceList, "priceName", "id"));
		return new ModelAndView("com/lhmh/lhdoctor/lhdoctorList");
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
	public void datagrid(LhdoctorEntity lhdoctor,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LhdoctorEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhdoctor, request.getParameterMap());
		this.lhdoctorService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除专家库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LhdoctorEntity lhdoctor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lhdoctor = systemService.getEntity(LhdoctorEntity.class, lhdoctor.getId());
		message = "专家库管理删除成功";
		lhdoctorService.delete(lhdoctor);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加专家库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LhdoctorEntity lhdoctor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(lhdoctor.getId())) {
			message = "专家库管理更新成功";
			LhdoctorEntity t = lhdoctorService.get(LhdoctorEntity.class, lhdoctor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(lhdoctor, t);
				lhdoctorService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "专家库管理更新失败";
			}
		} else {
			message = "专家库管理添加成功";
			lhdoctorService.save(lhdoctor);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 专家库管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LhdoctorEntity lhdoctor, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lhdoctor.getId())) {
			lhdoctor = lhdoctorService.getEntity(LhdoctorEntity.class, lhdoctor.getId());
			req.setAttribute("lhdoctorPage", lhdoctor);
		}
		List<LhcomEntity> comsList = systemService.getList(LhcomEntity.class);
		req.setAttribute("comsList", comsList);
		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		req.setAttribute("officeList", officeList);

		return new ModelAndView("com/lhmh/lhdoctor/lhdoctor");
	}
}
