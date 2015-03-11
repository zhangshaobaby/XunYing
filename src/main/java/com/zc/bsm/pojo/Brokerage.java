package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zc.base.po.Dict;
import com.zc.base.po.baseModel;
@Entity
@Table(name="Brokerage")
//服务费和佣金表
public class Brokerage {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    //推荐人或理财顾问受益用户
    @ManyToOne
	@JoinColumn(name = "revenueUserId")
    private User revenueUser;

    //产生交易的用户
    @ManyToOne
	@JoinColumn(name = "investUserId")
    private User investUser;
    //交易金额
    @Column(precision = 16, scale = 2)
    private BigDecimal transAmt;
    //佣金
    @Column(precision = 16, scale = 2)
    private BigDecimal brokerage;
	//产品
    @ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;
	//标
    @ManyToOne
	@JoinColumn(name = "tenderId")
	private Tender tender;
	//受益人类型 1推荐人 2理财顾问
	private Integer agentType;
	//交易状态  60 发放成功 or 61 提取中or 62 提现失败 or 63 提现成功  or 68 交易中（还没成立）  or 69产品购买失败(失败退还本金||产品撤销)
    @ManyToOne
	@JoinColumn(name = "state")
	private Dict state;
	@Column
	String createTime;
	@Column
	String rewardTime;
	// 修改时间
	@Column
	String updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getInvestUser() {
		return investUser;
	}
	public void setInvestUser(User investUser) {
		this.investUser = investUser;
	}
	public BigDecimal getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getAgentType() {
		return agentType;
	}
	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}
	public Dict getState() {
		return state;
	}
	public void setState(Dict state) {
		this.state = state;
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
	public User getRevenueUser() {
		return revenueUser;
	}
	public void setRevenueUser(User revenueUser) {
		this.revenueUser = revenueUser;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public Tender getTender() {
		return tender;
	}
	public void setTender(Tender tender) {
		this.tender = tender;
	}
	public String getRewardTime() {
		return rewardTime;
	}
	public void setRewardTime(String rewardTime) {
		this.rewardTime = rewardTime;
	}

	
}
