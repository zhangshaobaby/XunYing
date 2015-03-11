package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class UserRegisterReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String UsrId;
	private String UsrCustId;
	private String BgRetUrl;
	private String TrxId;
	private String RetUrl;
	private String MerPriv;
	private String IdType;
	private String IdNo;
	private String UsrMp;
	private String UsrEmail;
	private String UsrName;
	private String ChkValue;

public boolean validate(){
return TransSubmit.validate(this,this.getClass());
}	
	private String checkCode = "CmdId + RespCode + MerCustId + UsrId + UsrCustId + BgRetUrl + TrxId + RetUrl + MerPriv + ";
	
	
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
	public String getUsrId() {
		return UsrId;
	}
	public void setUsrId(String usrId) {
		UsrId = usrId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
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
	public String getIdType() {
		return IdType;
	}
	public void setIdType(String idType) {
		IdType = idType;
	}
	public String getIdNo() {
		return IdNo;
	}
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	public String getUsrMp() {
		return UsrMp;
	}
	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}
	public String getUsrEmail() {
		return UsrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		UsrEmail = usrEmail;
	}
	public String getUsrName() {
		return UsrName;
	}
	public void setUsrName(String usrName) {
		UsrName = usrName;
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
