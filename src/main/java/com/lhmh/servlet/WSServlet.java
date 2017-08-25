package com.lhmh.servlet; 

import com.lhmh.server.Java6WS; 

import javax.servlet.*; 
import javax.xml.ws.Endpoint; 
import java.io.IOException; 

/** 
* 发布Java6 WebService的Servlet， 
* 
* @author leizhimin 2009-11-16 13:52:49 
*/ 
public class WSServlet extends GenericServlet { 
        @Override 
        public void init(ServletConfig servletConfig) throws ServletException { 
                super.init(servletConfig); 
                System.out.println("准备启动WebService服务：http://127.0.0.1:8888/java6ws/Java6WS"); 
                //发布一个WebService 
                Endpoint.publish("http://127.0.0.1:8888/java6ws/Java6WS", new Java6WS()); 
                System.out.println("已成功启动WebService服务：http://127.0.0.1:8888/java6ws/Java6WS"); 

        } 

        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException { 
                System.out.println("此Servlet不处理任何业务逻辑，仅仅yonglai发布一个Web服务：http://127.0.0.1:8888/java6ws/Java6WS"); 
        } 
}