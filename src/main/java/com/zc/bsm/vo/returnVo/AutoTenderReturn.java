package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class AutoTenderReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String TrxId;
	private String IsFreeze;
	private String FreezeOrdId;
	private String FreezeTrxId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String RespExt;
	private String ChkValue;

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}

	private String checkCode = "CmdId + RespCode + MerCustId + OrdId + OrdDate + TransAmt + UsrCustId + TrxId + IsFreeze + FreezeOrdId + FreezeTrxId + RetUrl + BgRetUrl + MerPriv + RespExt + ";

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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return FreezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		FreezeOrdId = freezeOrdId;
	}

	public String getFreezeTrxId() {
		return FreezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
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
