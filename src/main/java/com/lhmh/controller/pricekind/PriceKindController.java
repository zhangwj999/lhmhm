package com.lhmh.controller.pricekind;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lhmh.entity.pricekind.PriceKindEntity;
import com.lhmh.service.pricekind.PriceKindServiceI;

/**   
 * @Title: Controller
 * @Description: 收费标准
 * @author zhangdaihao
 * @date 2017-11-26 10:42:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/priceKindController")
public class PriceKindController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PriceKindController.class);

	@Autowired
	private PriceKindServiceI priceKindService;
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
	 * 收费标准列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "priceKind")
	public ModelAndView priceKind(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/pricekind/priceKindList");
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
	public void datagrid(PriceKindEntity priceKind,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PriceKindEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, priceKind, request.getParameterMap());
		this.priceKindService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收费标准
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PriceKindEntity priceKind, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		priceKind = systemService.getEntity(PriceKindEntity.class, priceKind.getId());
		message = "收费标准删除成功";
		priceKindService.delete(priceKind);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收费标准
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PriceKindEntity priceKind, HttpServletRequest request) {
		
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		// 上级部门
		TSDepart parentdept = dept.getTSPDepart();
		if(parentdept.getId() != null){
			priceKind.setComId( parentdept.getId() );
		}
		
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(priceKind.getId())) {
			message = "收费标准更新成功";
			PriceKindEntity t = priceKindService.get(PriceKindEntity.class, priceKind.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(priceKind, t);
				priceKindService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "收费标准更新失败";
			}
		} else {
			message = "收费标准添加成功";
			priceKindService.save(priceKind);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 收费标准列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PriceKindEntity priceKind, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(priceKind.getId())) {
			priceKind = priceKindService.getEntity(PriceKindEntity.class, priceKind.getId());
			req.setAttribute("priceKindPage", priceKind);
		}
		return new ModelAndView("com/lhmh/pricekind/priceKind");
	}
}
