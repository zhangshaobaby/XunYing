package com.zc.bsm.vo;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class AutoCreditAssign {
	private String Version = "10";
	private String CmdId = "AutoCreditAssign";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	private String SellCustId;
	private String CreditAmt;
	private String CreditDealAmt;
	private  String  BidDetails;
	private String Fee;
	private String DivDetails;
	private String BuyCustId;
	private String OrdId;
	private String OrdDate;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ReqExt;



	private String checkCode = "Version + CmdId + MerCustId + SellCustId + CreditAmt + CreditDealAmt + BidDetails + Fee + DivDetails + BuyCustId + OrdId + OrdDate + RetUrl + BgRetUrl + MerPriv + ReqExt + ";

	
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


	public String getSellCustId() {
		return SellCustId;
	}


	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}


	public String getCreditAmt() {
		return CreditAmt;
	}


	public void setCreditAmt(String creditAmt) {
		CreditAmt = creditAmt;
	}


	public String getCreditDealAmt() {
		return CreditDealAmt;
	}


	public void setCreditDealAmt(String creditDealAmt) {
		CreditDealAmt = creditDealAmt;
	}
	public String getFee() {
		return Fee;
	}


	public void setFee(String fee) {
		Fee = fee;
	}
	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String getBuyCustId() {
		return BuyCustId;
	}


	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
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


	public String getBidDetails() {
		return BidDetails;
	}


	public void setBidDetails(String bidDetails) {
		BidDetails = bidDetails;
	}


	public String getDivDetails() {
		return DivDetails;
	}


	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}
}
