package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class UsrTransfer {
	private String Version = "10";
	private String CmdId = "UsrTransfer";
	private String MerCustId;
	private String OrdId;
	private String RetUrl;
	private String BgRetUrl;
	private String UsrCustId;
	private String InUsrCustId;
	private String TransAmt;
	private String MerPriv;
	private String ReqExt;



	private String checkCode = "Version + CmdId + OrdId + UsrCustId + MerCustId + TransAmt + InUsrCustId + RetUrl + BgRetUrl + MerPriv + ReqExt + ";

	
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


	public String getUsrCustId() {
		return UsrCustId;
	}


	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}


	public String getInUsrCustId() {
		return InUsrCustId;
	}


	public void setInUsrCustId(String inUsrCustId) {
		InUsrCustId = inUsrCustId;
	}


	public String getTransAmt() {
		return TransAmt;
	}


	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}


	public String getMerPriv() {
		return MerPriv;
	}


	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}


	public String getReqExt() {
		return ReqExt;
	}


	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}
	
}
