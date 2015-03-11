package com.zc.bsm.vo.returnVo;

import java.util.List;

import com.zc.base.util.TransSubmit;

public class FssPurchaseReconciliationReturn {

	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String BeginDate;
	private String EndDate;
	private String PageNum;
	private String PageSize;
	private String TotalItems;
	private List<FssPurchaseReconciliationResult> FssReconciliationList;
	private String RespExt;
	private String ChkValue;
	private String checkCode = "CmdId + RespCode + MerCustId + BeginDate + EndDate + PageNum + PageSize + TotalItems + ";

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}

	/** * */

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

	public String getTotalItems() {
		return TotalItems;
	}

	public void setTotalItems(String totalItems) {
		TotalItems = totalItems;
	}

	public List<FssPurchaseReconciliationResult> getFssReconciliationList() {
		return FssReconciliationList;
	}

	public void setFssReconciliationList(
			List<FssPurchaseReconciliationResult> fssReconciliationList) {
		FssReconciliationList = fssReconciliationList;
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
