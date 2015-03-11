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
* 类描述： 还款审核
* 创建者： zl
* 项目名称： open1.1
* 类名： Advert.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="RepaymentPlanVerify")
public class RepaymentPlanVerify extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
	 * pid
	 */
	@Column
	private Long projectId;

	/**
	 * 还款期数  0 为本金  1为利息
	 */
	@Column(length = 10)
	private Integer repayCount;
	/**
	 * 预计还款时间
	 */
	@Column(length = 50)
	private String repayTime;
	/**
	 * 还款金额
	 */
	@Column(length = 20)
	private BigDecimal repayMoney;
	
	/**
	 * 状态 -1拒绝  0待审  1审核通过  2已完成
	 */
	@Column
	private Integer state;
	/**
	 * 创建人id
	 */
	@Column(length = 10)
	private Integer createOper;
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
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Integer getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(Integer repayCount) {
		this.repayCount = repayCount;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public BigDecimal getRepayMoney() {
		return repayMoney;
	}
	public void setRepayMoney(BigDecimal repayMoney) {
		this.repayMoney = repayMoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCreateOper() {
		return createOper;
	}
	public void setCreateOper(Integer createOper) {
		this.createOper = createOper;
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
}
