package com.zc.bsm.vo;



import org.apache.commons.lang3.StringUtils;

import com.zc.base.util.HttpClientHandler;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

/**
 *  自动投标计划
 * @author Administrator
 *
 */
public class AutoTenderPlan {
	private String Version = "10";
	private String CmdId = "AutoTenderPlan";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	
	private String UsrCustId;
	private String TenderPlanType;
	private String TransAmt;
	private String RetUrl;
	private String MerPriv;

	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + TenderPlanType + TransAmt + RetUrl + MerPriv + ";

	
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
	public String getMerPriv() {
		return MerPriv;
	}
	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	public String getTenderPlanType() {
		return TenderPlanType;
	}
	public void setTenderPlanType(String tenderPlanType) {
		TenderPlanType = tenderPlanType;
	}
}
