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
	request.setCharacterEncoding("UTF-8");//���ñ����ʽ���Ͳ���һ����ת���ˡ�  
	FileMover fileMover = new FileMover();//��Ҳ����ʹ���Դ���ʵ����jsp:useBean����ʽ��  
	UploadBean upBean = new UploadBean();
	MultipartFormDataRequest mrequest = null;
	Hashtable files = null;
	if (MultipartFormDataRequest.isMultipartFormData(request)) {
		mrequest = new MultipartFormDataRequest(request, null,100 * 1024 * 1024, MultipartFormDataRequest.COSPARSER,"UTF-8");//ע������ҲҪ���ñ������  
		// ��ȡ�ϴ��ļ�����
		String fileName = mrequest.getParameter("title");
		out.println("<br>Title0�ǣ�" + fileName + "<br>");
//		String sTt1 = new String(sTt0.getBytes("ISO-8859-1"),"UTF-8");  
//		out.println("<br>Title1�ǣ�"+sTt1+"<br>");  
		//������������title�����Ƿ���ȷ�����Ե�ʱ�򣬼�һ��if (true)return;���ɡ�  
		files = mrequest.getFiles();
	}

	//��ȡ�޸�ǰ���ļ���  
//	String sOldFileName =mrequest.getParameter("oldfilename");   
//	out.println("sOldFileName:"+sOldFileName);  

	// �ļ��ϴ�·��  
//	String sWebRootPath = request.getRealPath("/");//�õ����webӦ�õĸ���
//	String sPath = sWebRootPath+"attach";  

	// ��ָ̨��·��
	String fileId = mrequest.getParameter("fileId");

	// �Զ���·��
	String sPath = "E:\\" + fileId;

	int iFileCount = 0;
	String sServerFileName = "";
	String sLocalFileName = "";
	//�ļ���ȡ  
	if ((files != null) || (!files.isEmpty())) {

		iFileCount = files.size();
		UploadFile file = (UploadFile) files.get("attach");
		sLocalFileName = file.getFileName();
		out.println("sLocalFileName:" + sLocalFileName);
		int ii = sLocalFileName.indexOf("."); //ȡ�ļ����ĺ�׺  
		String sExt = sLocalFileName.substring(ii,
				sLocalFileName.length());

		//�õ����ظ����ļ���  
		java.util.Date dt = new java.util.Date(
				System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sServerFileName = fmt.format(dt);
		sServerFileName = sServerFileName + sExt;

		//��������ڸ�Ŀ¼�����½�һ��  
		File dir = new File(sPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		upBean.setFolderstore(sPath);//����Ҫ�ϴ���Ŀ¼  
		upBean.addUploadListener(fileMover);//����filMover����  
//		fileMover.setNewfilename(sServerFileName);//���÷������ϵ��ļ���  
		fileMover.setNewfilename(sLocalFileName);//���÷������ϵ��ļ���  
		upBean.store(mrequest, "attach");//�ϴ�  
		out.println("file path is " + sPath + "/" + sServerFileName);
		
	}
%>
<form>

</form>
<script type="text/javascript">
	
	window.close();
</script>

