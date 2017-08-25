package com.lhmh.controller.channel;
import java.util.ArrayList;
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

import com.lhmh.entity.channel.ChannelEntity;
import com.lhmh.entity.ops_room.Ops_roomEntity;
import com.lhmh.service.channel.ChannelServiceI;

/**   
 * @Title: Controller
 * @Description: 通道管理
 * @author zhangdaihao
 * @date 2015-08-19 13:59:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/channelController")
public class ChannelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelController.class);

	@Autowired
	private ChannelServiceI channelService;
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
	 * 通道管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channel")
	public ModelAndView channel(HttpServletRequest request) {
		List<Ops_roomEntity> OfficeList = systemService.getList(Ops_roomEntity.class);
		request.setAttribute("OfficesReplace", RoletoJson.listToReplaceStr(OfficeList, "fname", "fid"));
		return new ModelAndView("com/lhmh/channel/channelList");
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
	public void datagrid(ChannelEntity channel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channel, request.getParameterMap());
		this.channelService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除通道管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelEntity channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channel = systemService.getEntity(ChannelEntity.class, channel.getId());
		message = "通道管理删除成功";
		channelService.delete(channel);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通道管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelEntity channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channel.getId())) {
			message = "通道管理更新成功";
			ChannelEntity t = channelService.get(ChannelEntity.class, channel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channel, t);
				channelService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "通道管理更新失败";
			}
		} else {
			message = "通道管理添加成功";
			channelService.save(channel);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 通道管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelEntity channel, HttpServletRequest req) {

		List<Ops_roomEntity> officeList = new ArrayList<Ops_roomEntity>();
		String type = oConvertUtils.getString(channel.getOfficeId());
		if(!StringUtil.isEmpty(type)){
			officeList.add((Ops_roomEntity)systemService.getEntity(Ops_roomEntity.class,type));
		}else {
			officeList.addAll((List)systemService.getList(Ops_roomEntity.class));
		}
		req.setAttribute("officesList", officeList);
		if (StringUtil.isNotEmpty(channel.getId())) {
			channel = channelService.getEntity(ChannelEntity.class, channel.getId());
			req.setAttribute("channelPage", channel);
		}
		return new ModelAndView("com/lhmh/channel/channel");
	}
	/**
	 * 客户信息管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelHelp")
	public ModelAndView custHelp(HttpServletRequest request) {
		return new ModelAndView("com/lhmh/channel/channelListHelp");
	}
}
