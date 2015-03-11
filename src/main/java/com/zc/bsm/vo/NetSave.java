package com.zc.bsm.vo;



import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class NetSave {
	private String Version = "10";
	private String CmdId = "NetSave";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	
	private String UsrCustId;
	private String OrdId;
	private String OrdDate;
	private String GateBusiId;
	private String OpenBankId;
	private String DcFlag;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	
	private String checkCode = "Version +CmdId + MerCustId + UsrCustId + OrdId+ OrdDate + GateBusiId+OpenBankId+ DcFlag +TransAmt+ RetUrl+ BgRetUrl+ MerPriv+ ";

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

	public String getGateBusiId() {
		return GateBusiId;
	}

	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getDcFlag() {
		return DcFlag;
	}

	public void setDcFlag(String dcFlag) {
		DcFlag = dcFlag;
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


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
