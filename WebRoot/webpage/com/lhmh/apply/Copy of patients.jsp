<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>角色集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="patientList" title="按病人选择" actionUrl="applyController.do?datagridPatients" idField="id" checkbox="true" showRefresh="false">
	<t:dgCol title="编号" field="patientId" hidden="false"></t:dgCol>
	<t:dgCol title="病人名称" field="patientName" width="50"></t:dgCol>
</t:datagrid>
</body>
</html>
