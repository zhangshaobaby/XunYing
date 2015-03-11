package com.zc.bsm.vo.returnVo;

public class AcctDetailResult {
private String AcctType;
private String SubAcctId;
private String AvlBal;
private String AcctBal;
private String FrzBal;

public String getAcctType() {
	return AcctType;
}
public void setAcctType(String acctType) {
	AcctType = acctType;
}
public String getSubAcctId() {
	return SubAcctId;
}
public void setSubAcctId(String subAcctId) {
	SubAcctId = subAcctId;
}
public String getAvlBal() {
	return AvlBal;
}
public void setAvlBal(String avlBal) {
	AvlBal = avlBal;
}
public String getAcctBal() {
	return AcctBal;
}
public void setAcctBal(String acctBal) {
	AcctBal = acctBal;
}
public String getFrzBal() {
	return FrzBal;
}
public void setFrzBal(String frzBal) {
	FrzBal = frzBal;
}
}
