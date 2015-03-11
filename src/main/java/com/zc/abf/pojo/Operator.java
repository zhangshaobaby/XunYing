package com.zc.abf.pojo;


import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zc.base.po.Dict;
@Entity
@Table
public class Operator {
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
	// 操作员状态
	// 正常，挂起，注销，锁定...',
	@ManyToOne
	Dict flag;
	//登陆名
	@Column
	private String userid; // USERID varchar(64) not null comment '登录用户名',
	//密码
	@Column
	private String pwd; // PASSWORD varchar(100) comment '密码',
	//操作名称
	@Column
	private String operatorname;// OPERATORNAME varchar(64) comment '操作员名称',
	// 操作员描述
	private String operatordesc;
	//多个角色
	@ManyToMany
	@JoinTable(name="operRole",joinColumns=@JoinColumn(name="oper"),inverseJoinColumns=@JoinColumn(name="role"))
	private List<Role> roles;
	//邮箱地址
	@Column
	private String email; // EMAIL varchar(255) comment '邮箱地址'
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
	public Dict getFlag() {
		return flag;
	}
	public void setFlag(Dict flag) {
		this.flag = flag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOperatordesc() {
		return operatordesc;
	}
	public void setOperatordesc(String operatordesc) {
		this.operatordesc = operatordesc;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Role> getRoles() {
		return roles;
	}
}
