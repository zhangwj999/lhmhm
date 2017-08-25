<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>
<%@ page import="javazoom.upload.*"%>
<%@ page import="uploadutilities.FileMover"%>
<%@ page import="org.jeecgframework.web.system.service.SystemService"%>
<%@ page language="java" import="java.util.*"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<%
	request.setCharacterEncoding("UTF-8");//设置编码格式，就不用一个个转码了。  
	FileMover fileMover = new FileMover();//你也可以使用自带的实例中jsp:useBean的形式。  
	UploadBean upBean = new UploadBean();
	MultipartFormDataRequest mrequest = null;
	Hashtable files = null;
	if (MultipartFormDataRequest.isMultipartFormData(request)) {
		mrequest = new MultipartFormDataRequest(request, null,100 * 1024 * 1024, MultipartFormDataRequest.COSPARSER,"UTF-8");//注意这里也要设置编码参数  
		// 获取上传文件名称
		String fileName = mrequest.getParameter("title");
		//这里用来测试title参数是否正确。调试的时候，加一句if (true)return;即可。  
		files = mrequest.getFiles();
	}

	// 后台指定路径
	String fileId = mrequest.getParameter("fileId");
	// 会诊单号
	String applyId = mrequest.getParameter("applyId");
	// 公司编号
	String comId = mrequest.getParameter("comId");
	// 文件序号
	String seq = mrequest.getParameter("seq");
	// 文件类型
	String fileType = mrequest.getParameter("fileType");

	// 自定义路径
	String sPath = "E:\\" + fileId;

	int iFileCount = 0;
	String sServerFileName = "";
	String sLocalFileName = "";
	//文件获取  
	if ((files != null) || (!files.isEmpty())) {

		iFileCount = files.size();
		UploadFile file = (UploadFile) files.get("attach");
		sLocalFileName = file.getFileName();

		//取文件名的后缀  
		int ii = sLocalFileName.indexOf("."); 
		String sExt = sLocalFileName.substring(ii,sLocalFileName.length());

		// 文件名定义为 单号 + 序列号
		sServerFileName = applyId + seq;
		sServerFileName = sServerFileName + sExt;

		//如果不存在该目录，则新建一个  
		File dir = new File(sPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		upBean.setFolderstore(sPath);//设置要上传的目录  
		upBean.addUploadListener(fileMover);//增加filMover监听  
		fileMover.setNewfilename(sServerFileName);//设置服务器上的文件名  
		upBean.store(mrequest, "attach");//上传  
	}
%>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyController.do?saveFile" >
	<input id="comId" name="comId" type="hidden" value="<%=comId %>">
	<input id="infoId" name="infoId" type="hidden" value="<%=applyId %>">
	<input id="seq" name="seq" type="hidden" value="<%=seq %>">
	<input id="fileType" name="fileType" type="hidden" value="<%=fileType %>">
	<input id="fileDocId" name="fileDocId" type="hidden" value="<%=sPath %>">
	<input id="fileName" name="fileName" type="hidden" value="<%=sServerFileName %>">
	<input id="isMrb" name="isMrb" type="hidden" value="1">
	<%=request.getContextPath() %>
</t:formvalid>
</body>
</html>
<script type="text/javascript">
//请求 URL: http://localhost:8080/jeecg-framework/applyController.do?datagrid&field=id,applyId,date1,patientName,officeId,apofficeId,apcomId,apdate1,status,
	document.forms[0].action = "http://localhost:8080/jeecg-framework/applyController.do?saveFile";
	document.getElementById("formobj").submit();
</script>

