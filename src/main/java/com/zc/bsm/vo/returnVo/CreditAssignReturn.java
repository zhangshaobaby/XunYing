package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class CreditAssignReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String SellCustId;
	private String CreditAmt;
	private String CreditDealAmt;
	private String Fee;
	private String BuyCustId;
	private String OrdId;
	private String OrdDate;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String RespExt;
	private String ChkValue;

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}

	private String checkCode = "CmdId + RespCode + MerCustId + SellCustId + CreditAmt + CreditDealAmt + Fee + BuyCustId + OrdId + OrdDate + RetUrl + BgRetUrl + MerPriv + RespExt + ";

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

	public String getSellCustId() {
		return SellCustId;
	}

	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}

	public String getCreditAmt() {
		return CreditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		CreditAmt = creditAmt;
	}

	public String getCreditDealAmt() {
		return CreditDealAmt;
	}

	public void setCreditDealAmt(String creditDealAmt) {
		CreditDealAmt = creditDealAmt;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
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
