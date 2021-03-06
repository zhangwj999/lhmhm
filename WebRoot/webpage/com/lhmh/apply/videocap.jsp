<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>拍照上传</title>
		<script>
			var applyId = '${applyId}'
			var type = '${type}'
		</script>
		
		<link href="/vendor/layer/skin/layer.css" media="all" rel="stylesheet" type="text/css" /></link>
		<script type="text/javascript" src="/vendor/jquery.js"></script>
		<script type="text/javascript" src="/vendor/lodash_backbone.js"></script>
		<script type="text/javascript" src="/vendor/layer/layer.js"></script>

		<script type="text/javascript"
			src="<%=path %>/plug-in/jpeg_camera/jpeg_camera_with_dependencies.min.js"></script>

		<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	</head>
	<body>
		<form id="mainForm" name="form1" METHOD="POST" ACTION="" ENCTYPE="multipart/form-data">
			<div style="width:100%;height:50px;float:right;margin-right:20px;">
				<input class="btn" type="button" id="close-btn" value="关闭">
				<input class="btn" type="button" id="discard_snapshot" value="删除">
				<input class="btn" type="button" id="upload_snapshot" value="上传">
				<input class="btn" type="button" id="select_snapshot_allunsel" value="清除选择">
				<input class="btn" type="button" id="select_snapshot_all" value="全选">
			</div>
			
			<div class="divBody">
				<div class="left-container">
<!-- 					<div id="camera_info"></div> -->
					<div class="take_snapshots_container">
						<input type="button" id="take_snapshots" value="拍照" />
					</div>
					<div id="camera">
						<div class="placeholder">
							Your browser does not support camera access.<br>
							We recommend
							<a href="https://www.google.com/chrome/" target="_blank">Chrome</a>
							&mdash; modern, secure, fast browser from Google.<br>
							It's free.
						</div>
					</div>
				</div>
		
				<div class="right-container">
					<div id="snapshots"></div>
				</div>
		
				<input type="text" style="display:none;" id="api_url" value="/uploadImg"><br>
			</div>

			<img src="<%=path %>/skin/videocap/loader.gif" id="loader">
			<div id="upload_status"></div>
			<div id="upload_result"></div>
		</form>
	</body>
	<link href="/videocap/main.css" media="all" rel="stylesheet" type="text/css" /></link>
	<script type="text/javascript" src="/videocap/main.js"></script>
</html>
