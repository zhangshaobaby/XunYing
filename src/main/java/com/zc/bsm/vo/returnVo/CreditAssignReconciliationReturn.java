package com.zc.bsm.vo.returnVo;

import java.util.List;

import com.zc.base.util.TransSubmit;

public class CreditAssignReconciliationReturn {

	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId;
	private String OrdId;
	private String BeginDate;
	private String EndDate;
	private String SellCustId;
	private String BuyCustId;
	private String PageNum;
	private String PageSize;
	private String TotalItems;
	private List<CreditAssignReconciliationResult> BidCaReconciliationList;
	private String RespExt;
	private String ChkValue;

	private String checkCode = " CmdId + RespCode + MerCustId + OrdId + BeginDate + EndDate + SellCustId + BuyCustId + PageNum + PageSize + TotalItems + RespExt + ";

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

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
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

	public String getSellCustId() {
		return SellCustId;
	}

	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
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

	public List<CreditAssignReconciliationResult> getBidCaReconciliationList() {
		return BidCaReconciliationList;
	}

	public void setBidCaReconciliationList(
			List<CreditAssignReconciliationResult> bidCaReconciliationList) {
		BidCaReconciliationList = bidCaReconciliationList;
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
