package com.zc.bsm.vo.returnVo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class UsrAcctPayReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String OrdId;
	private String UsrCustId;
	private String MerCustId;
	private String TransAmt;
	private String InAcctId;
	private String InAcctType;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ChkValue;

public boolean validate(){
return TransSubmit.validate(this,this.getClass());
}


	private String checkCode = "CmdId + RespCode + OrdId + UsrCustId + MerCustId + TransAmt + InAcctId + InAcctType + RetUrl + BgRetUrl + MerPriv + ";



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



	public String getUsrCustId() {
		return UsrCustId;
	}



	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}



	public String getMerCustId() {
		return MerCustId;
	}



	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}



	public String getTransAmt() {
		return TransAmt;
	}



	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}



	public String getInAcctId() {
		return InAcctId;
	}



	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}



	public String getInAcctType() {
		return InAcctType;
	}



	public void setInAcctType(String inAcctType) {
		InAcctType = inAcctType;
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
