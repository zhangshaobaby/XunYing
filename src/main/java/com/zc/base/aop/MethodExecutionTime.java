/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.base.aop<br/>  
 * <b>文件名：</b>MethodTimeCount.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-16-下午11:52:31<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.util.StopWatch;

/**
 * <b>类名称：</b>MethodTimeCount<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-16 下午11:52:31<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class MethodExecutionTime implements Ordered {

	protected static final Log log = LogFactory.getLog(MethodExecutionTime.class);

	private static final int DEFAULT_MAX_RETRIES = 1;

	private int maxRetries = DEFAULT_MAX_RETRIES;
	private int order = 1;

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Object getMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Object result = pjp.proceed();
		clock.stop(); // 计时结束

		// 方法参数类型，转换成简单类型
		Object[] params = pjp.getArgs();
		String[] simpleParams = new String[params.length];
		for (int i = 0; i < params.length; i++) {
			simpleParams[i] = params[i].toString();
		}
		log.debug("{getMethodExecutionTime:" + clock.getTotalTimeMillis() + "ms,className:"
		        + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()}");
		return result;
	}
	

}
