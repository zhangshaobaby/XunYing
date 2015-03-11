package com.zc.bsm.vo;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class QueryTenderPlan {
	private String Version = "10";
	private String CmdId = "QueryTenderPlan";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId   ;
	private String checkCode ="Version +CmdId + MerCustId+ UsrCustId";
	public String getParam() {
		return TransSubmit.getHref(this, this.getClass());
	}	
}
