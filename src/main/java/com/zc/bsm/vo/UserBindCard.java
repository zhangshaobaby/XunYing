package com.zc.bsm.vo;



import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class UserBindCard {
	private String Version = "10";
	private String CmdId = "UserBindCard";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId;
	//后台应答地址 商户网站都应在答接收页面输出 RECV_ORD_ID 字样字符串  表明已收到交易结果
	private String BgRetUrl;
	private String MerPriv;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + BgRetUrl + MerPriv + ";
	
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
