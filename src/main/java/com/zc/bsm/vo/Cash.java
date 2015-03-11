package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class Cash {
	private String Version = "20";
	private String CmdId = "Cash";
	private String MerCustId=SignUtils.MER_CUST_ID;
	private String OrdId;
	private String UsrCustId;
	private String TransAmt;
	private String ServFee;
	private String ServFeeAcctId;
	private String OpenAcctId;
	private String RetUrl;
	private String BgRetUrl;
	private String Remark;
	private String CharSet;
	private String MerPriv;
	private String ReqExt;

	private String checkCode = "Version + CmdId + MerCustId + OrdId + UsrCustId + TransAmt + ServFee + ServFeeAcctId + OpenAcctId + RetUrl + BgRetUrl + Remark + MerPriv + ReqExt + ";

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

	public String getServFee() {
		return ServFee;
	}

	public void setServFee(String servFee) {
		ServFee = servFee;
	}

	public String getServFeeAcctId() {
		return ServFeeAcctId;
	}

	public void setServFeeAcctId(String servFeeAcctId) {
		ServFeeAcctId = servFeeAcctId;
	}

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
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

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}
}
