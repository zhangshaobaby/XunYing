package com.zc.base.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.action.Action;

public class FunctionInterceptor implements HandlerInterceptor {
	protected static final Log log = LogFactory.getLog(LoginInterceptor.class);
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
	   String functionid=request.getParameter("functionid");
		HttpSession session = request.getSession();
		String functionids = (String) session.getAttribute("funcs");
	   if(functionid!=null){
		   if(functionids.contains(functionid)){
			   return true;
		   }else{
			   return false;
		   }
	   }
       return true;  
	}
}
