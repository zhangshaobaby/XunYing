package com.zc.bsm.vo.returnVo;

import java.util.List;

import com.zc.base.util.TransSubmit;

public class QueryReturnDzFeeReturn {
	
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String BeginDate;
	private String EndDate;
	private List<QueryReturnDzFeeResult> 	DatedBatchList;
	private String ChkValue;
	
	private String checkCode = "CmdId + RespCode + MerCustId + BeginDate + EndDate + ";
	
	
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


	public String getBeginDate() {
		return BeginDate;
	}


	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}


	public String getEndDate() {
		return EndDate;
	}


	public void setEndDate(String endDate) {
		EndDate = endDate;
	}


	public List<QueryReturnDzFeeResult> getDatedBatchList() {
		return DatedBatchList;
	}


	public void setDatedBatchList(List<QueryReturnDzFeeResult> datedBatchList) {
		DatedBatchList = datedBatchList;
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
