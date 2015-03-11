package com.zc.bsm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zc.base.po.baseModel;

/**
 * 
* 类描述： 产品
* 创建者： zl
* 项目名称： open1.1
* 类名： zcProject.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="Company")
public class Company  extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 担保/信托公司名称
     */
    @Column
    private String company_name;
	/**
	 * 担保/信托公司详情
	 */
	@Column(columnDefinition="TEXT")
	private String company_content;
	/**
	 * 担保/信托公司详情
	 */
	@Column(length = 50)
	private String logo;
	/**
	 * 担保/信托  0担保 1信托
	 */
	@Column
	private Integer type;
	/**
	 * 创建人id
	 */
	@Column(length = 10)
	private Integer create_user;
	/**
	 * 创建时间
	 */
	@Column	
	private String createTime;
	/**
	 * 创建人名称
	 */
	@Column(length = 10)
	private String create_username;
	/**
	 * 删除标识
	 */
	@Column(length = 10)
	private String flag;
	/**
	 * 删除时间
	 */
	@Column	(length = 20)
	private String updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_content() {
		return company_content;
	}
	public void setCompany_content(String company_content) {
		this.company_content = company_content;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public String getCreate_username() {
		return create_username;
	}
	public void setCreate_username(String create_username) {
		this.create_username = create_username;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
