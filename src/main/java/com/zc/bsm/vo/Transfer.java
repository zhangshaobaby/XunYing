package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class Transfer {
	private String Version = "10";
	private String CmdId = "Transfer";
	
	private String OrdId;
	private String OutCustId;
	private String OutAcctId;
	private String TransAmt;
	private String InCustId;
	private String InAcctId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	
	
	
	private String checkCode = "Version + CmdId + OrdId + OutCustId + OutAcctId + TransAmt + InCustId + InAcctId + RetUrl + BgRetUrl + MerPriv + ";

	public Map getParam(){
		return TransSubmit.getMap(this,this.getClass());
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
}
