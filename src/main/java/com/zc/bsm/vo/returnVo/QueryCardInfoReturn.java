package com.zc.bsm.vo.returnVo;

import java.util.List;

import com.zc.base.util.TransSubmit;

public class QueryCardInfoReturn {
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;	
	private String UsrCustId;
	private String CardId;

	private List<QueryCardInfoResult> 	UsrCardInfolist;
	private String ChkValue;
	
	private String checkCode = " CmdId + RespCode + MerCustId + UsrCustId + CardId + ";
	
	
	public boolean validate(){
		return TransSubmit.validate(this,this.getClass());
	}
	/****/


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


	public String getUsrCustId() {
		return UsrCustId;
	}


	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}


	public String getCardId() {
		return CardId;
	}


	public void setCardId(String cardId) {
		CardId = cardId;
	}


	public List<QueryCardInfoResult> getUsrCardInfolist() {
		return UsrCardInfolist;
	}


	public void setUsrCardInfolist(List<QueryCardInfoResult> usrCardInfolist) {
		UsrCardInfolist = usrCardInfolist;
	}


	public String getChkValue() {
		return ChkValue;
	}


	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}




}
