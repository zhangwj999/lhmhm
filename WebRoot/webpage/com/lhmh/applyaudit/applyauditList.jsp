<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="applyauditList" title="会诊申请审核" actionUrl="applyauditController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="申请单号" field="applyId" query="true" ></t:dgCol>
   <t:dgCol title="申请日期" field="date1" query="true"></t:dgCol>
   <t:dgCol title="病人姓名" field="patientName" query="true" ></t:dgCol>
   <t:dgCol title="科室" field="officeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊科室" field="apofficeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊单位" field="apcomId" replace="${comsReplace}"></t:dgCol>
   <t:dgCol title="拟会诊时间" field="apdate1" ></t:dgCol>
   <t:dgCol title="状态" field="status" query="true" dictionary="apstatus"></t:dgCol>
    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   	<t:dgConfOpt title="审核通过" exp="status#eq#01" url="applyauditController.do?present&id={id}" message="确定该记录审核通过吗？"/>
   	<t:dgToolBar title="查看" icon="icon-search" url="applyController.do?detail" onclick="openDetail()" funname="detail"></t:dgToolBar>
   	<t:dgToolBar title="退回" icon="icon-back" url="applyauditController.do?back" onclick="openback()"></t:dgToolBar>
  	<t:dgToolBar title="资料查看" icon="icon-putout" url="applyController.do?datadetail" onclick="listJpeg('1')"></t:dgToolBar>
   	<t:dgToolBar title="模板生成" icon="icon-putout" url="applyController.do?detailword" onclick="applyprint()"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
  <script type="text/javascript">
  	function openDetail(){
		var rowData = $("#applyauditList").datagrid("getSelected");
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
	
	function openback(){
		var rowData = $("#applyauditList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择退回记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再退回");
			return;
		}
		// 退回借用修改方法
		update("退回","applyauditController.do?addorupdate","applyauditList",null,null);
	}
	
	function dataDetail(){
		var rowData = $("#applyauditList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?datadetail&id="+rowData.id);
	}
	
	function applyprint(){
		var rowData = $("#applyauditList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择生成模板的记录");
			return;
		}
		
		window.open("applyController.do?applyprint&id="+rowData.id);
	}
	function listJpeg( type ){
		var rowData = $("#applyauditList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?fileList&id="+rowData.id + 
				"&editable=false&type=" + type  );
	}
  </script>