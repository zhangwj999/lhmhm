<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>角色集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="managerList" title="按科主任选择" actionUrl="applyController.do?datagridManagers" idField="id" showRefresh="false" queryMode="group">
	<t:dgCol title="编号" field="id" width="50" query="true"></t:dgCol>
	<t:dgCol title="科主任" field="fname" width="50" query="true"></t:dgCol>
</t:datagrid>
</body>
</html>
