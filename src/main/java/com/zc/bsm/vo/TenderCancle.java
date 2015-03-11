package com.zc.bsm.vo;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class TenderCancle {
	private String Version = "20";
	private String CmdId = "TenderCancle";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String IsUnFreeze;
	private String UnFreezeOrdId;
	private String FreezeTrxId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ReqExt;

	private String checkCode = "Version + CmdId + MerCustId + OrdId + OrdDate + TransAmt + UsrCustId + IsUnFreeze + UnFreezeOrdId + FreezeTrxId + RetUrl + BgRetUrl + MerPriv + ReqExt + ";

	
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


	public String getIsUnFreeze() {
		return IsUnFreeze;
	}


	public void setIsUnFreeze(String isUnFreeze) {
		IsUnFreeze = isUnFreeze;
	}


	public String getUnFreezeOrdId() {
		return UnFreezeOrdId;
	}


	public void setUnFreezeOrdId(String unFreezeOrdId) {
		UnFreezeOrdId = unFreezeOrdId;
	}


	public String getFreezeTrxId() {
		return FreezeTrxId;
	}


	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
	}


	public String getReqExt() {
		return ReqExt;
	}


	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}
	
}
