package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class QueryBalanceBgReturn {private String CmdId;private String RespCode ;private String RespDesc  ;private String MerCustId   ;private String UsrCustId   ;
//可用余额private String AvlBal   ;
//AcctBal账户余额private String AcctBal   ;
//冻结余额private String FrzBal   ;private String checkCode="CmdId + RespCode + MerCustId + UsrCustId+ AvlBal+ AcctBal+ FrzBal+";
public boolean validate(){
	return TransSubmit.validate(this,this.getClass());
}
public String getCmdId() {
	return CmdId;
}
public void setCmdId(String cmdId) {
	CmdId = cmdId;
}
public String getRespCode() {
	return RespCode;
}
public void setRespCode(String respCode) {
	RespCode = respCode;
}
public String getRespDesc() {
	return RespDesc;
}
public void setRespDesc(String respDesc) {
	RespDesc = respDesc;
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
public String getCheckCode() {
	return checkCode;
}
public void setCheckCode(String checkCode) {
	this.checkCode = checkCode;
}
}
