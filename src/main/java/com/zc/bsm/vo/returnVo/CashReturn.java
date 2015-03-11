package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class CashReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String OrdId;
	private String UsrCustId;
	private String TransAmt;
	private String OpenAcctId;
	private String OpenBankId;
	private String FeeAmt;
	private String FeeCustId;
	private String FeeAcctId;
	private String ServFee;
	private String ServFeeAcctId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String RespExt;
	private String ChkValue;

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}

	private String checkCode = "CmdId + RespCode + MerCustId + OrdId + UsrCustId + TransAmt + OpenAcctId + OpenBankId + FeeAmt + FeeCustId + FeeAcctId + ServFee + ServFeeAcctId + RetUrl + BgRetUrl + MerPriv + RespExt + ";

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

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
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

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
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

	public String getServFee() {
		return ServFee;
	}

	public void setServFee(String servFee) {
		ServFee = servFee;
	}

	public String getServFeeAcctId() {
		return ServFeeAcctId;
	}

	public void setServFeeAcctId(String servFeeAcctId) {
		ServFeeAcctId = servFeeAcctId;
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

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
}
