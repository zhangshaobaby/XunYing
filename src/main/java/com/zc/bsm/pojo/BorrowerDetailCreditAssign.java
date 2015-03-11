package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 借款人信息 债权转让
 * @author Administrator
 *
 */
@Entity
@Table(name="borrowerDetailCreditAssign")
public class BorrowerDetailCreditAssign {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    private String borrowerCustId;
    @Column(precision = 16, scale = 2)
    private BigDecimal borrowerCreditAmt;
    @Column(precision = 16, scale = 2)
    private BigDecimal prinAmt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBorrowerCustId() {
		return borrowerCustId;
	}
	public void setBorrowerCustId(String borrowerCustId) {
		this.borrowerCustId = borrowerCustId;
	}
	public BigDecimal getBorrowerCreditAmt() {
		return borrowerCreditAmt;
	}
	public void setBorrowerCreditAmt(BigDecimal borrowerCreditAmt) {
		this.borrowerCreditAmt = borrowerCreditAmt;
	}
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}
}
