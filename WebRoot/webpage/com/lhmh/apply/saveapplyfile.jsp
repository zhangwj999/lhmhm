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
	request.setCharacterEncoding("UTF-8");//���ñ����ʽ���Ͳ���һ����ת���ˡ�  
	FileMover fileMover = new FileMover();//��Ҳ����ʹ���Դ���ʵ����jsp:useBean����ʽ��  
	UploadBean upBean = new UploadBean();
	MultipartFormDataRequest mrequest = null;
	Hashtable files = null;
	if (MultipartFormDataRequest.isMultipartFormData(request)) {
		mrequest = new MultipartFormDataRequest(request, null,100 * 1024 * 1024, MultipartFormDataRequest.COSPARSER,"UTF-8");//ע������ҲҪ���ñ������  
		// ��ȡ�ϴ��ļ�����
		String fileName = mrequest.getParameter("title");
		//������������title�����Ƿ���ȷ�����Ե�ʱ�򣬼�һ��if (true)return;���ɡ�  
		files = mrequest.getFiles();
	}

	// ��ָ̨��·��
	String fileId = mrequest.getParameter("fileId");
	// ���ﵥ��
	String applyId = mrequest.getParameter("applyId");
	// ��˾���
	String comId = mrequest.getParameter("comId");
	// �ļ����
	String seq = mrequest.getParameter("seq");
	// �ļ�����
	String fileType = mrequest.getParameter("fileType");

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

		//ȡ�ļ����ĺ�׺  
		int ii = sLocalFileName.indexOf("."); 
		String sExt = sLocalFileName.substring(ii,sLocalFileName.length());

		// �ļ�������Ϊ ���� + ���к�
		sServerFileName = applyId + seq;
		sServerFileName = sServerFileName + sExt;

		//��������ڸ�Ŀ¼�����½�һ��  
		File dir = new File(sPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		upBean.setFolderstore(sPath);//����Ҫ�ϴ���Ŀ¼  
		upBean.addUploadListener(fileMover);//����filMover����  
		fileMover.setNewfilename(sServerFileName);//���÷������ϵ��ļ���  
		upBean.store(mrequest, "attach");//�ϴ�  
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
//���� URL: http://localhost:8080/jeecg-framework/applyController.do?datagrid&field=id,applyId,date1,patientName,officeId,apofficeId,apcomId,apdate1,status,
	document.forms[0].action = "http://localhost:8080/jeecg-framework/applyController.do?saveFile";
	document.getElementById("formobj").submit();
</script>

