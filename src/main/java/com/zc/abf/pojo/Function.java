package com.zc.abf.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Function {
	// 主键 UUID增长
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	// 创建时间
	@Column
	String createTime;
	// 修改时间
	@Column
	String updateTime;
	// 功能编号
	@Column(name = "funccode")
	private String funccode; 
	// 功能名称
	@Column(name = "funcname")
	private String funcname; 
	@Column(name = "funcdesc")
	// 功能描述
	private String funcdesc; 
	//功能类型
	@Column(name = "functype")
	private String functype;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	public String getFuncdesc() {
		return funcdesc;
	}
	public void setFuncdesc(String funcdesc) {
		this.funcdesc = funcdesc;
	}
	public String getFunctype() {
		return functype;
	}
	public void setFunctype(String functype) {
		this.functype = functype;
	} 
	
}
