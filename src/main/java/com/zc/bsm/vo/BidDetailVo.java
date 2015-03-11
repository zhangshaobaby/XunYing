package com.zc.bsm.vo;

import java.util.List;

public class BidDetailVo {
	 private String   BidOrdId;
	    private String BidOrdDate;
	    private String BidCreditAmt;
	    private List<BorrowerDetailCreditAssignVo> BorrowerDetails;
		public String getBidOrdId() {
			return BidOrdId;
		}
		public void setBidOrdId(String bidOrdId) {
			BidOrdId = bidOrdId;
		}
		public String getBidOrdDate() {
			return BidOrdDate;
		}
		public void setBidOrdDate(String bidOrdDate) {
			BidOrdDate = bidOrdDate;
		}
		public List<BorrowerDetailCreditAssignVo> getBorrowerDetails() {
			return BorrowerDetails;
		}
		public void setBorrowerDetails(
				List<BorrowerDetailCreditAssignVo> borrowerDetails) {
			BorrowerDetails = borrowerDetails;
		}
		public String getBidCreditAmt() {
			return BidCreditAmt;
		}
		public void setBidCreditAmt(String bidCreditAmt) {
			BidCreditAmt = bidCreditAmt;
		}
}
