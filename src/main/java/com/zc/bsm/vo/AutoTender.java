package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class AutoTender {
	private String Version = "20";
	private String CmdId = "AutoTender";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String MaxTenderRate;
	private String BorrowerDetails;
	private String IsFreeze;
	private String FreezeOrdId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ReqExt;
	
	private String checkCode = "Version + CmdId + MerCustId + OrdId + OrdDate + TransAmt + UsrCustId + MaxTenderRate + BorrowerDetails + IsFreeze + FreezeOrdId + RetUrl + BgRetUrl + MerPriv + ReqExt + ";

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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getMaxTenderRate() {
		return MaxTenderRate;
	}

	public void setMaxTenderRate(String maxTenderRate) {
		MaxTenderRate = maxTenderRate;
	}

	public String getBorrowerDetails() {
		return BorrowerDetails;
	}

	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return FreezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		FreezeOrdId = freezeOrdId;
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
