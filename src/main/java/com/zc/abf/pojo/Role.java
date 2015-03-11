package com.zc.abf.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zc.base.po.Dict;
@Entity
@Table
public class Role {
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
	@Column
	private String roleCode; // 角色编号
	@Column
	private String roleName; // 角色名称
	@Column
	private String roleDesc; // 角色描述
	//-->角色菜单集合
	@OneToMany(mappedBy = "roleid", cascade = CascadeType.ALL)
	private Set<RoleMenu> roleMenus;
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Set<RoleMenu> getRoleMenus() {
		return roleMenus;
	}
	public void setRoleMenus(Set<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}
	
}
