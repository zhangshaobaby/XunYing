package com.zc.tdl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.zc.base.util.StringUtil;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.impl.EnchashmentServiceImpl;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;

public class selectBalanceTag extends SimpleTagSupport{
	private String value;
	public void doTag() throws JspException,IOException{
		EnchashmentService	enchashmentService=new EnchashmentServiceImpl();
		QueryBalanceBgReturn qbr = enchashmentService
		.getBalanceObj(value);
		String result="0";
		if(qbr!=null){
			 result=qbr.getAvlBal();
		}
		
			this.getJspContext().getOut().write(result);
		}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
