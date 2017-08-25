<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科室管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="lhOfficeController.do?save">
		<input id="id" name="id" type="hidden" value="${lhOfficePage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">医院编号:</label>
		      <input class="inputxt" id="comId" name="comId" 
					   value="${lhOfficePage.comId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">科室编号:</label>
		      <input class="inputxt" id="officeId" name="officeId" 
					   value="${lhOfficePage.officeId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">科室名称:</label>
		      <input class="inputxt" id="officeName" name="officeName" 
					   value="${lhOfficePage.officeName}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>