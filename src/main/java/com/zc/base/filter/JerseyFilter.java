package com.zc.base.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.base.util.Encrypt;

public class JerseyFilter implements Filter {
	
	private final String errorPath = "/login.html";
	private final String limitIp="0:0:0:0:0:0:0:1";
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("调用init");
	}

	public void destroy() {
		System.out.println("调用destroy");
	}

	// 解决post方式的乱码问题
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//		if(request.getRemoteAddr().equals(limitIp)){
//			//String words = request.getHeader("");
//			String words = request.getParameter(">.<");
//			if(words!=null&&!words.equals("")){
//				Encrypt abc = new Encrypt();
//				abc.setKey();
//				abc.setDesString(words);   //将要解密的密文传送给Encrypt.java进行解密计算。
//				String M=abc.getStrM(); //调用get函数获取解密后明文。
//				try{
//					Date date = new Date();
//					Long now = date.getTime();
//					Long time = Long.parseLong(M);
//					if(now<time||now>(time+5000)){
//						//错误
//						response.sendRedirect(request.getContextPath()+errorPath);
//					}
//				}catch(Exception e){
//					//错误
//					response.sendRedirect(request.getContextPath()+errorPath);
//				}
//			}else{
//				//错误
//				response.sendRedirect(request.getContextPath()+errorPath);
//			}
//		}else{
//			//错误
//			response.sendRedirect(request.getContextPath()+errorPath);
//		}
		chain.doFilter(request, response);
	}
}
