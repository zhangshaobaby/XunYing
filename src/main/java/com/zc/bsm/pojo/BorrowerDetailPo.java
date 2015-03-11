package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
/**
 *放标时 借款人详情
 */
public class BorrowerDetailPo {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id; 
    @Column
	private String borrowerCustId;
    @Column(precision = 16, scale = 2)
	private BigDecimal borrowerAmt;
    @Column
	private String borrowerRate;
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
	public BigDecimal getBorrowerAmt() {
		return borrowerAmt;
	}
	public void setBorrowerAmt(BigDecimal borrowerAmt) {
		this.borrowerAmt = borrowerAmt;
	}
	public String getBorrowerRate() {
		return borrowerRate;
	}
	public void setBorrowerRate(String borrowerRate) {
		this.borrowerRate = borrowerRate;
	}
	
	
	
}
