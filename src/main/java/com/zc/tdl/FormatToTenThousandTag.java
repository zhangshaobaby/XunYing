package com.zc.tdl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zc.base.util.StringUtil;

public class FormatToTenThousandTag extends SimpleTagSupport{
	private String value;
	public void doTag() throws JspException,IOException{
	 String result=	StringUtil.FormatToTenThousand(value);
			this.getJspContext().getOut().write(result);
		}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
