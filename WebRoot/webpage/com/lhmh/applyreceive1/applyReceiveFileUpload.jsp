<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="applyreceivefileuploadList" title="会诊资料上传" actionUrl="applyReceiveController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="申请单号" field="applyId" query="true" ></t:dgCol>
   <t:dgCol title="申请日期" field="date1"></t:dgCol>
   <t:dgCol title="病人姓名" field="patientName" query="true" ></t:dgCol>
   <t:dgCol title="科室" field="officeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊科室" field="apofficeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊单位" field="apcomId" replace="${comsReplace}"></t:dgCol>
   <t:dgCol title="拟会诊时间" field="apdate1" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="预计开始时间" field="pbeginDate" ></t:dgCol>
   <t:dgCol title="预计结束时间" field="pendDate" ></t:dgCol>
   <t:dgCol title="开始时间" field="beginDate" ></t:dgCol>
   <t:dgCol title="结束时间" field="endDate" ></t:dgCol>
   <t:dgCol title="状态" field="status" query="true" dictionary="apstatus"></t:dgCol>
   <t:dgCol title="报告状态" field="reportStatus" query="true" dictionary="reportstat"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgConfOpt title="报告完成" exp="reportStatus#eq#1" url="applyController.do?receiveDone&id={id}" message="确定结束吗？"/>
<%--    <t:dgFunOpt title="报告完成" exp="status#eq#30" funname="begin"></t:dgFunOpt> --%>
<%--    <t:dgFunOpt title="接收" exp="status#eq#31" funname="begin"></t:dgFunOpt> --%>
<%--    <t:dgConfOpt title="开始" exp="status#eq#30" url="applyrangeController.do?beginDate&id={id}" message="确定开始吗？"/> --%>
<%--    <t:dgConfOpt title="结束" exp="status#eq#31" url="applyrangeController.do?endDate&id={id}" message="确定结束吗？"/> --%>
<%--    <t:dgToolBar title="查看" icon="icon-search" url="applyController.do?detail" onclick="openDetail()" funname="detail"></t:dgToolBar> --%>
   <t:dgToolBar title="会诊报告录入" icon="icon-edit" url="applyrangeController.do?addorupdate" onclick="openreport()"></t:dgToolBar>
   <t:dgToolBar title="拍照直接上传" icon="icon-putout" url="applyController.do?videoCap" onclick="videoCap('2')"></t:dgToolBar>
   <t:dgToolBar title="选择文件上传" icon="icon-putout" url="applyrangeController.do?uploading" onclick="addListJpeg()"></t:dgToolBar>
   <t:dgToolBar title="资料查看" icon="icon-putout" url="applyController.do?datadetail" onclick="listJpeg('1')"></t:dgToolBar>
<t:dgToolBar title="打印申请" icon="icon-putout" url="applyController.do?applyprint" onclick="applyprint()"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
  	function openDetail(){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再查看");
			return;
		}
		
		window.open("applyController.do?detail&id="+rowData.id);
	}
	
	function openreport(){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择录入记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再录入");
			return;
		}
		
		// 借用修改方法
		update("会诊报告","applyrangeController.do?addorupdate","applyrangeList",null,null);
	}
	
	function dataDetail(){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?datadetail&id="+rowData.id);
	}
	
	function detailWord(){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择打印申请的记录");
			return;
		}
		
		window.open("applyController.do?detailword&id="+rowData.id);
	}
	function applyprint(){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择打印申请的记录");
			return;
		}
		
		window.open("applyController.do?applyprint&id="+rowData.id);
	}
	function listJpeg( type ){
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		// 申请资料看明细，完成资料可上传
		window.open("applyController.do?fileList&id="+rowData.id + 
				"&editable=" + ( type == '1' ? false : true ) + "&type=" + type  );
	}
	function addListJpeg(){
		
		var rowData = $("#applyreceiveList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		var status = rowData.status;
		if(status != "32"){
			tip("请选择会诊结束状态记录再上传资料");
			return;
		}
		
		listJpeg( '2' )
	}
	function videoCap( type ){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择拍照上传资料的记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再拍照上传资料");
			return;
		}
		
		var status = rowData.status;
		if(status != "32" && status != "33"){
			tip("请选择结束状态的记录再拍照上传资料");
			return;
		}
		
		window.open("applyController.do?videoCap&id="+rowData.id + "&type=" + type );
	}
	function begin(){
		var rowData = $("#applyreceivefileuploadList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择要开始的记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再开始");
			return;
		}
		var url = "applyReceiveController.do?receiveDone&id="+rowData.id
		$.ajax({
			url: url,
			method: 'GET',
			dataType: 'json'
		}).done( function( data ){
			$("#applyreceiveList").datagrid("reload");
		}).fail( function(){
			$.messager.error( '修改申请单状态失败！' )
		})
	}
	$(function(){
		$('#applyreceivefileuploadListtb').find( 'input[name=apdate1_begin]' ).val( '${date1}' )
	})
</script>