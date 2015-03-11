package com.zc.bsm.vo;

import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class BgBindCard {
	private String Version = "10";
	private String CmdId = "BgBindCard";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	
	private String UsrCustId;
	private String OpenAcctId;
	private String OpenBankId;
	private String OpenprovId;
	private String OpenAreaId;
	private String OpenBranchName;
	private String IsDefault;
	private String MerPriv;
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + OpenAcctId + OpenBankId + OpenProvId + OpenAreaId + OpenBranchName + IsDefault + MerPriv + ";

	
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


	public String getOpenAcctId() {
		return OpenAcctId;
	}


	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}


	public String getOpenBankId() {
		return OpenBankId;
	}


	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}


	public String getOpenprovId() {
		return OpenprovId;
	}


	public void setOpenprovId(String openprovId) {
		OpenprovId = openprovId;
	}


	public String getOpenAreaId() {
		return OpenAreaId;
	}


	public void setOpenAreaId(String openAreaId) {
		OpenAreaId = openAreaId;
	}


	public String getOpenBranchName() {
		return OpenBranchName;
	}


	public void setOpenBranchName(String openBranchName) {
		OpenBranchName = openBranchName;
	}


	public String getIsDefault() {
		return IsDefault;
	}


	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}


	public String getMerPriv() {
		return MerPriv;
	}


	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	
}
