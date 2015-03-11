package com.zc.bsm.vo.returnVo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SaveReconciliation")
public class SaveReconciliationResult {
	//主键 UUID增长
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	private String MerCustId;		
	private String UsrCustId;	
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String TransStat;
	private String GateBusiId;
	private String OpenBankId;
	private String OpenAcctId;
	private String 	FeeAmt;
	private String FeeCustId;
	private String FeeAcctId;
	public String getMerCustId() {
		return MerCustId;
	}
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
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
	public String getGateBusiId() {
		return GateBusiId;
	}
	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}
	public String getOpenBankId() {
		return OpenBankId;
	}
	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}
	public String getOpenAcctId() {
		return OpenAcctId;
	}
	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}
	public String getFeeAmt() {
		return FeeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}
	public String getFeeCustId() {
		return FeeCustId;
	}
	public void setFeeCustId(String feeCustId) {
		FeeCustId = feeCustId;
	}
	public String getFeeAcctId() {
		return FeeAcctId;
	}
	public void setFeeAcctId(String feeAcctId) {
		FeeAcctId = feeAcctId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	
}
