package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class MerCash {
	private String Version = "10";
	private String CmdId = "MerCash";
	private String MerCustId;
	private String OrdId;
	private String UsrCustId;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String Remark;
	private String CharSet;
	private String MerPriv;

	private String checkCode = "Version  +CmdId  +  MerCustId+  OrdId  +  UsrCustId  +  TransAmt  +  RetUrl  +BgRetUrl+ Remark+ MerPriv+";

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


	public String getRemark() {
		return Remark;
	}


	public void setRemark(String remark) {
		Remark = remark;
	}


	public String getCharSet() {
		return CharSet;
	}


	public void setCharSet(String charSet) {
		CharSet = charSet;
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
