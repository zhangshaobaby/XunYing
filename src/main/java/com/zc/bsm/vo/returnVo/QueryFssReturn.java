package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

public class QueryFssReturn {

	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;	
	private String AnnuRate;	
	private String PrdRate;
	private String RespExt;
	private String ChkValue;	
	private String checkCode = "CmdId + RespCode + MerCustId + AnnuRate + PrdRate + RespExt + ";

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

	public String getAnnuRate() {
		return AnnuRate;
	}

	public void setAnnuRate(String annuRate) {
		AnnuRate = annuRate;
	}

	public String getPrdRate() {
		return PrdRate;
	}

	public void setPrdRate(String prdRate) {
		PrdRate = prdRate;
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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	
}
