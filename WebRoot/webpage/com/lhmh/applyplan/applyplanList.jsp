<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="applyplanList" title="会诊安排确认" actionUrl="applyplanController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="申请单号" field="applyId" query="true" ></t:dgCol>
   <t:dgCol title="申请日期" field="date1" query="true"></t:dgCol>
   <t:dgCol title="病人姓名" field="patientName" query="true" ></t:dgCol>
   <t:dgCol title="科室" field="officeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊科室" field="apofficeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊单位" field="apcomId" replace="${comsReplace}"></t:dgCol>
   <t:dgCol title="拟会诊时间" field="apdate1" ></t:dgCol>
   <t:dgCol title="预计开始时间" field="pbeginDate" ></t:dgCol>
   <t:dgCol title="预计结束时间" field="pendDate" ></t:dgCol>
   <t:dgCol title="状态" field="status" query="true" dictionary="apstatus"></t:dgCol>
   <t:dgToolBar title="查看" icon="icon-search" url="applyController.do?detail" onclick="openDetail()" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="会诊计划" icon="icon-edit" url="applyplanController.do?applyplan" onclick="openplan()"></t:dgToolBar>
   <t:dgToolBar title="资料查看" icon="icon-putout" url="applyController.do?datadetail" onclick="listJpeg('1')"></t:dgToolBar>
<t:dgToolBar title="打印申请" icon="icon-putout" url="applyController.do?applyprint" onclick="applyprint()"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
 <script type="text/javascript">
  	function openDetail(){
		var rowData = $("#applyplanList").datagrid("getSelected");
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
	
	function openplan(){
		var rowData = $("#applyplanList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择会诊记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再退回");
			return;
		}
		
		// 退回借用修改方法
		update("会诊安排","applyplanController.do?addorupdate","applyplanList",null,null);
	}
	
	function dataDetail(){
		var rowData = $("#applyplanList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?datadetail&id="+rowData.id);
	}
	
	function detailWord(){
		var rowData = $("#applyplanList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择生成模板的记录");
			return;
		}
		
		window.open("applyController.do?detailword&id="+rowData.id);
	}
	
	function applyprint(){
		var rowData = $("#applyplanList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择生成模板的记录");
			return;
		}
		
		window.open("applyController.do?applyprint&id="+rowData.id);
	}
	function listJpeg( type ){
		var rowData = $("#applyplanList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?fileList&id="+rowData.id + 
				"&editable=false&type=" + type  );
	}
</script>