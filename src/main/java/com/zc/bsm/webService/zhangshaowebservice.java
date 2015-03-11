package com.zc.bsm.webService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(name = "zhangService", serviceName = "zhangService", targetNamespace = "http://webService.bsm.zc.com")
public class zhangshaowebservice {
 
	public String zhangshaofangfa(String name,int age){
		return "我的名字"+name+"我的年龄"+age;		
	}
public static void main(String[] args) {
	Endpoint.publish("http://localhost:8089/zhangshao", new zhangshaowebservice());
	System.out.println("发布成功");
}	
}
