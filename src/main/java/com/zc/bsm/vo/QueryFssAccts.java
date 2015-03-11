package com.zc.bsm.vo;

import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class QueryFssAccts {

	private String Version = "10";
	private String CmdId = "QueryFssAccts";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId;
	private String ReqExt;
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + ReqExt + ";


	public Map getParam() {
		return TransSubmit.getMap(this, this.getClass());
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


	public String getUsrCustId() {
		return UsrCustId;
	}


	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}


	public String getReqExt() {
		return ReqExt;
	}


	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
