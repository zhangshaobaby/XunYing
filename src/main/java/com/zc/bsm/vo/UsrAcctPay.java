package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class UsrAcctPay {
	private String Version = "10";
	private String CmdId = "UsrAcctPay";
	private String OrdId;
	private String UsrCustId;
	private String MerCustId= SignUtils.MER_CUST_ID;
	private String TransAmt;
	private String InAcctId;
	private String InAcctType;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;


	private String checkCode = "Version +CmdId + OrdId + UsrCustId + MerCustId + TransAmt+ InAcctId +InAcctType + RetUrl + BgRetUrl+ MerPriv+";
	                                                                                  
	public String getParam(){
		return TransSubmit.getHref(this,this.getClass());
	}


	public String getVersion() {
		return Version;
	}


	public void setVersion(String version) {
		Version = version;
	}


	public String getCmdId() {
		return CmdId;
	}


	public void setCmdId(String cmdId) {
		CmdId = cmdId;
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


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
