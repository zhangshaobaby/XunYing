package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;


public class TransferReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String OrdId;
	private String OutCustId;
	private String OutAcctId;
	private String TransAmt;
	private String InCustId;
	private String InAcctId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ChkValue;

public boolean validate(){
return TransSubmit.validate(this,this.getClass());
}	
	private String checkCode = "CmdId + RespCode + OrdId + OutCustId + OutAcctId + TransAmt + InCustId + InAcctId + RetUrl + BgRetUrl + MerPriv + ";

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

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
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

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
}
