<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ page isELIgnored="true" %> 
<script type="text/javascript" src="/vendor/jquery.js"></script>

<%
	String docCName = "申请单明细打印";
	List dataList = (List) request.getAttribute("docData");
%>
<html>
<meta http-equiv="content-type" content="text/html; charset=gb2312">
<head>
	<title>申请单明细打印</title>
	<style>
		.fakehlink {cursor: hand; text-decoration:none; color: #0066CC; font-weight:normal;}
	</style>
<script language="JavaScript">
var TANGER_OCX_bDocOpen = false;
var TANGER_OCX_OBJ;
//onload时调用的方法
function MyOnloadFunc(){
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	TANGER_OCX_bDocOpen = true;
	//设置为全屏
	TANGER_OCX_OBJ.FullScreenMode= true;
	AddMyMenuItems();
	OpenDocTemplate();
	OpenDocTemplate();
	copyDataToDoc();
	setDocProperties();
// 	getProspect(primaryKey);
// 	test3();
}
//设置word属性
function setDocProperties(){
	TANGER_OCX_SetReadOnly(true);//设置是否可修改word
	TANGER_OCX_EnableFilePrintMenu(true);//打印菜单控制
	TANGER_OCX_EnableFilePrintPreviewMenu(true);//打印预览菜单控制
	TANGER_OCX_EnableFileNewMenu(false);//设置新建菜单是否可用
	TANGER_OCX_EnableFileOpenMenu(false);//设置打开菜单是否可用
	TANGER_OCX_EnableFileSaveMenu(true);//设置保存菜单是否可用
	TANGER_OCX_EnableFileSaveAsMenu(false);//设置另存为菜单是否可用
}
//打开文书word模板
function OpenDocTemplate() {
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	TANGER_OCX_OBJ.Titlebar = false;	//标题栏显示控制
	TANGER_OCX_OBJ.Statusbar = false;	//状态栏显示控制
	TANGER_OCX_OBJ.Toolbars =false;		//工具栏显示控制
	TANGER_OCX_OBJ.IsUseUTF8Data =true;		//读取/设置是否使用UTF-8编码
	TANGER_OCX_OBJ.IsUseUTF8URL =true;		//读取/设置是否使用UTF-8传输URL
	
	var DOC_PATH;
	DOC_PATH="/template/apply_print.doc";//word模板路径。
	TANGER_OCX_OBJ.OpenFromURL(DOC_PATH);	//打开doc模板文件.
}

//文本替换
function ReplaceText(inputvalue,inputname) {
	if( TANGER_OCX_bDocOpen) {
		searchStr = inputname;
		replaceStr = inputvalue;
	replaceStr=replaceStr.replace(/&/g,"\r");
		rangeWord = TANGER_OCX_OBJ.ActiveDocument.Content;
		rangeWord.Find.Execute(searchStr,false,false,false,false,false,true,1,false,replaceStr,2);
	}
}

// function test3(){
// 	TANGER_OCX_OBJ.activedocument.application.Worksheets("Sheet1").cells.select();//TANGER_OCX_OBJ此为隐藏控件的控件对象，实现是选中sheet1里面的所有单元格；
// 	//TANGER_OCX_OBJ.ActiveDocument.Application.ActiveSheet.cells(1,1).Select().AddComment("555555555555");
// 	for(var i=6 ;i<200;i++){
// 		for(var j=2 ;j<200;j++){
// 			TANGER_OCX_OBJ.ActiveDocument.Application.ActiveSheet.cells(i,j) = "12";//对选中单元格添加值
// 		}
		
// 	}
// 	TANGER_OCX_OBJ.activedocument.application.selection.copy();//对选中的所有单元格进行copy;
// }

//将window的数据复制到
function copyDataToDoc() {
<%
	for (int i=0; i<dataList.size(); i++) {
		HashMap useMap = (HashMap) dataList.get(i);
		String name = (String)useMap.get("name");
		String value = (String)useMap.get("value");
		if (null == value) {
			value="";
		}
		//由于控件支持替换的字符串最大为255个汉字，而超过255字就会产生错误，因此，在替换前进行判断，以避免此中情况的发生
		//另：要求大于255汉字长度的字段，请在拦截器中进行分割
		if(value.length()<= 255){
%>
		ReplaceText('<%=value.replaceAll("\r\n","^l").replaceAll("\n","^l").replaceAll("&","^l")%>'
				,'${<%=name%>}');
<%
		}
	}
%>	
	TANGER_OCX_OBJ.focus();	
}

//切换文档的只读状态（文档保护状态）
function TANGER_OCX_SetReadOnly(boolvalue){
	var i;
	try{
		if (boolvalue) {
			TANGER_OCX_OBJ.IsShowToolMenu = false;
		}
		with(TANGER_OCX_OBJ.ActiveDocument){
			if (TANGER_OCX_OBJ.DocType == 1){ //word
				if ( (ProtectionType != -1) &&  !boolvalue){
					Unprotect();
				}
				if ( (ProtectionType == -1) &&  boolvalue){
					Protect(2,true,"");
				}
			}else if(TANGER_OCX_OBJ.DocType == 2){//excel
				for(i=1;i<=Application.Sheets.Count;i++){
					if(boolvalue){
						Application.Sheets(i).Protect("",true,true,true);
					}else{
						Application.Sheets(i).Unprotect("");
					}
				}
				if(boolvalue){
					Application.ActiveWorkbook.Protect("",true);
				}else{
					Application.ActiveWorkbook.Unprotect("");
				}
			}
		}
	}catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{}
}

//允许或禁止文件－>新建菜单
function TANGER_OCX_EnableFileNewMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileNew = boolvalue;
}
//允许或禁止文件－>打开菜单
function TANGER_OCX_EnableFileOpenMenu(boolvalue)
{
	TANGER_OCX_OBJ. FileOpen  = boolvalue;
}
//允许或禁止文件－>关闭菜单
function TANGER_OCX_EnableFileCloseMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileClose = boolvalue;
}
//允许或禁止文件－>保存菜单
function TANGER_OCX_EnableFileSaveMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileSave = false;
}
//允许或禁止文件－>另存为菜单
function TANGER_OCX_EnableFileSaveAsMenu(boolvalue)
{
	TANGER_OCX_OBJ.FileSaveAs = boolvalue;
}
//允许或禁止文件－>打印菜单
function TANGER_OCX_EnableFilePrintMenu(boolvalue)
{
	TANGER_OCX_OBJ.FilePrint = boolvalue;
}
//允许或禁止文件－>打印预览菜单
function TANGER_OCX_EnableFilePrintPreviewMenu(boolvalue)
{
	TANGER_OCX_OBJ.FilePrintPreview = boolvalue;
}

//打印功能拿出来
function forprint(){
	var oldOption;	
	try{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		oldOption = objOptions.PrintBackground;
		objOptions.PrintBackground = isBackground;
	}catch(err){};
	
	TANGER_OCX_OBJ.printout(true);
	
	try{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		objOptions.PrintBackground = oldOption;
	}
	catch(err){};

} 

//自定义菜单
function AddMyMenuItems(){
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
 	try{
		TANGER_OCX_OBJ.AddCustomButtonOnMenu(2,"打印",true,2);
	}catch(err){
		alert("不能创建新对象："+ err.number +":" + err.description);
	}finally{
	}
}

function getProspect(primaryKey){
	  if(docName=='lmapplyWritProspect'){
		  TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
		  var url = findPositonImage(primaryKey);
		  var shape =TANGER_OCX_OBJ.ActiveDocument.Shapes.AddPicture(url); 
		  shape.height="120"; 
		  shape.left="200"; 
		  shape.top="50";  
		/*   TANGER_OCX_OBJ.AddPicFromURL("/DocCenterService/image?photo_id=509",true,
			30,20,1,
			25,1);    */
		  var url1=	findPlanImage(primaryKey);
		  var shape1 =TANGER_OCX_OBJ.ActiveDocument.Shapes.AddPicture(url1); 
		  //shape.width="130";
		  shape1.height="120"; 
		  shape1.left="200"; 
		  shape1.top="220"; 
	  }
}

</script>
</head>
<body bgcolor="#ffffff" topmargin="5" leftmargin="0" marginheight="0" marginwidth="0" onload="MyOnloadFunc()">
<FORM METHOD=post ACTION=""  ENCTYPE="multipart/form-data" NAME="myForm" id="myForm">
<html:hidden name="docName" value="申请单明细打印" />
<object id="TANGER_OCX" classid="clsid:01DFB4B4-0E07-4e3f-8B7A-98FD6BFF153F"
	codebase="<%=request.getContextPath()%>/plug-in/docprint/ofctnewclsid.cab#version=5,0,2,2" width="100%" height="100%">
<param name="BorderStyle" value="1">
<param name="TitlebarColor" value="42768">
<param name="TitlebarTextColor" value="0"> 
<param name="Titlebar" value="-1">
<param name="Toolbars" value="-1">
<param name="MakerCaption" value="山东浪潮齐鲁软件产业股份有限公司">
<param name="MakerKey" value="DED4CCDE4D68C9F0CA2D9498592B80E9E091979A">
<param name="ProductCaption" value="山东浪潮齐鲁软件产业股份有限公司">
<param name="ProductKey" value="1D46250922B1FF48A0C9B8F1D6CEDEC932FE0315">
<param name="Caption" value="<%=docCName%>">
</object>
</FORM>
<script language="JScript" for="TANGER_OCX" event="OnCustomButtonOnMenuCmd(btnPos,btnCaption,btnCmdid)">
	//addLicenseCopy();//副本预览
	if(2 == btnCmdid)
	{ 
		forprint();//打印  	
	}
	

</script>
<script language="JScript" for="TANGER_OCX" event="OnCustomMenuCmd(menuIndex,menuCaption,menuID)">
//alert("第" + menuIndex +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
if(menuID=='1'){
	var mydoc = TANGER_OCX.ActiveDocument; //得到Document对象
	var app = mydoc.Application; //得到Application对象
	var sel = app.Selection; //得到Selection对象
	sel.FormFields.Add(sel.Range,i);//添加一个复选框71
	
	mydoc.FormFields(1).CheckBox.Default = true;//选中第一个复选框
}else if(menuID=='2'){
	var mydoc = TANGER_OCX.ActiveDocument; //得到Document对象
	var app = mydoc.Application; //得到Application对象
	var sel = app.Selection; //得到Selection对象
	app.Selection="□";// □
	
}
</script>

</body>
</html>
