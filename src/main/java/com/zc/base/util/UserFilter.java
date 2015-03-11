package com.zc.base.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
public class UserFilter implements Filter {

	private final static double DEFAULT_USERID = Math.random() * 100000.0;

	public void destroy() {
	}

	/*
	 * 获取操作员名称
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		MDC.put("ip", req.getRemoteAddr());
		MDC.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		if (session != null) {
			AcOperator loginOperator = (AcOperator) session
					.getAttribute("acOperator");
			if (loginOperator != null && loginOperator.getUserid() != null
					&& loginOperator.getOperatorname() != null
					&& !"".equals(loginOperator.getId())
					&& !"".equals(loginOperator.getOperatorname())) {
				MDC.put("userId", loginOperator.getUserid());
				MDC.put("userName", loginOperator.getOperatorname());
			} else {
				MDC.put("userId", DEFAULT_USERID);
				MDC.put("userName", DEFAULT_USERID);
			}
		} else {
			MDC.put("userId", DEFAULT_USERID);
			MDC.put("userName", DEFAULT_USERID);
		}
		chain.doFilter(request, response);*/
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
