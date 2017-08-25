<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>病人管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="lhPatieninfoController.do?save">
		<input id="id" name="id" type="hidden" value="${lhPatieninfoPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">医院编号:</label>
		      <input class="inputxt" id="comId" name="comId" 
					   value="${lhPatieninfoPage.comId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">住院号:</label>
		      <input class="inputxt" id="fId" name="fId" 
					   value="${lhPatieninfoPage.fId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">姓名:</label>
		      <input class="inputxt" id="fName" name="fName" 
					   value="${lhPatieninfoPage.fName}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">年龄:</label>
		      <input class="inputxt" id="fAge" name="fAge" 
					   value="${lhPatieninfoPage.fAge}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">性别 枚举SEX:</label>
		      <input class="inputxt" id="fSex" name="fSex" 
					   value="${lhPatieninfoPage.fSex}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">科室OFFICE_ID:</label>
		      <input class="inputxt" id="fType" name="fType" ignore="ignore"
					   value="${lhPatieninfoPage.fType}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">医保卡号:</label>
		      <input class="inputxt" id="medicalCard" name="medicalCard" 
					   value="${lhPatieninfoPage.medicalCard}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">患者唯一识别编号:</label>
		      <input class="inputxt" id="patientPk" name="patientPk" 
					   value="${lhPatieninfoPage.patientPk}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">备注:</label>
		      <input class="inputxt" id="fMemo" name="fMemo" 
					   value="${lhPatieninfoPage.fMemo}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>