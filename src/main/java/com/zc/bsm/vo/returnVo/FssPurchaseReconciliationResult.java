package com.zc.bsm.vo.returnVo;


public class FssPurchaseReconciliationResult {

	private String MerCustId;		
	private String UsrCustId;	
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String TransStat;
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
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getOrdDate() {
		return OrdDate;
	}
	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}
	public String getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	public String getTransStat() {
		return TransStat;
	}
	public void setTransStat(String transStat) {
		TransStat = transStat;
	}
	
}
