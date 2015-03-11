package com.zc.bsm.vo.returnVo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
商户扣款对账列表类
 * @author Administrator
 *
 */
@Entity
@Table(name = "TrfReconciliation")
public class ReconciliationDtoResult {
// 主键 UUID增长
	//主键 UUID增长
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY)  
private Long id;private String OrdId ;private String MerCustId  ;private String UsrCustId  ;private String TransAmt ;private String TransStat ;private String PnrDate  ;private String PnrSeqId  ;

public String getOrdId() {
	return OrdId;
}
public void setOrdId(String ordId) {
	OrdId = ordId;
}
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
