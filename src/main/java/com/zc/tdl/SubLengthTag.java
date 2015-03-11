package com.zc.tdl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SubLengthTag extends SimpleTagSupport{
	private String var;
	private int length;
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void doTag() throws JspException,IOException{
		//System.out.println(var);
		String a = "";
		String content = "";
		int b = 0;
		int i = 0 ;
		for(; i<length &&i<var.length()+i; i++){
			a = var.substring(0,1);
			if(a.matches("[a-zA-Z0-9]")){
				b++;
				if(b%2!=0)
				length++;
			}
			var = var.substring(1);
			content+=a;
		}
		if(i<length||var.equals("")){
			this.getJspContext().getOut().write(content);
		}else{
			this.getJspContext().getOut().write(content+"...");
		}
	}
}
