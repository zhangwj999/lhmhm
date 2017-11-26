<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="applyList" title="医师会诊申请" actionUrl="applyController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="申请单号" field="applyId" query="true" ></t:dgCol>
   <t:dgCol title="申请日期" field="date1" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="病人姓名" field="patientName" query="true" ></t:dgCol>
   <t:dgCol title="科室" field="officeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊科室" field="apofficeId" replace="${officesReplace}"></t:dgCol>
   <t:dgCol title="拟请会诊单位" field="apcomId" replace="${comsReplace}"></t:dgCol>
   <t:dgCol title="拟会诊时间" field="apdate1" ></t:dgCol>
   <t:dgCol title="状态" field="status" query="true" dictionary="apstatus"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" exp="status#eq#00,21" url="applyController.do?del&id={id}" />
   <t:dgToolBar title="新增申请" icon="icon-add" url="applyController.do?add" onclick="openAdd()" height="500" width="1040" funname="add"></t:dgToolBar>
   <t:dgToolBar title="修改申请" icon="icon-edit" url="applyController.do?update" onclick="openUpdate()" funname="update"></t:dgToolBar>
   <t:dgToolBar title="打印申请" icon="icon-putout" url="applyController.do?applyprint" onclick="applyprint()"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>

 <script type="text/javascript">
 	// 初始化将下拉框默认不可选择
	window.onload = function(){
		var sels = document.getElementsByName("select");
		for (var i = 0; i < sels.length; i++) {
			var sel = sels[i];
			sel.disabled = "disabled";
		}
	}
 
	function openAdd(){
		window.open("applyController.do?add");
	}

	function openUpdate(){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择修改记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再修改");
			return;
		}
		
		var status = rowData.status;
		if(status != "00" && status != "21"){
			tip("请选择制单状态或退回状态记录再修改");
			return;
		}
		
		window.open("applyController.do?update&id="+rowData.id);
	}

	function openDetail(){
		var rowData = $("#applyList").datagrid("getSelected");
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

	function dataDetail(){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?datadetail&id="+rowData.id);
	}

	function listJpeg( type ){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择查看资料的记录");
			return;
		}
		
		window.open("applyController.do?fileList&id="+rowData.id + 
				"&editable=true&type=" + type  );
	}
	
	function present(){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择提交记录");
			return;
		}
		if (rowData.length > 1) {
			tip("请选择一条记录再提交");
			return;
		}
		document.forms[0].action = "applyController.do?del&flag=1";
		document.forms[0].submit();
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
		if(status != "00" && status != "21"){
			tip("请选择制单状态或退回状态记录再拍照上传资料");
			return;
		}
		
		window.open("applyController.do?videoCap&id="+rowData.id + "&type=" + type );
	}
	
	function applyprint(){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择打印申请的记录");
			return;
		}
		
		window.open("applyController.do?applyprint&id="+rowData.id);
	}
	function webOffice(){
		var rowData = $("#applyList").datagrid("getSelected");
		if (!rowData || rowData.length == 0) {
			tip("请选择打印申请的记录");
			return;
		}
		
		window.open("applyController.do?webOffice&id="+rowData.id);
	}
	
	$(function(){
		$('#applyListtb').find( 'input[name=date1_begin]' ).val( '${date1}' )
	})
 </script>