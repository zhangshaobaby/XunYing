package com.zc.bsm.vo;

import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class FssRedeemReconciliation {

	private String Version = "10";
	private String CmdId = "FssRedeemReconciliation";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String BeginDate;
	private String EndDate;
	private String PageNum;
	private String PageSize;
	private String ReqExt;
	private String checkCode = "Version + CmdId + MerCustId + BeginDate + EndDate + PageNum + PageSize + ReqExt + ";


	public Map getParam() {
		return TransSubmit.getMap(this, this.getClass());
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


	public String getPageNum() {
		return PageNum;
	}


	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}


	public String getPageSize() {
		return PageSize;
	}


	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}


	public String getReqExt() {
		return ReqExt;
	}


	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


}
