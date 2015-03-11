package com.zc.bsm.vo;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class AcctModify {
	private String Version = "10";
	private String CmdId = "AcctModify";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId;
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + ";

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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
	public static void main(String[]args){
		int i=1000;
		int j = 33;
		int c = 1000/33;
		System.out.println(c);
		
	}
}
