package com.lhmh.client;   
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.encoding.XMLType;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;

//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;


public class Client {  
	public static void main1(String[] args) {   
		///HelloWorldImplService service = new HelloWorldImplService();    
		//HelloWorld port = service.getHelloWorldImplPort();        
		//System.out.println(port.hello("����"));  
		//HelloWorldImplService service = new HelloWorldImplService();    
		//HelloWorld port = service.getHelloWorldImplPort();        
		//System.out.println(port.hello("����"));  
		
		String wsdlUrl ="http://localhost:8989/axis/HelloWorld.jws?wsdl";
		 String nameSpaceUri ="http://localhost:8989/axis/HelloWorld.jws";
		String serviceName = "HelloWorldService";
		String portName = "HelloWorld";
		 
		// ServiceFactory serviceFactory = ServiceFactory.newInstance();
		// Service afService =serviceFactory.createService(new URL(wsdlUrl),new QName(nameSpaceUri, serviceName));
		//  HelloWorldInterface proxy = (HelloWorldInterface)afService.getPort(new QName(nameSpaceUri, portName),HelloWorldInterface.class);
		// System.out.println("return value is "+proxy.getName("john") ) ;
		}  
	
	public static void main(String[] args) {
		//���Եķ������
		String srvName="��ѯҵ��ϵͳ�Ŀͻ�����";
		try {
			//�޸�����XML         			
           // String inXml = "<XSM><DATASET ORG_CODE='11150201' CUST_CODE = '150207105765' ORDER_DATE='20140808'></DATASET></XSM>";
			
			//�޸�V3�����ַIP
            String url ="http://10.25.176.193/eco/axis/XsmCoService?wsdl";
			//���񷽷�		
			String operation = "doSomething";
			//�������
			String arg = "inXml";

		/*	Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("http://soapinterop.org/",operation));
			call.addParameter(arg, XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			System.out.println("��ʼ�������");
			String ret = (String) call.invoke(new Object[] {inXml});
			 
			System.out.println(srvName+"���أ�"+ret);*/
 
			
		} catch (Exception e) {
			System.out.println(srvName+"���?"+e);
		}
	}


	} 