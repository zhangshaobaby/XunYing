package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zc.base.po.baseModel;

/**
 * 
* 类描述： 积分流水表
* 创建者： zl
* 项目名称： open1.1
* 类名： ScoreLog.java
* 创建时间： 2014-10-29 
 */
@Entity
@Table(name="ScoreLog")
public class ScoreLog extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    
    /**
     * 来源
     */
    @Column
    private String comeFrom;
    /**
     * uid
     */
    @Column
    private String uid;
    /**
     *  id
     */
    private Long foreId;
    /**
     * 涉及金额
     */
    @Column	(precision = 8, scale = 2)
    private BigDecimal money;
    /**
     * 换取积分数额
     */
    @Column	(precision = 8, scale = 2)
    private BigDecimal score;
    
    /**
     * 摘要
     */
    @Column
    private String summary;
	//创建者
	@Column	
	String createOper;
	//修改者
	@Column	
	String updateOper;
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 删除标识
	 */
	@Column(length = 10)
	private String flag;
	/**
	 * 更新时间
	 */
	@Column	(length = 20)
	private String updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public Long getForeId() {
		return foreId;
	}
	public void setForeId(Long foreId) {
		this.foreId = foreId;
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
