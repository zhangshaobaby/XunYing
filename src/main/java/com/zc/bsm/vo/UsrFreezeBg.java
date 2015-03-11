package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

/**
 * 冻结
 * @author Administrator
 *
 */
public class UsrFreezeBg {
	private String Version = "10";
	private String CmdId = "UsrFreezeBg";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;


	private String UsrCustId;
	private String SubAcctType;
	private String SubAcctId;
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + SubAcctType + SubAcctId + OrdId + OrdDate + TransAmt + RetUrl + BgRetUrl + MerPriv + ";

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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getSubAcctType() {
		return SubAcctType;
	}

	public void setSubAcctType(String subAcctType) {
		SubAcctType = subAcctType;
	}

	public String getSubAcctId() {
		return SubAcctId;
	}

	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
	}

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getOrdDate() {
		return OrdDate;
	}

	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
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

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	
}
