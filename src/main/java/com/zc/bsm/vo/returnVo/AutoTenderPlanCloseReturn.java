package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class AutoTenderPlanCloseReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String UsrCustId;
	private String RetUrl;
	private String MerPriv;
	private String ChkValue;

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}

	private String checkCode = "CmdId + RespCode + MerCustId + UsrCustId + RetUrl + MerPriv + ";

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

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
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
