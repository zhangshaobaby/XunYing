package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class NetSaveReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String UsrCustId;
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String TrxId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String GateBusiId;
	private String GateBankId;
	private String ChkValue;

	private String FeeAmt;
	private String FeeCustId;
	private String FeeAcctId;
	
	private String checkCode = "CmdId + RespCode + MerCustId + UsrCustId + OrdId + OrdDate + TransAmt + TrxId + RetUrl + BgRetUrl + MerPriv + ";
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
	public String getTrxId() {
		return TrxId;
	}
	public void setTrxId(String trxId) {
		TrxId = trxId;
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
	public String getMerPriv() {
		return MerPriv;
	}
	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	public String getGateBusiId() {
		return GateBusiId;
	}
	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}
	public String getGateBankId() {
		return GateBankId;
	}
	public void setGateBankId(String gateBankId) {
		GateBankId = gateBankId;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
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
}
