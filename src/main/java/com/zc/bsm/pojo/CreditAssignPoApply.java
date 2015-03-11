package com.zc.bsm.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zc.base.po.Dict;

/**
 * 债权转让申请表
 *@author Administrator
 *
 */
@Entity
@Table(name="creditAssignApply")
public class CreditAssignPoApply {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    //转让人
    private String sellCustId;
    //转让本金额
    @Column(precision = 16, scale = 2)
    private BigDecimal creditAmt;
    //转让价格
    @Column(precision = 16, scale = 2)
    private BigDecimal creditDealAmt;
    //转让的产品
    @ManyToOne
    private  Project project;
    //债权转让明细
    @ManyToMany
	@JoinTable(name="CreditAssignApply_bidDetail",joinColumns=@JoinColumn(name="creditAssignId"),inverseJoinColumns=@JoinColumn(name="bidDetailId"))
    List<BidDetail> bidDetails;
    //剩余天数
    private String diffDays;
    //状态 转让中 51，转让成功52  无人购买53  已撤销 54
    @ManyToOne
    @JoinColumn(name="state")
    private Dict state;
    //创建时间
	@Column	
  String createTime;
  //修改时间
	@Column	
  String updateTime;
	//日期
  String applydate;	
  //开始时间
  String startTime;
  //结束时间
  String endTime;
    public CreditAssignPoApply() {
	super();
	// TODO Auto-generated constructor stub
}
	public CreditAssignPoApply(Long id) {
	super();
	this.id = id;
}
	//非数据库字段 用于支持JSP传递list
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSellCustId() {
		return sellCustId;
	}
	public void setSellCustId(String sellCustId) {
		this.sellCustId = sellCustId;
	}
	public BigDecimal getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}
	public BigDecimal getCreditDealAmt() {
		return creditDealAmt;
	}
	public void setCreditDealAmt(BigDecimal creditDealAmt) {
		this.creditDealAmt = creditDealAmt;
	}
	public String getDiffDays() {
		return diffDays;
	}
	public void setDiffDays(String diffDays) {
		this.diffDays = diffDays;
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
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<BidDetail> getBidDetails() {
		return bidDetails;
	}
	public void setBidDetails(List<BidDetail> bidDetails) {
		this.bidDetails = bidDetails;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
