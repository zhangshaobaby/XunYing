package com.zc.bsm.vo.returnVo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zc.base.util.TransSubmit;

/**
 * 放还款对账列表类
 * @author Administrator
 *
 */
@Entity
@Table(name="Reconciliation")
public class ReconciliationResult {
	//主键 UUID增长
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;private String OrdId ;private String OrdDate  ;private String MerCustId  ;private String InvestCustId  ;private String BorrCustId ;private String TransAmt ;private String TransStat ;private String PnrDate  ;private String PnrSeqId  ;
public String getOrdId() {
	return OrdId;
}
public void setOrdId(String ordId) {
	OrdId = ordId;
}
public String getOrdDate() {
	return OrdDate;
}
public void setOrdDate(String ordDate) {
	OrdDate = ordDate;
}
public String getMerCustId() {
	return MerCustId;
}
public void setMerCustId(String merCustId) {
	MerCustId = merCustId;
}
public String getInvestCustId() {
	return InvestCustId;
}
public void setInvestCustId(String investCustId) {
	InvestCustId = investCustId;
}
public String getBorrCustId() {
	return BorrCustId;
}
public void setBorrCustId(String borrCustId) {
	BorrCustId = borrCustId;
}
public String getTransAmt() {
	return TransAmt;
}
public void setTransAmt(String transAmt) {
	TransAmt = transAmt;
}
public String getTransStat() {
	return TransStat;
}
public void setTransStat(String transStat) {
	TransStat = transStat;
}
public String getPnrDate() {
	return PnrDate;
}
public void setPnrDate(String pnrDate) {
	PnrDate = pnrDate;
}
public String getPnrSeqId() {
	return PnrSeqId;
}
public void setPnrSeqId(String pnrSeqId) {
	PnrSeqId = pnrSeqId;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
}
