package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class UserBindCardReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	
	private String OpenAcctId;
	private String OpenBankId;
	
	private String UsrCustId;
	private String TrxId;
	private String BgRetUrl;
	private String MerPriv;
	private String ChkValue;

	public boolean validate(){
		return TransSubmit.validate(this,this.getClass());
	}	
	private String checkCode = "CmdId + RespCode + MerCustId + OpenAcctId + OpenBankId + UsrCustId + TrxId + BgRetUrl + MerPriv + ";
	

	
	/****/
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
