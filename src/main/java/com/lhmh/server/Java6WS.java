package com.lhmh.server; 

import javax.jws.WebService; 

/** 
* Java6开发的WebService服务端 
* 
* @author leizhimin 2009-11-16 10:24:13 
*/ 
@WebService 
public class Java6WS { 

        /** 
         * Web服务中的业务方法 
         * 
         * @return 一个字符串 
         */ 
        public String doSomething(String username) { 
                return username + " is doing something!"; 
        } 
}