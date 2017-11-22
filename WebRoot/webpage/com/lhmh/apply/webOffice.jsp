<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<TITLE>weboffice演示页面</TITLE>
<!-- --------------------=== 调用Weboffice初始化方法 ===--------------------- -->
<SCRIPT language=javascript event=NotifyCtrlReady for=WebOffice1>
/****************************************************
*
*	在装载完Weboffice(执行<object>...</object>)
*	控件后执行 "WebOffice1_NotifyCtrlReady"方法
*
****************************************************/
	WebOffice1_NotifyCtrlReady();
</SCRIPT>


<SCRIPT language=javascript>
/****************************************************
*
*		控件初始化WebOffice方法
*
****************************************************/
function WebOffice1_NotifyCtrlReady() {
	document.all.WebOffice1.ShowToolBar(0);
	document.all.WebOffice1.OptionFlag |= 128;
	document.all.WebOffice1.LoadOriginalFile("./applyController.do?getDoc&id=${applyId}&fileId=1", 'doc');
	ProtectFull()
}
// ---------------------== 关闭页面时调用此函数，关闭文件 ==---------------------- //
function window_onunload() {
	document.all.WebOffice1.Close();
}
function newDoc() {
	document.all.WebOffice1.ShowToolBar(0);
	var doctype=document.all.doctype.value;
	document.all.WebOffice1.LoadOriginalFile("", doctype);
}

// 打开本地文件
function docOpen() {
	document.all.WebOffice1.LoadOriginalFile("open", "doc");
}
// -----------------------------== 保存文档 ==------------------------------------ //
function newSave() {
	document.all.WebOffice1.Save();
}
// -----------------------------== 另存为文档 ==------------------------------------ //
function SaveAsTo() {
	document.all.WebOffice1.ShowDialog(84);
}

/****************************************************
*				设置文档保护 
****************************************************/
function ProtectFull() {
	try{
		var webObj=document.getElementById("WebOffice1");
		webObj.ProtectDoc( 1,1, '123' );
// 		webObj.FullScreen = true;
	}catch(e){
		alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
	}
}
</SCRIPT>
</HEAD>
<BODY style="BACKGROUND: #ccc" onunload="return window_onunload()">
<form name="myform">
<table width="95%" border="0" cellpadding="1" cellspacing="3">
	<tr bgcolor="#8CBFE9">
		<td height="15" valign="top" class="downSide">
<%-- 			<font size="-1">&nbsp;&nbsp; 文档编号:</font> <input name="DocID" value="${docData.docid}" size="14">  --%>
<%-- 			<span class="STYLE1">|</span> <font size="-1">&nbsp;&nbsp; 标 题:</font> <input name="DocTitle" value="${docData.doctitle}" size="14">  --%>
<!-- 		 	<span class="STYLE1">|</span> <input name="button9" type="button" onClick="return SaveDoc()" value="上传到服务器" classs="rollout">  -->
<!-- 		 	<span class="STYLE1">|</span> <input name="button" type="button" onClick="return docOpen()" value="打开本地文件" /></td> -->
		<td></td>
	</tr>
	<tr>
		<td colspan="2" rowspan="12" valign="top"><!-- -----------------------------== 装载weboffice控件 ==--------------------------------- --> <SCRIPT>
					var s = ""
						s += "<object id=WebOffice1 height=768 width='100%' style='LEFT: 0px; TOP: 0px' classid='clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5' codebase='../../../plug-in/webOffice/weboffice_v6.0.5.0.cab#Version=6,0,5,0'>"
						s +="<param name='_ExtentX' value='6350'><param name='_ExtentY' value='6350'>"
						s +="</OBJECT>"			
						document.write(s)
				</SCRIPT> <!-- --------------------------------== 结束装载控件 ==----------------------------------- --></td>
	</tr>
</table>
</form>
</BODY>
</HTML>
