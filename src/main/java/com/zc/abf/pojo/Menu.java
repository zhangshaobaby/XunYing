package com.zc.abf.pojo;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table
public class Menu {
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
	@Column
	@NotEmpty//表单校验的注解
	private String menuName; // '菜单名称'
	//父菜单
	@ManyToOne
	private Menu parentMenu; 
	//父菜单
	@OneToMany(mappedBy = "parentMenu", cascade = CascadeType.ALL)
	private Collection<Menu> subMenuList; 
	@Column
	@NotEmpty
	private String menuLabel; //  '菜单显示（中文）',
	@Column
	@NotEmpty
	private String menuCode; // '菜单代码',	
	@Column
	private String url;//点击菜单的路径
	@Column
	private String menuClass;//菜单样式
	@Column
	private String menuIcon; //菜单的图标
	//关联功能
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
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLabel() {
		return menuLabel;
	}
	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public Collection<Menu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(Collection<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

}
