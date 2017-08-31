<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>拍照上传</title>
		<script type="text/javascript" src="<%=path %>/plug-in/jquery/jquery-2.0.0.min.js">
		</script>
		<script type="text/javascript"
			src="<%=path %>/plug-in/jpeg_camera/jpeg_camera_with_dependencies.min.js"></script>
		<script type="text/javascript" src="<%=path %>/skin/videocap/demo.js"></script>
		<link href="<%=path %>/skin/videocap/demo.css" media="all" rel="stylesheet" type="text/css" /></link>
		<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	</head>
	<body>
		<form name="form1" METHOD="POST" ACTION="" ENCTYPE="multipart/form-data">
			<div style="width:200px;height:50px;float:right;position:fixed;right:20px;">
				<input class="btn" style="display:block !important;" 
					type="button" onclick="closeit()" value="关闭">
			</div>
			
			<div id="camera_info"></div>
<!-- 			<div id="stream_stats"></div> -->
	
			<div id="camera">
				<div class="placeholder">
					Your browser does not support camera access.<br>
					We recommend
					<a href="https://www.google.com/chrome/" target="_blank">Chrome</a>
					&mdash; modern, secure, fast browser from Google.<br>
					It's free.
				</div>
			</div><br>
	
			<input type="button" id="take_snapshots" value="拍照" />
			<input type="button" id="show_stream" value="显示摄像头" />
			<br>
	
			<div id="snapshots"></div>
	
			<input type="button"  id="discard_snapshot" value="删除" />
			<input type="button"  id="upload_snapshot" value="上传" /><br>
	
			<input type="text" id="api_url" value="/uploadImg"><br>
	
			<img src="loader.gif" id="loader">
			<div id="upload_status"></div>
			<div id="upload_result"></div>
		</form>
	</body>
</html>
