package com.zc.bsm.vo;

import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

/**
 * 商户子账户信息查询
 * 
 * @author Administrator
 * 
 */
public class QueryAccts {
	private String Version = "10";
	private String CmdId = "QueryAccts";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String checkCode ="Version +CmdId + MerCustId+";
	public Map<String,String> getParam() {
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
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
