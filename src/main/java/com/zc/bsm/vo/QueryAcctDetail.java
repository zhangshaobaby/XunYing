package com.zc.bsm.vo;



import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class QueryAcctDetail {
	private String Version = "10";
	private String CmdId = "QueryAcctDetail";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId= "";	
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + ";
	
	public String getParam(){
		return TransSubmit.getHref(this,this.getClass());
	}
/**getter & setter**/

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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
