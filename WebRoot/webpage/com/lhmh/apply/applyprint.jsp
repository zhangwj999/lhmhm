<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ page isELIgnored="true" %> 

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
			<div class="left sansan item">会诊单号：${applyNum}</div>
			<div class="left sansan item"></div>
			<div class="right sansan item indent">会诊日期：${apdate1}</div>
		</div>
		<div class="info2">
			<div class="left sansan">
				<div class="item">住院号：</div>
				<div class="item">社保卡/医保卡：</div>
				<div class="item">拟会诊单位：${apcomName}</div>
				<div class="item">拟会诊专家：${doctorName}</div>
			</div>
			<div class="left sansan">
				<div class="item indent">病人姓名：</div>
				<div class="item indent">性别：</div>
				<div class="item indent">拟会诊科室：</div>
			</div>
			<div class="right sansan">
				<div class="item indent">年龄：</div>
				<div class="item indent">婚否：</div>
				<div class="item indent">拟会诊时间：${apdate1}</div>
			</div>
		</div>
		<div class="info3">
			<div class="item">病史摘要：</div>
			<div class="content-length">&nbsp;&nbsp;${hisSummary}</div>
			<div class="item">当初初步诊断与处理：</div>
			<div class="content-length">&nbsp;&nbsp;${curDetail}</div>
		</div>
		<div class="info4">
			<div class="item">会诊理由和目的：</div>
			<div class="content-length">&nbsp;&nbsp;${meetPurpose}</div>
			<div class="item">会诊结果：</div>
			<div class="content-length">&nbsp;&nbsp;${rptDetail}</div>
		</div>
		<div class="info5">
			<div class="left sansan item">申请单位：SQ000031</div>
			<div class="left sansan item"></div>
			<div class="left sansan item indent">申请科室：20171123</div>
			<div class="left sansan item">申请医师：SQ000031</div>
			<div class="left sansan item indent">科主任：SQ000031</div>
			<div class="left sansan item indent">科室联系电话：20171123</div>
		</div>
	</body>
</html>