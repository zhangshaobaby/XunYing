package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class LoansReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String OrdId;
	private String OrdDate;
	private String OutCustId;
	private String OutAcctId;
	private String TransAmt;
	private String Fee;
	private String InCustId;
	private String InAcctId;
	private String SubOrdId;
	private String SubOrdDate;
	private String FeeObjFlag;
	private String IsDefault;
	private String IsUnFreeze;
	private String UnFreezeOrdId;
	private String FreezeTrxId;
	private String BgRetUrl;
	private String MerPriv;
	private String RespExt;
	private String ChkValue;

public boolean validate(){
return TransSubmit.validate(this,this.getClass());
}
	
	private String checkCode = "CmdId + RespCode + MerCustId + OrdId + OrdDate + OutCustId + OutAcctId + TransAmt + Fee + InCustId + InAcctId + SubOrdId + SubOrdDate + FeeObjFlag + IsDefault + IsUnFreeze + UnFreezeOrdId + FreezeTrxId + BgRetUrl + MerPriv + RespExt + ";

	/*************************/
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


	public String getOutCustId() {
		return OutCustId;
	}


	public void setOutCustId(String outCustId) {
		OutCustId = outCustId;
	}


	public String getOutAcctId() {
		return OutAcctId;
	}


	public void setOutAcctId(String outAcctId) {
		OutAcctId = outAcctId;
	}


	public String getTransAmt() {
		return TransAmt;
	}


	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}


	public String getFee() {
		return Fee;
	}


	public void setFee(String fee) {
		Fee = fee;
	}


	public String getInCustId() {
		return InCustId;
	}


	public void setInCustId(String inCustId) {
		InCustId = inCustId;
	}


	public String getInAcctId() {
		return InAcctId;
	}


	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}


	public String getSubOrdId() {
		return SubOrdId;
	}


	public void setSubOrdId(String subOrdId) {
		SubOrdId = subOrdId;
	}


	public String getSubOrdDate() {
		return SubOrdDate;
	}


	public void setSubOrdDate(String subOrdDate) {
		SubOrdDate = subOrdDate;
	}


	public String getFeeObjFlag() {
		return FeeObjFlag;
	}


	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
	}


	public String getIsDefault() {
		return IsDefault;
	}


	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}


	public String getIsUnFreeze() {
		return IsUnFreeze;
	}


	public void setIsUnFreeze(String isUnFreeze) {
		IsUnFreeze = isUnFreeze;
	}


	public String getUnFreezeOrdId() {
		return UnFreezeOrdId;
	}


	public void setUnFreezeOrdId(String unFreezeOrdId) {
		UnFreezeOrdId = unFreezeOrdId;
	}


	public String getFreezeTrxId() {
		return FreezeTrxId;
	}


	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
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


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	
	
}
