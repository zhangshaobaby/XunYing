package com.zc.tdl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zc.base.util.StringUtil;

public class ToMoneyTag extends SimpleTagSupport{
	private String var;
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public void doTag() throws JspException,IOException{
		if(var==null||var.equals("")){
			var="0";
		}
		this.getJspContext().getOut().write(StringUtil.BigDecimal2StringSmall(new BigDecimal(var)));
	}
}
