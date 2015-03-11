package com.zc.bsm.pojo;

import java.math.BigDecimal;
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

import com.zc.base.po.Dict;

/**
 * 债权转让投标(已用tender表实现 )
 * @author 张少
 *
 */
@Entity
@Table
public class CreditAssignTender {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	//转让人
    String  sellCustId;
    //转让金额
    @Column(precision = 16, scale = 2)
    BigDecimal creditAmt;
    //承接金额
    @Column(precision = 16, scale = 2)
    BigDecimal creditDealAmt;
    //债权转让明细
    @ManyToMany
	@JoinTable(name="CreditAssignTender_bidDetail",joinColumns=@JoinColumn(name="creditAssignId"),inverseJoinColumns=@JoinColumn(name="bidDetailId"))
    List<BidDetail> bidDetails;
    //扣款手续费
    @Column(precision = 16, scale = 2)
    BigDecimal fee;
    //承接人客户
    String buyCustId;
    //创建时间
    String createTime;
    //修改时间
    String updateTime;
    //成功标志 0 成功 1 失败
    Integer flag;
    //转让描述
    String respDesc;
    /**
	 * 状态  -1失败 0 等待  1成功
	 */
	@Column(length=4)
	private Integer state;
	private String ordDate;
	//所属转让申请
	@ManyToOne
	@JoinColumn(name="creditAssignApplyid")
    private   CreditAssignPoApply creditAssignApply;
	//形成的债权转让标
	@OneToMany(mappedBy="creditAssignTenderId")
	private List<Tender> tenderList;
	//作为订单ID ，传给汇付。默认从TenderList 拿第一个主键
	private Long orderid;
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
	
	
	
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getBuyCustId() {
		return buyCustId;
	}
	public void setBuyCustId(String buyCustId) {
		this.buyCustId = buyCustId;
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
	public void setBidDetails(List<BidDetail> bidDetails) {
		this.bidDetails = bidDetails;
	}
	public List<BidDetail> getBidDetails() {
		return bidDetails;
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

	public Integer getState() {
		return state;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public CreditAssignPoApply getCreditAssignApply() {
		return creditAssignApply;
	}
	public void setCreditAssignApply(CreditAssignPoApply creditAssignApply) {
		this.creditAssignApply = creditAssignApply;
	}
	
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public List<Tender> getTenderList() {
		return tenderList;
	}
	public void setTenderList(List<Tender> tenderList) {
		this.tenderList = tenderList;
	}
	
}
