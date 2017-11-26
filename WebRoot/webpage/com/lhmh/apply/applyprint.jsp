<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<link href="/apply_print/main.css" type="text/css" rel="stylesheet"/>
	</head>
	<body>
		<div class="title">
			会诊单申请
		</div>
		<div class="info1">
			<div class="left sansan item">会诊单号：${docData.APPLY_ID}</div>
			<div class="left sansan item"></div>
			<div class="right sansan item indent">会诊日期：${docData.APDATE1}</div>
		</div>
		<div class="info2">
			<div class="left sansan">
				<div class="item">住院号：${docData.APPLY_ID}</div>
				<div class="item">社保卡/医保卡：${docData.MEDICAL_CARD}</div>
				<div class="item">拟会诊单位：${docData.APCOM_NAME}</div>
				<div class="item">拟会诊专家：${docData.DOCTOR_NAME}</div>
			</div>
			<div class="left sansan">
				<div class="item indent">病人姓名：${docData.PATIENT_NAME}</div>
				<div class="item indent">性别：${sexEnum[docData.PATIENT_SEX]}</div>
				<div class="item indent">拟会诊科室：${docData.AP_OFFICE_NAME}</div>
			</div>
			<div class="right sansan">
				<div class="item indent">年龄：${docData.PATIENT_AGE}</div>
				<div class="item indent">婚否：-</div>
				<div class="item indent">拟会诊时间：${docData.PBEGIN_DATE}</div>
			</div>
		</div>
		<div class="info3">
			<div class="item">病史摘要：</div>
			<div class="content-length">&nbsp;&nbsp;${docData.HIS_SUMMARY}</div>
			<div class="item">当初初步诊断与处理：</div>
			<div class="content-length">&nbsp;&nbsp;${docData.CUR_DETAIL}</div>
		</div>
		<div class="info4">
			<div class="item">会诊理由和目的：</div>
			<div class="content-length">&nbsp;&nbsp;${docData.MEET_PURPOSE}</div>
			<div class="item">会诊结果：</div>
			<div class="content-length">&nbsp;&nbsp;${docData.RPT_DETAIL}</div>
		</div>
		<div class="info5">
			<div class="left sansan item">申请单位：${docData.COM_NAME}</div>
			<div class="left sansan item"></div>
			<div class="left sansan item indent">申请科室：${docData.OFFICE_NAME}</div>
			<div class="left sansan item">申请医师：${docData.DOCTOR_NAME}</div>
			<div class="left sansan item indent">科主任：${docData.MANAGER_NAME}</div>
			<div class="left sansan item indent">科室联系电话：-</div>
		</div>
	</body>
</html>