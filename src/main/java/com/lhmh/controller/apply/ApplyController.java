package com.lhmh.controller.apply;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
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
import org.jeecgframework.web.demo.entity.test.WebOfficeEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.doctor.DoctorEntity;
import com.lhmh.entity.filepathsave.FilepathsaveEntity;
import com.lhmh.entity.hishareattach.HiShareAttachEntity;
import com.lhmh.entity.lhcom.LhcomEntity;
import com.lhmh.entity.lhdoctor.LhdoctorEntity;
import com.lhmh.entity.lhoffice.LhOfficeEntity;
import com.lhmh.entity.lhpatieninfo.LhPatieninfoEntity;
import com.lhmh.pub.PubTool;
import com.lhmh.service.apply.ApplyServiceI;
import com.lhmh.service.filepathsave.FilepathsaveServiceI;

/**   
 * @Title: Controller
 * @Description: 会诊申请
 * @author zhangdaihao
 * @date 2017-06-23 19:17:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyController")
public class ApplyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyController.class);

	@Autowired
	private ApplyServiceI applyService;
	@Autowired
	private FilepathsaveServiceI filepathsaveService;
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
	 * 会诊申请列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "apply")
	public ModelAndView apply(HttpServletRequest request) {
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		request.setAttribute("comsReplace", RoletoJson.listToReplaceStr(comList, "comName", "comId"));

		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		request.setAttribute("officesReplace", RoletoJson.listToReplaceStr(officeList, "officeName", "officeId"));
		return new ModelAndView("com/lhmh/apply/applyList");
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
	public void datagrid(ApplyEntity apply,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyEntity.class, dataGrid);
		String date1 = apply.getDate1();
		String applyId = apply.getApplyId();
		String patientName = apply.getPatientName();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(Calendar.getInstance().getTime());
		
		if(date1 != null && !"".equals(date1)){
			apply.setDate1("*" + date1 + "*");
		}else{
			// 测试需要  暂时去掉
//			apply.setDate1(today);
		}
		if(applyId != null && !"".equals(applyId)){
			apply.setApplyId("*" + applyId + "*");
		}
		if(patientName != null && !"".equals(patientName)){
			apply.setPatientName("*" + patientName + "*");
		}
		
		// 登录人
		TSUser user = ResourceUtil.getSessionUserName();
		apply.setCrtUser(user.getUserName());
					
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, apply, request.getParameterMap());
		this.applyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会诊申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ApplyEntity apply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(apply.getId())) {
			message = "会诊申请删除成功";
			try {
				String sql="UPDATE LH_APPLY SET STATUS = '80' WHERE ID = '" + apply.getId() + "'";
				systemService.executeSql(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊申请删除失败";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 提交会诊申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "present")
	@ResponseBody
	public AjaxJson present(ApplyEntity apply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(apply.getId())) {
			message = "会诊申请提交成功";
			System.out.println(apply.getId());
			try {
				String sql="UPDATE LH_APPLY SET STATUS = '01' WHERE ID = '" + apply.getId() + "'";
				systemService.executeSql(sql);
				// querys.executeUpdate() 更新数量
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会诊申请提交失败";
			}
		} 
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加会诊申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	public ModelAndView save(ApplyEntity apply, HttpServletRequest request) {
		
		if (StringUtil.isNotEmpty(apply.getId())) {
			ApplyEntity t = applyService.get(ApplyEntity.class, apply.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(apply, t);
				applyService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
			
			// 日期
			String today = sdf.format(Calendar.getInstance().getTime());
			// 主键
			String applyId  = getMaxId(today);
			// 登录人
			TSUser user = ResourceUtil.getSessionUserName();
//			ResourceUtil.
			apply.setApplyId(applyId);
			apply.setStatus("00");
			apply.setDate1(today);
			apply.setCrtUser(user.getUserName());
			apply.setCrtName(user.getRealName());
			applyService.save(apply);
		}
		apply = applyService.getEntity(ApplyEntity.class, apply.getId());
		request.setAttribute("applyPage", apply);
		return new ModelAndView("com/lhmh/apply/applyupdate");
	}

	/**
	 * 提交会诊申请并保存
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savepresent")
	public ModelAndView savepresent(ApplyEntity apply, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(apply.getId())) {
			ApplyEntity t = applyService.get(ApplyEntity.class, apply.getId());
			try {
				t.setStatus("01");
				MyBeanUtils.copyBeanNotNull2Bean(apply, t);
				applyService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
			
			// 日期
			String today = sdf.format(Calendar.getInstance().getTime());
			// 主键
			String applyId  = getMaxId(today);
			// 登录人
			TSUser user = ResourceUtil.getSessionUserName();
			apply.setApplyId(applyId);
			apply.setStatus("01");
			apply.setDate1(today);
			apply.setCrtUser(user.getUserName());
			apply.setCrtName(user.getRealName());
			applyService.save(apply);
		}
		
		apply = applyService.getEntity(ApplyEntity.class, apply.getId());
		req.setAttribute("applyPage", apply);
		
		return new ModelAndView("com/lhmh/apply/applydetail");
	}



	/**
	 * 会诊申请列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "add")
	public ModelAndView add(ApplyEntity apply, HttpServletRequest req) {
		
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		// 上级部门
		TSDepart parentdept = dept.getTSPDepart();
		if (StringUtil.isNotEmpty(apply.getId())) {
			apply = applyService.getEntity(ApplyEntity.class, apply.getId());
		}
		if(parentdept.getId() != null){
			apply.setComId(parentdept.getId());
		}
		req.setAttribute("applyPage", apply);
		return new ModelAndView("com/lhmh/apply/applyadd");
	}

	/**
	 * 会诊申请列表页面跳转修改
	 * 
	 * @return
	 */
	@RequestMapping(params = "update")
	public ModelAndView update(ApplyEntity apply, HttpServletRequest req) {
		
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		req.setAttribute("comList", comList);
		
		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		req.setAttribute("officeList", officeList);
		
		if (StringUtil.isNotEmpty(apply.getId())) {
			apply = applyService.getEntity(ApplyEntity.class, apply.getId());
			req.setAttribute("applyPage", apply);
		}
		return new ModelAndView("com/lhmh/apply/applyupdate");
	}

	/**
	 * 会诊申请列表页面跳转明细
	 * 
	 * @return
	 */
	@RequestMapping(params = "detail")
	public ModelAndView detail(ApplyEntity apply, HttpServletRequest req) {
		
		List<LhcomEntity> comList = systemService.getList(LhcomEntity.class);
		req.setAttribute("comList", comList);
		
		List<LhOfficeEntity> officeList = systemService.getList(LhOfficeEntity.class);
		req.setAttribute("officeList", officeList);
		
		if (StringUtil.isNotEmpty(apply.getId())) {
			apply = applyService.getEntity(ApplyEntity.class, apply.getId());
			req.setAttribute("applyPage", apply);
		}
		return new ModelAndView("com/lhmh/apply/applydetail");
	}

	/**
	 * 资料查看明细页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "datadetail")
	public ModelAndView datadetail(ApplyEntity apply, HttpServletRequest req) {

		apply = applyService.getEntity(ApplyEntity.class, apply.getId());
		String sql = "SELECT COM_ID,INFO_ID,SEQ,FILE_TYPE,FILE_DOC_ID,FILE_NAME,IS_MRB,ID FROM HI_SHARE_ATTACH WHERE INFO_ID='" + apply.getApplyId() + "' AND IS_MRB = '1'";
		
		SQLQuery query = systemService.getSession().createSQLQuery(sql);
		// 根据实体类名和数据名称与值 获取实体List
//		List<FilepathsaveEntity> filepathList = systemService.findByProperty(FilepathsaveEntity.class,"infoId",apply.getApplyId());
		List<Object> filepathList = query.list();
		List<Map> filepathPage = new ArrayList<Map>();
		if (filepathList != null && filepathList.size() > 0){
			for (int i = 0; i < filepathList.size(); i++) {
				Object[] temp = (Object[]) filepathList.get(i);
				Map map = new HashMap();
				map.put("COM_ID", (String)temp[0]);
				map.put("INFO_ID", (String)temp[1]);
				map.put("SEQ", (Integer)(temp[2]));
				
				// 类型转换
				String type =  (String)temp[3];
				if("1".equals(type)){
					map.put("FILE_TYPE", "申请资料");
				}else{
					map.put("FILE_TYPE", "会诊报告");
				}
				
				map.put("FILE_DOC_ID", (String)temp[4]);
				map.put("FILE_NAME", (String)temp[5]);
				map.put("IS_MRB", (String)temp[6]);
				map.put("ID", (String)temp[7]);
				filepathPage.add(map);
			}
		}
		req.setAttribute("filepathPage", filepathPage);
		req.setAttribute("status", apply.getStatus());
		return new ModelAndView("com/lhmh/apply/filedetail");
	}
	
	/**
	 * 资料查看明细页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "fileList")
	public ModelAndView fileList(ApplyEntity apply, HttpServletRequest req) {
		apply = applyService.getEntity(ApplyEntity.class, apply.getId());
		req.setAttribute( "applyId", apply.getApplyId() );
		String editable = req.getParameter( "editable" );
		if( "true".equals( editable ) ){
			return new ModelAndView("com/lhmh/apply/filelist_editable");
		}else{
			return new ModelAndView("com/lhmh/apply/filelist");
		}
	}
	
	/**
	 * 删除文件路径
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "deletefile")
	public ModelAndView deletefile(HttpServletRequest req) {

		String id = req.getParameter("id");
		
		// 删除就是更新路径记录 IS_MRB 字段为 0
		String sql="UPDATE HI_SHARE_ATTACH SET IS_MRB = '0' WHERE ID = '" + id + "'";
		systemService.executeSql(sql);
		
		// 查询会诊单状态
		String sql1 = "SELECT STATUS FROM LH_APPLY WHERE APPLY_ID = (SELECT INFO_ID FROM HI_SHARE_ATTACH WHERE ID = '" + id + "')";
		List<String> statusList = systemService.findListbySql(sql1);		
		
		// 初始化查询文件列表
		String querysql = "SELECT COM_ID,INFO_ID,SEQ,FILE_TYPE,FILE_DOC_ID,FILE_NAME,IS_MRB,ID FROM HI_SHARE_ATTACH WHERE IS_MRB = '1' AND INFO_ID = ( "
				+ "SELECT INFO_ID FROM HI_SHARE_ATTACH WHERE ID = '" + id + "')";

		SQLQuery query = systemService.getSession().createSQLQuery(querysql);
		List<Object> filepathList = query.list();
		List<Map<String,String>> filepathPage = new ArrayList<Map<String,String>>();
		if (filepathList != null && filepathList.size() > 0){
			for (int i = 0; i < filepathList.size(); i++) {
				Object[] temp = (Object[]) filepathList.get(i);
				Map map = new HashMap();
				map.put("COM_ID", (String)temp[0]);
				map.put("INFO_ID", (String)temp[1]);
				map.put("SEQ", (Integer)temp[2]);
				
				String type =  (String)temp[3];
				if("1".equals(type)){
					map.put("FILE_TYPE", "申请资料");
				}else{
					map.put("FILE_TYPE", "会诊报告");
				}
				
				map.put("FILE_DOC_ID", (String)temp[4]);
				map.put("FILE_NAME", (String)temp[5]);
				map.put("IS_MRB", (String)temp[6]);
				map.put("ID", (String)temp[7]);
				filepathPage.add(map);
			}
		}
		req.setAttribute("filepathPage", filepathPage);
		req.setAttribute("msg", "文件删除成功");
		req.setAttribute("status", statusList.get(0));
		return new ModelAndView("com/lhmh/apply/filedetail");
	}
	
	/**
	 * 会诊申请列表页面跳转资料上传
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploading")
	public ModelAndView uploading(ApplyEntity apply, HttpServletRequest request) {
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		
		String sql = "SELECT ROOT_ID FROM FILEROOT WHERE DEPT_ID = '"+dept.getId()+"'";
		List<String> fileId = systemService.findListbySql(sql);
		
		String id = request.getParameter("id"); 
		apply = applyService.getEntity(ApplyEntity.class, id);

		String sql1 = "SELECT MAX(SEQ) SEQ FROM HI_SHARE_ATTACH WHERE INFO_ID = '"+apply.getApplyId()+"'";
		List seqList = systemService.findListbySql(sql1);

		int seq = 0;
		if(seqList != null && seqList.size() > 0 && seqList.get(0) != null && !"null".equals(seqList.get(0))){
			seq = (Integer)(seqList.get(0)) + 1;
		}
		
		
		// 会诊申请ID
		request.setAttribute("applyId", apply.getApplyId());
		// 公司编码
		request.setAttribute("comId", apply.getComId());
		// 程序路径编码
		request.setAttribute("fileId", fileId.get(0));
		// 文件序号
		request.setAttribute("seq", seq+"");
		// 文件类型	1：申请资料  2：完成资料
		request.setAttribute("fileType", "1");
		
		return new ModelAndView("com/lhmh/apply/uploading");
	}
	
	/**
	 * 会诊申请列表页面跳转拍照资料上传
	 * 
	 * @return
	 */
	@RequestMapping(params = "videoCap")
	public ModelAndView videoCap( ApplyEntity apply, HttpServletRequest request ) {
		String id = request.getParameter("id"); 
		apply = applyService.getEntity(ApplyEntity.class, id);
		System.out.println( "apply.getApplyId() = " + apply.getApplyId() );
		request.setAttribute( "applyId", apply.getApplyId() );
		
		return new ModelAndView("com/lhmh/apply/videocap");
	}
	
	/**
	 * 会诊申请列表页面跳转拍照资料上传接收图片
	 * @return
	 */
	@RequestMapping( params = "upLoadJpeg" )
	@ResponseBody
	public AjaxJson upLoadJpeg( HttpServletRequest request, HttpServletResponse response ) {
		
		AjaxJson j = new AjaxJson();
		try {
			String applyId = request.getParameter( "applyId" );
			String path = request.getParameter( "path" );
			String fileName = request.getParameter( "fileName" );
			HiShareAttachEntity attach = PubTool.saveAttachEntity( applyId, path, fileName, systemService );
			j.setObj( attach );
			j.setMsg( "上传图片成功" );
		} catch ( Exception e ) {
			j.setSuccess( false );
			j.setMsg( e.getMessage() );
			e.printStackTrace();
		}
		return j;
	}
	
	
	/**
	 * 删除图片
	 * @return
	 */
	@RequestMapping( params = "deleteJpeg" )
	@ResponseBody
	public AjaxJson deleteJpeg( HttpServletRequest request, HttpServletResponse response ) {
		
		AjaxJson j = new AjaxJson();
		try {
			String id = request.getParameter( "id" );
			PubTool.delAttachById( id, systemService );
			j.setMsg( "删除图片成功" );
		} catch ( Exception e ) {
			j.setSuccess( false );
			j.setMsg( e.getMessage() );
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 会诊申请列表页面跳转拍照资料上传接收图片
	 * @return
	 */
	@RequestMapping( params = "listJpeg" )
	@ResponseBody
	public AjaxJson listJpeg( HttpServletRequest request, HttpServletResponse response ) {
		
		AjaxJson j = new AjaxJson();
		try {
			String applyId = request.getParameter( "applyId" );
			List rltList = PubTool.listAttachByApplyId( applyId, systemService );
			request.setAttribute( "applyId", applyId );
			j.setObj( rltList );
			j.setMsg( "上传图片成功" );
		} catch ( Exception e ) {
			j.setSuccess( false );
			j.setMsg( e.getMessage() );
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 生成模板
	 * 
	 * @return
	 */
	@RequestMapping(params = "detailword")
	public ModelAndView detailword(ApplyEntity apply, HttpServletRequest req) {

		apply = applyService.getEntity(ApplyEntity.class, apply.getId());

		String sql = "SELECT COM_NAME FROM LH_COM WHERE COM_ID = '"+apply.getComId()+"'";
		List<String> comName = systemService.findListbySql(sql);
		if(comName != null && comName.size() > 0){
			apply.setComId(comName.get(0));
		}else{
			apply.setComId("");
		}
		
		String sql1 = "SELECT OFFICE_NAME FROM LH_OFFICE WHERE OFFICE_ID = '"+apply.getOfficeId()+"'";
		List<String> officeName = systemService.findListbySql(sql1);
		if(officeName != null && officeName.size() > 0){
			apply.setOfficeId(officeName.get(0));
		}else{
			apply.setOfficeId("");
		}

		String sql2 = "SELECT ROOM_NAME FROM LH_MEETROOM WHERE ROOM_ID = '"+apply.getRoomId()+"'";
		List<String> roomName = systemService.findListbySql(sql2);
		if(roomName != null && roomName.size() > 0){
			apply.setRoomId(roomName.get(0));
		}else{
			apply.setRoomId("");
		}
		
		String sql3 = "SELECT OFFICE_NAME FROM LH_OFFICE WHERE OFFICE_ID = '"+apply.getApofficeId()+"'";
		List<String> ApofficeName = systemService.findListbySql(sql3);
		if(ApofficeName != null && ApofficeName.size() > 0){
			apply.setApofficeId(ApofficeName.get(0));
		}else{
			apply.setApofficeId("");
		}

		String sql4 = "SELECT TYPENAME FROM T_S_TYPE WHERE TYPEGROUPID = '402880ee5db14625015db147eee40001' AND TYPECODE = '"+apply.getReturnResion()+"'";
		List<String> ReturnResion = systemService.findListbySql(sql4);
		if(ReturnResion != null && ReturnResion.size() > 0){
			apply.setReturnResion(ReturnResion.get(0));
		}else{
			apply.setReturnResion("");
		}
		
		String sql5 = "SELECT COM_NAME FROM LH_COM WHERE COM_ID = '"+apply.getApcomId()+"'";
		List<String> apcomName = systemService.findListbySql(sql5);
		if(apcomName != null && apcomName.size() > 0){
			apply.setApcomId(apcomName.get(0));
		}else{
			apply.setApcomId("");
		}
		
		req.setAttribute("applyPage", apply);
		return new ModelAndView("com/lhmh/apply/detailword");
	}
	
	/**
	 * 病人选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "patients")
	public String patients() {
		return "com/lhmh/apply/patients";
	}
	
	/**
	 * 病人显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridPatients")
	public void datagridPatients(LhPatieninfoEntity patieninfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		// 上级部门
		TSDepart parentdept = dept.getTSPDepart();
		if(parentdept.getId() != null){
			patieninfo.setComId(parentdept.getId());
		}
		
		String id = patieninfo.getFId();
		String name = patieninfo.getFName();
		
		if(id != null && !"".equals(id)){
			patieninfo.setFId("*" + id + "*");
		}

		if(name != null && !"".equals(name)){
			patieninfo.setFName("*" + name + "*");
		}
		
		CriteriaQuery cq = new CriteriaQuery(LhPatieninfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, patieninfo, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 科主任选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "managers")
	public String managers() {
		return "com/lhmh/apply/managers";
	}
	
	/**
	 * 科主任显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridManagers")
	public void datagridManagers(DoctorEntity doctor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String id = doctor.getId();
		String name = doctor.getFname();
		
		if(id != null && !"".equals(id)){
			doctor.setId("*" + id + "*");
		}

		if(name != null && !"".equals(name)){
			doctor.setFname("*" + name + "*");
		}
		
		doctor.setFtype("10");
		
		CriteriaQuery cq = new CriteriaQuery(DoctorEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, doctor, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 拟请专家选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "doctors")
	public String doctors(ApplyEntity apply, HttpServletRequest request, HttpServletResponse response) {
		String apcomId = apply.getApcomId();
		System.out.println(apcomId);
		System.out.println(apply.toString());
		request.setAttribute("apcomId", request.getParameter("apcomId"));
		return "com/lhmh/apply/doctors";
	}
	
	/**
	 * 拟请专家显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridDoctors")
	public void datagridDoctors(LhdoctorEntity lhdoctor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String id = lhdoctor.getDoctorId();
		String name = lhdoctor.getDoctorName();
		
		if(id != null && !"".equals(id)){
			lhdoctor.setDoctorId("*" + id + "*");
		}

		if(name != null && !"".equals(name)){
			lhdoctor.setDoctorName("*" + name + "*");
		}
		
		String apcomId = request.getParameter("apcomId");
		lhdoctor.setComId(apcomId);
		
		CriteriaQuery cq = new CriteriaQuery(LhdoctorEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lhdoctor, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除会诊申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "changeOffice")
	@ResponseBody
	public AjaxJson changeOffice(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String comId = request.getParameter("comId");
		String sql = "SELECT OFFICE_ID,OFFICE_NAME FROM LH_OFFICE WHERE COM_ID = '"+comId+"'";
		SQLQuery query = systemService.getSession().createSQLQuery(sql);
		List list = query.list();
		StringBuffer sb = new StringBuffer();
		sb.append("<select name=\"officeId\">");
		if (list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Object[] temp = (Object[]) list.get(i);
				sb.append("<option value=\"").append(temp[0].toString()).append("\">").append(temp[1].toString()).append("</option>");
			}
		}
		sb.append("</select>");
		j.setMsg(sb.toString());
		return j;
	}
	
	/**
	 * 保存资料文件路径
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveFile")
	public ModelAndView saveFile(FilepathsaveEntity filepathsave, HttpServletRequest request) {
		
		filepathsaveService.save(filepathsave);
		
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
				
		String sql = "SELECT ROOT_ID FROM FILEROOT WHERE DEPT_ID = '"+dept.getId()+"'";
		List<String> fileId = systemService.findListbySql(sql);
				
		String sql1 = "SELECT MAX(SEQ) SEQ FROM HI_SHARE_ATTACH WHERE INFO_ID = '"+filepathsave.getInfoId()+"'";
		List<String> seqList = systemService.findListbySql(sql1);

		String sql2 = "SELECT COM_ID FROM LH_APPLY WHERE APPLY_ID = '"+filepathsave.getInfoId()+"'";
		List<String> comList = systemService.findListbySql(sql2);
		
		int seq = 0;
		if(seqList != null && seqList.size() > 0 && seqList.get(0) != null && !"null".equals(seqList.get(0))){
			seq = Integer.parseInt(seqList.get(0)) + 1;
		}

		// 会诊申请ID
		request.setAttribute("applyId", filepathsave.getInfoId());
		// 公司编码
		request.setAttribute("comId", comList.get(0));
		// 程序路径编码
		request.setAttribute("fileId", fileId.get(0));
		// 文件序号
		request.setAttribute("seq", seq+"");
		// 文件类型	1：申请资料  2：完成资料
		request.setAttribute("fileType", filepathsave.getFileType());
		// 提示信息
		request.setAttribute("msg", "资料上传成功");
		
		return new ModelAndView("com/lhmh/apply/uploading");
	}
	
	@RequestMapping(params = "download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		// 文件的默认保存名
		String fileName = request.getParameter("name");
		// 绝对路径
		String url = request.getParameter("url");
		// 读到流中
		InputStream inStream = new FileInputStream(url);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "downloadById")
	public void downloadById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 文件的主键
		String id = request.getParameter("id");
		
		List<HiShareAttachEntity> attachs = systemService.findByProperty( HiShareAttachEntity.class,
				"id", id );
		
		HiShareAttachEntity attach = attachs.get( 0 );
		
		String fileName = attach.getFileName();
		// 绝对路径
		String url = attach.getFileDocId() + fileName;
		// 读到流中
		InputStream inStream = new FileInputStream(url);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode( fileName, "UTF-8" ) + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 最大号为１０位，前６位为日期格式，后４位为顺序号 当前传入系统登录日期，银行编号先不传
	 * 如果当日已月201506260001的记录，本方法返回值加１，为201506260002 如果当日无记录，则返回201506260001
	 */
	public String getMaxId(String date1) {
		String tableId = "";
		String sql = "SELECT MAX(APPLY_ID) APPLY_ID  FROM LH_APPLY WHERE APPLY_ID LIKE'" + date1 + "%' ";
		List<String> roles = systemService.findListbySql(sql);
		if (roles != null) {
			if (roles.get(0) == null || roles.get(0) == "null") {
				tableId = "1";
			} else {
				tableId = roles.get(0).substring(8,12);
				tableId = (Integer.parseInt(tableId) + 1) + "";
			}
		} else {
			tableId = "1";
		}
		String result = date1 + String.format("%1$4s", tableId);
		result = result.replace(" ", "0");
		return result;
	}
	
	/**
	 * WebOffice例子列表页面跳转
	 * @return
	 */
	@RequestMapping(params = "webOffice")
	public ModelAndView webOffice( ApplyEntity apply, HttpServletRequest req ) {
		if ( StringUtil.isNotEmpty( apply.getId() ) ) {
		}
		req.setAttribute( "fileType", "doc" );//doc,xls,ppt,wps
		req.setAttribute( "docId", "yesihava" );//麻蛋没鸟用啊
		
		return new ModelAndView("com/lhmh/apply/webOffice");
	}
	
	@RequestMapping(params = "getDoc")
	public void getDoc(HttpServletRequest request, Integer fileId, HttpServletResponse response) {
		// 从数据库取得数据
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( ( "会诊单明细.doc" ).getBytes("GBK"), "ISO8859-1"));
			//从数据库中读取出来	, 输出给下载用
			HttpServletRequest req = ( ( HttpServletRequest ) request );
			InputStream bis = new FileInputStream( req.getRealPath( "/" ) + 
					"export/template/apply_template.docx" );
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			long lTotalLen = 0;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
				lTotalLen += bytesRead;
			}
			response.setHeader("Content-Length", String.valueOf(lTotalLen));
			bis.close();
			bos.flush();
			bos.close();
		} catch (Exception  e){
			e.printStackTrace();
		}
	}
}
