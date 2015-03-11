package com.zc.bsm.vo.returnVo;

import java.util.List;

import com.zc.base.util.TransSubmit;

/**
 *取现对账
 * @author Administrator
 *
 */
public class CashReconciliationDtoReturn {
	private String CmdId ;
	private String RespCode  ;
	private String RespDesc  ;
	private String MerCustId  ;
	private String BeginDate   ;
	private String EndDate   ;
	private String PageNum   ;
	private String PageSize    ;
	private String TotalItems   ;
	private String FeeObj;
	List<CashReconciliatioResult> CashReconciliationDtoList;
	private String checkCode = "CmdId + RespCode+ MerCustId+  BeginDate+ EndDate+ PageNum+ PageSize+TotalItems+ FeeObj";
	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}
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
	public String getFeeObj() {
		return FeeObj;
	}
	public void setFeeObj(String feeObj) {
		FeeObj = feeObj;
	}
	public List<CashReconciliatioResult> getCashReconciliationDtoList() {
		return CashReconciliationDtoList;
	}
	public void setCashReconciliationDtoList(
			List<CashReconciliatioResult> cashReconciliationDtoList) {
		CashReconciliationDtoList = cashReconciliationDtoList;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
