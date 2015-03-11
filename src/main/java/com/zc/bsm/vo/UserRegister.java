package com.zc.bsm.vo;



import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class UserRegister {
	private String Version = "10";
	private String CmdId = "UserRegister";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	//后台应答地址 商户网站都应在答接收页面输出 RECV_ORD_ID 字样字符串  表明已收到交易结果
	private String BgRetUrl = "";
	//页面返回 url
	private String RetUrl = "";
	//商户下的平台用户号，在每个商户下唯一（必须是6-25)
	private String UsrId;
	//真实姓名
	private String UsrName;
	//'00' – 身份证
	private String IdType = "00";
	//证件号
	private String IdNo;
	//手机号
	private String UsrMp ;
	//email
	private String UsrEmail;
	//私有域  中文base64加密
	private String CharSet = "UTF-8";
	private String MerPriv;
	
	private String checkCode = "Version + CmdId + MerCustId + BgRetUrl + RetUrl + UsrId + UsrName + IdType + IdNo + UsrMp + UsrEmail + MerPriv + ";
	
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

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
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
	public String getUsrId() {
		return UsrId;
	}
	public void setUsrId(String usrId) {
		UsrId = usrId;
	}
	public String getUsrName() {
		return UsrName;
	}
	public void setUsrName(String usrName) {
		UsrName = usrName;
	}
	public String getIdType() {
		return IdType;
	}
	public void setIdType(String idType) {
		IdType = idType;
	}
	public String getIdNo() {
		return IdNo;
	}
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	public String getUsrMp() {
		return UsrMp;
	}
	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}
	public String getUsrEmail() {
		return UsrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		UsrEmail = usrEmail;
	}
	public String getCharSet() {
		return CharSet;
	}
	public void setCharSet(String charSet) {
		CharSet = charSet;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
