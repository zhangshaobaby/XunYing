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
* 类描述： 后台操作日志
* 创建者： zl
* 项目名称： open1.1
* 类名： OperatorLog.java
* 创建时间： 2014-11-5
 */
@Entity
@Table(name="OperatorLog")
public class OperatorLog extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    /**
     * 行为
     */
    @Column(length = 50)
    private String behavior;
    /**
	 * 操作员id
	 */
	@Column(length = 50)
	private String oid;
	/**
	 * 操作员名称
	 */
	@Column(length = 50)
	private String operatorname;
	/**
	 * json化对象（初定）
	 */
	@Column(columnDefinition="TEXT")
	private String object;
	@Column	
	String createTime;
	//修改时间
	@Column	
	String updateTime;
	//创建者
	@Column	
	String createOper;
	//修改者
	@Column	
	String updateOper;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
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
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
}
