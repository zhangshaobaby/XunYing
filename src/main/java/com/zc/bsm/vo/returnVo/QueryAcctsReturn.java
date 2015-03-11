package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

/**
 * 商户子账户信息查询
 * @author Administrator
 *
 */
public class QueryAcctsReturn {private String CmdId;private String RespCode ;private String RespDesc ;private String MerCustId ;private String AcctDetails  ;private String checkCode="CmdId + RespCode+ MerCustId";
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
public String getAcctDetails() {
	return AcctDetails;
}
public void setAcctDetails(String acctDetails) {
	AcctDetails = acctDetails;
}
public String getCheckCode() {
	return checkCode;
}
public void setCheckCode(String checkCode) {
	this.checkCode = checkCode;
}
}
