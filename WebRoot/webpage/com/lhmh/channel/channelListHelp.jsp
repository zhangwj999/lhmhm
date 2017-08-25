<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>通道列表</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">

<t:datagrid name="channelfunctionList" title=" 通道列表" actionUrl="channelController.do?datagrid" idField="channelId" checkbox="true" showRefresh="false">
   <t:dgCol title="通道编号" field="channelId" sortable="true" query="true"></t:dgCol>
   <t:dgCol title="通道名称" field="channelName" sortable="true" query="true"></t:dgCol>
   
</t:datagrid>
</body>
</html>
