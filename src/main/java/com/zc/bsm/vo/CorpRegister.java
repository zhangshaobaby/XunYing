package com.zc.bsm.vo;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class CorpRegister {
	private String Version = "10";
	private String CmdId = "CorpRegister";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrId;
	private String UsrName;
	//组织机构代码
	private String InstuCode;
	//营业执照编号
	private String BusiCode;
	//税务登记号
	private String TaxCode;
	private String MerPriv;
	private String Charset;
	//是否担保类型，Y：是(目前只支持担保类型)
	private String GuarType;
	private String BgRetUrl;
	//用于扩展请求参数
	private String ReqExt;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrId + UsrName + InstuCode + BusiCode + TaxCode + MerPriv + GuarType + BgRetUrl + ReqExt + ";

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

	public String getInstuCode() {
		return InstuCode;
	}

	public void setInstuCode(String instuCode) {
		InstuCode = instuCode;
	}

	public String getBusiCode() {
		return BusiCode;
	}

	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}

	public String getTaxCode() {
		return TaxCode;
	}

	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getGuarType() {
		return GuarType;
	}

	public void setGuarType(String guarType) {
		GuarType = guarType;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	public String getCharset() {
		return Charset;
	}

	public void setCharset(String charset) {
		Charset = charset;
	}
	
}
