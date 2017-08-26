<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>video cap control demo</title>
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
	}

	$(function() {

	$('#btn_hidden_btns').click(function() {
	document.getElementById('WebVideoCap1').hiddenControllButtons();
	document.getElementById('WebVideoCap1').autofill(636, false);

	}), $('#btn_show_btns').click(function() {
	document.getElementById('WebVideoCap1').showControllButtons();
	document.getElementById('WebVideoCap1').autofill(636, true);
	}), $('#btn_start').click(function() {
	document.getElementById('WebVideoCap1').start();

	}), $('#btn_stop').click(function() {
	document.getElementById('WebVideoCap1').stop();

	}), $('#btn_cap_only').click(function() {
	document.getElementById('WebVideoCap1').cap();

	}), $('#btn_cap').click(function() {
	document.getElementById('WebVideoCap1').cap();
	document.getElementById('base64_jpeg').value = document
	.getElementById('WebVideoCap1').jpegBase64Data;
	document.getElementById('base64_bmp').value = document
	.getElementById('WebVideoCap1').bmpBase64Data;
	document.getElementById("picData").value = document
	.getElementById('WebVideoCap1').jpegBase64Data;

	ajax_post();
	}), $('#btn_submit_only').click(function() {
	document.getElementById('WebVideoCap1').cap();
	document.getElementById('base64_jpeg').value = document
	.getElementById('WebVideoCap1').jpegBase64Data;
	document.getElementById('base64_bmp').value = document
	.getElementById('WebVideoCap1').bmpBase64Data;
	document.getElementById("picData").value = document
	.getElementById('WebVideoCap1').jpegBase64Data;

	alert(document.getElementById("picData").value.length);
	document.forms[0].submit();

	}), $('#btn_getdata_only').click(function() {
	document.getElementById('base64_jpeg').value = document
	.getElementById('WebVideoCap1').jpegBase64Data;
	document.getElementById('base64_bmp').value = document
	.getElementById('WebVideoCap1').bmpBase64Data;
	document.getElementById("picData").value = document
	.getElementById('WebVideoCap1').jpegBase64Data;

	alert(document.getElementById("picData").value.length);
	}), $('#btn_clear').click(function() {
	        document.getElementById('WebVideoCap1').clear();
	}), $('#btn_submit').click(function() {
	document.getElementById('WebVideoCap1').cap();
	document.getElementById('base64_jpeg').value = document
	.getElementById('WebVideoCap1').jpegBase64Data;
	document.getElementById('base64_bmp').value = document
	.getElementById('WebVideoCap1').bmpBase64Data;
	document.getElementById("picData").value = document
	.getElementById('WebVideoCap1').jpegBase64Data;

	alert(document.getElementById("picData").value.length);
	document.forms[0].submit();
	});

	});

	function ajax_post() {
	var base64_data = document.getElementById('WebVideoCap1').jpegBase64Data;
	$.ajax({
	url : 'http://localhost:8080/VideoCap/servlet/VideoCap4Ajax',
	type : 'POST',
	dataType : 'jason',
	data : {
	picData : "'" + base64_data + "'"
	},
	timeout : 1000,
	success : callbackfun
	});

	}

	function callbackfun(data) {
	var obj = eval('(' + data + ')');

	if ('ok' == obj.savestatus) {
	alert('照片采集成功!');
	}

	}

</script>
</head>
<body>

<form action="<%=request.getContextPath()%>/servlet/VideoCap" id="cap_form" method="post">
<input type="hidden"  id="picData" name="picData">
<object classid="clsid:34681DB3-58E6-4512-86F2-9477F1A9F3D8"
id="WebVideoCap1" codebase="<%=request.getContextPath()%>/cabs/Package.cab#version=1,0,0,50" width="636" height="238">
<param name="Visible" value="0">
<param name="AutoScroll" value="0">
<param name="AutoSize" value="0">
<param name="AxBorderStyle" value="1">
<param name="Caption" value="WebVideoCap">
<param name="Color" value="4278190095">
<param name="Font" value="宋体">
<param name="KeyPreview" value="0">
<param name="PixelsPerInch" value="96">
<param name="PrintScale" value="1">
<param name="Scaled" value="-1">
<param name="DropTarget" value="0">
<param name="HelpFile" value>
<param name="PopupMode" value="0">
<param name="ScreenSnap" value="0">
<param name="SnapBuffer" value="10">
<param name="DockSite" value="0">
<param name="DoubleBuffered" value="0">
<param name="ParentDoubleBuffered" value="0">
<param name="UseDockManager" value="0">
<param name="Enabled" value="-1">
<param name="AlignWithMargins" value="0">
<param name="ParentCustomHint" value="-1">
</object>
<hr/> 
<input type="button" value="隐藏控制按钮" id="btn_hidden_btns">
<input type="button" value="显示控制按钮" id="btn_show_btns">
<input type="button" value="开始捕获" id="btn_start">
<input type="button" value="停止捕获" id="btn_stop">
<input type="button" value="照相" id="btn_cap_only">
<input type="button" value="照相并用ajax方式提交" id="btn_cap">
<input type="button" value="照相并用非ajax方式提交" id="btn_submit">
<input type="button" value="不照相直接提交" id="btn_submit_only">
<input type="button" value="只获取base64数据" id="btn_getdata_only">
<input type="button" value="清除数据" id="btn_clear">


<hr/>
jpeg格式base64编码数据内容：
<textarea rows="30" cols="50" id="base64_jpeg" name="base64_jpeg"></textarea>
bmp格式base64编码数据内容：
<textarea rows="30" cols="50" id="base64_bmp" name="base64_bmp"></textarea>

</form>
</body>
</html>
