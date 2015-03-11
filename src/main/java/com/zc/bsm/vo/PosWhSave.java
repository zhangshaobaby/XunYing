package com.zc.bsm.vo;



import java.util.Map;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class PosWhSave {
	private String Version = "10";
	private String CmdId = "PosWhSave";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;


	private String UsrCustId;
	private String OpenAcctId;
	private String TransAmt;
	private String OrdId;
	private String OrdDate;
	private String CheckDate;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + OpenAcctId + TransAmt + OrdId + OrdDate + CheckDate + RetUrl + BgRetUrl + MerPriv + ";

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

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
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

	public String getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
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
