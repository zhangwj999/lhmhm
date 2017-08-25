<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>
<%@ page import="javazoom.upload.*"%>
<%@ page import="uploadutilities.FileMover"%>
<%@ page import="org.jeecgframework.web.system.service.SystemService;"%>

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
		out.println("<br>Title0是：" + fileName + "<br>");
//		String sTt1 = new String(sTt0.getBytes("ISO-8859-1"),"UTF-8");  
//		out.println("<br>Title1是："+sTt1+"<br>");  
		//这里用来测试title参数是否正确。调试的时候，加一句if (true)return;即可。  
		files = mrequest.getFiles();
	}

	//获取修改前的文件名  
//	String sOldFileName =mrequest.getParameter("oldfilename");   
//	out.println("sOldFileName:"+sOldFileName);  

	// 文件上传路径  
//	String sWebRootPath = request.getRealPath("/");//得到你的web应用的根。
//	String sPath = sWebRootPath+"attach";  

	// 后台指定路径
	String fileId = mrequest.getParameter("fileId");

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
		out.println("sLocalFileName:" + sLocalFileName);
		int ii = sLocalFileName.indexOf("."); //取文件名的后缀  
		String sExt = sLocalFileName.substring(ii,
				sLocalFileName.length());

		//得到不重复的文件名  
		java.util.Date dt = new java.util.Date(
				System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sServerFileName = fmt.format(dt);
		sServerFileName = sServerFileName + sExt;

		//如果不存在该目录，则新建一个  
		File dir = new File(sPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		upBean.setFolderstore(sPath);//设置要上传的目录  
		upBean.addUploadListener(fileMover);//增加filMover监听  
//		fileMover.setNewfilename(sServerFileName);//设置服务器上的文件名  
		fileMover.setNewfilename(sLocalFileName);//设置服务器上的文件名  
		upBean.store(mrequest, "attach");//上传  
		out.println("file path is " + sPath + "/" + sServerFileName);
		
	}
%>
<form>

</form>
<script type="text/javascript">
	
	window.close();
</script>

