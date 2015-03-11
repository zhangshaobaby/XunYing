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
import javax.persistence.Table;

/**
 * 债权转让明细
 * @author Administrator
 *
 */
@Entity
@Table(name="bidDetail")
public class BidDetail {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    private String   bidOrdId;
    private String bidOrdDate;
    @Column(precision = 16, scale = 2)
    private BigDecimal bidCreditAmt;
    @ManyToMany
	@JoinTable(name="bidDetail_borrowerDetail",joinColumns=@JoinColumn(name="bidDetailId"),inverseJoinColumns=@JoinColumn(name="borrowerDetailId"))
    private List<BorrowerDetailCreditAssign> borrowerDetails;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBidOrdId() {
		return bidOrdId;
	}
	public void setBidOrdId(String bidOrdId) {
		this.bidOrdId = bidOrdId;
	}
	public String getBidOrdDate() {
		return bidOrdDate;
	}
	public void setBidOrdDate(String bidOrdDate) {
		this.bidOrdDate = bidOrdDate;
	}
	public BigDecimal getBidCreditAmt() {
		return bidCreditAmt;
	}
	public void setBidCreditAmt(BigDecimal bidCreditAmt) {
		this.bidCreditAmt = bidCreditAmt;
	}
	public List<BorrowerDetailCreditAssign> getBorrowerDetails() {
		return borrowerDetails;
	}
	public void setBorrowerDetails(List<BorrowerDetailCreditAssign> borrowerDetails) {
		this.borrowerDetails = borrowerDetails;
	}
}
