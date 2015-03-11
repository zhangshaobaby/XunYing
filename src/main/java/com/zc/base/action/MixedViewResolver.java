/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.base.action<br/>  
 * <b>文件名：</b>MixedViewResolver.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-20-下午11:18:38<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.action;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * <b>类名称：</b>MixedViewResolver<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-20 下午11:18:38<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class MixedViewResolver implements ViewResolver {
	private Map<String, ViewResolver> resolvers;

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		int n = viewName.lastIndexOf(".");
		if (n != -1) {
			// 取出扩展名
			String suffix = viewName.substring(n + 1);
			// 取出对应的ViewResolver
			ViewResolver resolver = resolvers.get(suffix);
			if (resolver == null) {
				throw new RuntimeException("No ViewResolver for " + suffix);
			}
			viewName=viewName.substring(0,n);
			return resolver.resolveViewName(viewName, locale);
		} else {
			ViewResolver resolver = resolvers.get("jsp");
			return resolver.resolveViewName(viewName, locale);
		}
	}
}
