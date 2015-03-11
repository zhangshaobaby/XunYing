package com.zc.bsm.vo;



import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class BgRegister {
	private String Version = "10";
	private String CmdId = "BgRegister";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	//商户下的平台用户号，在每个商户下唯一（必须是6-25)
	private String UsrId;
	//真实姓名
	private String UsrName;
	private String LoginPwd;
	private String TransPwd;
	//'00' – 身份证
	private String IdType = "00";
	//证件号
	private String IdNo;
	//手机号
	private String UsrMp ;
	//email
	private String UsrEmail;
	//私有域  中文base64加密
	private String MerPriv;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrId + UsrName + LoginPwd + TransPwd + IdType + IdNo + UsrMp + UsrEmail + MerPriv + ";
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
	public String getLoginPwd() {
		return LoginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		LoginPwd = loginPwd;
	}
	public String getTransPwd() {
		return TransPwd;
	}
	public void setTransPwd(String transPwd) {
		TransPwd = transPwd;
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
