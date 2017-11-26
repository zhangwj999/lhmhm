<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript">
	
	// 初始化将下拉框默认不可选择
	window.onload = function(){

	}
</script>
<title>角色集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="doctorList" title="按专家选择" actionUrl="applyController.do?datagridDoctors" checkbox="true" idField="doctorId" queryMode="group">
	<t:dgCol title="编号" field="doctorId" width="50" query="true"></t:dgCol>
	<t:dgCol title="专家名称" field="doctorName" width="50" query="true"></t:dgCol>
	<t:dgCol title="收费标准" field="payKind" width="50" replace="${payKindReplace}"></t:dgCol>
	<t:dgCol title="优势擅长" field="fMemo" width="50" query="true"></t:dgCol>
</t:datagrid>
</body>
</html>
