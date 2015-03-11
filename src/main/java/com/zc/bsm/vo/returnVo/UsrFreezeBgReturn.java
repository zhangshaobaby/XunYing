package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;





public class UsrFreezeBgReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String UsrCustId;
	private String SubAcctType;
	private String SubAcctId;
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String TrxId;
	private String MerPriv;
	private String ChkValue;

public boolean validate(){
return TransSubmit.validate(this,this.getClass());
}	
	private String checkCode = "CmdId + RespCode + MerCustId + UsrCustId + SubAcctType + SubAcctId + OrdId + OrdDate + TransAmt + RetUrl + BgRetUrl + TrxId + MerPriv + ";

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
	public String getSubAcctType() {
		return SubAcctType;
	}
	public void setSubAcctType(String subAcctType) {
		SubAcctType = subAcctType;
	}
	public String getSubAcctId() {
		return SubAcctId;
	}
	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
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
	public String getRetUrl() {
		return RetUrl;
	}
	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}
	public String getBgRetUrl() {
		return BgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}
	public String getTrxId() {
		return TrxId;
	}
	public void setTrxId(String trxId) {
		TrxId = trxId;
	}
	public String getMerPriv() {
		return MerPriv;
	}
	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
	
	
	
}
