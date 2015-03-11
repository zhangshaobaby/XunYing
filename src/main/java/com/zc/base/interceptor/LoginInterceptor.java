package com.zc.base.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.ParseAnnotation;
import com.zc.base.util.SignUtils;
import com.zc.bsm.pojo.User;

public class LoginInterceptor extends ParseAnnotation implements
		HandlerInterceptor {
	protected static final Log log = LogFactory.getLog(LoginInterceptor.class);
	private String mappingURL;// 利用正则映射到需要拦截的路径

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug("请求路径" + request.getRequestURL());
		//检查是否需要拦截Action
			Method method = (Method) PropertyUtils.getProperty(handler,
					"method");
			String needInterceptor = this.parseMethod(method);
			if (needInterceptor != null && needInterceptor.equals("no")) {
				return true;
			}
		HttpSession session = request.getSession();
		if(SignUtils.AUTH_Filter){//如果是内部版
			Object menus = session.getAttribute("menus");
			if (menus == null) {
				String projectName = request.getContextPath();
				//response.sendRedirect(projectName + "/auth/login/dologin");		
				
				 PrintWriter out = response.getWriter();		
    		     out.println("<html>");  
    		     out.println("<script type='text/javascript'>");  
    		     out.println("window.open ('"+projectName+"/auth/login/dologin','_top')");  
    		     out.println("</script>");  
    		     out.println("</html>");
				return false;
			}
		}else{//如果是外部版
			User currUser = (User) session.getAttribute("user");		
			if (currUser == null) {
				String projectName ="";		
				 if(request.getServerName().indexOf("1717tou.com")>=0){
					 projectName = "https://"+request.getServerName()+request.getContextPath();  
				 }else{
					projectName = request.getContextPath();						 
				 }
	
				 PrintWriter out = response.getWriter();		
    		     out.println("<html>");  
    		     out.println("<script type='text/javascript'>");  
    		     out.println("window.open ('"+projectName+"/dologin','_top')");  
    		     out.println("</script>");  
    		     out.println("</html>");
				return false;
			}else{
				//判断是否需要开通第三方支付
				String needHfInterceptor = this.parseOpenHFPayMethod(method);
				if (needHfInterceptor != null && needHfInterceptor.equals("no")) {
					return true;
				}
				//判断是否开通第三方支付
				if(currUser.getHuifuID()==null||currUser.getHuifuID().equals("")){
					String projectName = request.getContextPath();
					//response.sendRedirect(projectName + "/dologin");	
					 PrintWriter out = response.getWriter();		
	    		     out.println("<html>");  
	    		     out.println("<script type='text/javascript'>");  
	    		     out.println("window.open ('"+projectName+"/user/hfpay','_top')");  
	    		     out.println("</script>");  
	    		     out.println("</html>");
	    		 	return false;
				}
				
			}
		}
		return true;
	}
}