package com.zc.base.filter;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.zc.base.util.SignUtils;

public class AuthFilter implements Filter {	
	
	private final String errorPath = "/login.html";
    static final String[] MAIN_URL= {"/auth/"};
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("AuthFilter 调用init");
	}

	public void destroy() {
		System.out.println("AuthFilter 调用destroy");
	}


	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
	       boolean innerroot=false;
	       String path = request.getRequestURI();
	       
	    	for (String tmpurl: MAIN_URL){
	    		if (path.contains(tmpurl) || path.contains(tmpurl.toUpperCase()) || path.contains(tmpurl.toLowerCase())){
	    			innerroot=true;
	    			}
	    		}
	    	
			if(!SignUtils.AUTH_Filter){
				if(innerroot){
					String projectName ="";		
					 if(request.getServerName().indexOf("1717tou.com")>=0){
						 projectName = "https://"+request.getServerName()+request.getContextPath();  
					 }else{
						projectName = request.getContextPath();						 
					 }
					
					//response.sendRedirect(projectName + "/dologin");
					 PrintWriter out = response.getWriter();		
        		     out.println("<html>");  
        		     out.println("<script type='text/javascript'>");  
        		     out.println("window.open ('"+projectName+"/dologin','_top')");  
        		     out.println("</script>");  
        		     out.println("</html>");
        		     return ;
				}else{
					 chain.doFilter(request, response);
					 
				}
			}else{
				 
				 chain.doFilter(request, response);
				 		}

	}
}
