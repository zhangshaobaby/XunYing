package com.zc.tdl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;

public class PicHostTag extends SimpleTagSupport{
	public void doTag() throws JspException,IOException{
		this.getJspContext().getOut().write(SignUtils.PIC_HOST);
	}
}
