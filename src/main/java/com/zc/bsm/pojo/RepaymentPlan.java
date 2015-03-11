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
* 类描述： 产品还款计划
* 创建者： zl
* 项目名称： open1.1
* 类名： RepaymentPlan.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="repaymentPlan")
public class RepaymentPlan {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * uid
     */
    @Column
    private String userId;
    /**
     * uid
     */
    @Column
    private String usrCustId;
    /**
	 * pid
	 */
	@Column
	private Long projectId;
	/**
	 * 投标id
	 */
	private Long tenderId;
	/**
	 * 投标时间
	 */
	private String ordDate;
	/**
	 * 预计还款时间
	 */
	@Column(length = 50)
	private String repayTime;
	/**
	 * 还款期数  0 为本金  其余为利息
	 */
	@Column(length = 10)
	private Integer repayCount;
	/**
	 * 预计收益
	 */
	@Column(length = 20)
	private BigDecimal repayMoney;
	/**
	 * 状态  -1失败  1成功 2无效 0发送中
	 */
	@Column(length = 10)
	private Integer state;
	/**
	 * 还款接口类型  0 还款接口  1 平台转账接口  
	 */
	@Column(length = 10)
	private Long interType;
	/**
	 * 还款发起者类型 1 用户行为（后台不能忽略）
	 */
	@Column(length = 10,name="cType")
	private String cType;
	/**
	 * 删除标识 0有效 1无效
	 */
	@Column(length = 10)
	private Integer flag;
	/**	
	 * 	申请还款日
	 */
	@Column(length = 20)
	private String applyTime;
	@Column	
	private String createTime;
	@Column	
	private String updateTime;
	@Column	
	private String createOper;
	@Column	
	private String updateOper;
    //还款的的计息开始时间
	@Column(length = 20)
	private String start_time;
    //还款的计息结束时间 理论等于预期还款时间
	@Column	(length = 20)
	private String end_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getTenderId() {
		return tenderId;
	}
	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public Integer getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(Integer repayCount) {
		this.repayCount = repayCount;
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	public Long getInterType() {
		return interType;
	}
	public void setInterType(Long interType) {
		this.interType = interType;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getCType() {
		return cType;
	}
	public void setCType(String type) {
		cType = type;
	}


	
}
