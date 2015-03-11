package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class CorpRegisterReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String UsrId;
	private String UsrName;
	private String UsrCustId;
	private String AuditStat;
	private String AuditDesc;
	private String MerPriv;
	private String TrxId;
	private String OpenBankId;
	private String CardId;
	private String BgRetUrl;
	private String RespExt;
	private String ChkValue;

	public boolean validate() {
		return TransSubmit.validate(this,this.getClass());
	}

	private String checkCode = " CmdId + RespCode +MerCustId+ UsrId +UsrName+ UsrCustId + AuditStat + TrxId + OpenBankId + CardId+ BgRetUrl+ RespExt+ ";

	public String getCmdId() {
		return CmdId;
	}

	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}

	public String getRespCode() {
		return RespCode;
	}

	public void setRespCode(String respCode) {
		RespCode = respCode;
	}

	public String getRespDesc() {
		return RespDesc;
	}

	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getAuditStat() {
		return AuditStat;
	}

	public void setAuditStat(String auditStat) {
		AuditStat = auditStat;
	}

	public String getAuditDesc() {
		return AuditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		AuditDesc = auditDesc;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

}
