package com.zc.bsm.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

public class BorrowerDetailCreditAssignVo {
	 private String BorrowerCustId;
	    private String BorrowerCreditAmt;
	    private String PrinAmt;
		public String getBorrowerCustId() {
			return BorrowerCustId;
		}
		public void setBorrowerCustId(String borrowerCustId) {
			BorrowerCustId = borrowerCustId;
		}
		public String getBorrowerCreditAmt() {
			return BorrowerCreditAmt;
		}
		public void setBorrowerCreditAmt(String borrowerCreditAmt) {
			BorrowerCreditAmt = borrowerCreditAmt;
		}
		public String getPrinAmt() {
			return PrinAmt;
		}
		public void setPrinAmt(String prinAmt) {
			PrinAmt = prinAmt;
		}
}
