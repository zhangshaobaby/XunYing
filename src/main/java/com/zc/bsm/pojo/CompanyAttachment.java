package com.zc.bsm.pojo;

import java.util.Date;

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
* 类名： zcCompanyAttachment.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="CompanyAttachment")
public class CompanyAttachment  extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 担保/信托公司id
     */
    @Column(columnDefinition="TEXT")
    private Long cid;
	
    /**
     * 附件名称
     */
    @Column(length = 50)
    private String name;
    /**
     * 附件地址
     */
    @Column(length = 500)
    private String url;
    /**
     * 附件原名称
     */
    @Column(length = 500)
    private String names;
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
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 创建人
	 */
	@Column	(length = 10)
	private String createOper;
	/**
	 * 修改者
	 */
	@Column	(length = 10)
	private String updateOper;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateOper() {
		return createOper;
	}
	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}
	public String getUpdateOper() {
		return updateOper;
	}
	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
}