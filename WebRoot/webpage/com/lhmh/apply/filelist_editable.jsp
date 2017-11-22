<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>会诊文件清单</title>
		<meta charset="UTF-8"/>
		<script>
			var applyId = '${applyId}'
			var type = '${type}'
		</script>
		<link href="/vendor/layer/skin/layer.css" media="all" rel="stylesheet" type="text/css" /></link>
		<link href="/displayfile/main.css" media="all" rel="stylesheet" type="text/css" /></link>
		
		<script type="text/javascript" src="/vendor/jquery.js"></script>
		<script type="text/javascript" src="/vendor/lodash_backbone.js"></script>
		<script type="text/javascript" src="/vendor/layer/layer.js"></script>
		<script type="text/javascript" src="/displayfile/main.js"></script>
		<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	</head>
	<body style="margin: 20px 20px 0px 20px;">
		<form name="form1" METHOD="POST" ACTION="" ENCTYPE="multipart/form-data">
			<div style="height:50px;">
				<input class="btn" style="display:block !important;" 
					type="button" onclick="closeit()" value="关闭">
			</div>
			<div class="divBody">
				<div class="files-container">
					<div class="add-file">
						<img src="/displayfile/default.png"/>
						<div class="fun-container"></div>
					</div>
				</div>
			</div>
			<div id="big-img-container"><img id="bigImg" /></div>
			<input type="file" id="hid-input-file" style="display:none;" onchange="fileChangeCallback()">
		</form>
	</body>
</html>