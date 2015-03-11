/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.base.action<br/>  
 * <b>文件名：</b>Action.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-20-下午06:55:42<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zc.base.util.Page;
import com.zc.bsm.service.bizDataService;

/**
 * <b>类名称：</b>Action<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-20 下午06:55:42<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public abstract class Action {

	protected static final Log log = LogFactory.getLog(Action.class);
    public String msg;
    public String ERROR="error";
    public String HRERROR="hrerror";
    public String SUCCESS="success";
    public Page page;
    public List list;  
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
