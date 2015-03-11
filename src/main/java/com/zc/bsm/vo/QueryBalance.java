package com.zc.bsm.vo;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

/**
 * 查询余额
 * 
 * @author Administrator
 * 
 */
public class QueryBalance {
	private String Version = "10";
	private String CmdId = "QueryBalance";
	private String MerCustId = SignUtils.MER_CUST_ID;
	private String UsrCustId;
	private String checkCode = "Version +CmdId + MerCustId + UsrCustId";
	public String getParam() {
		return TransSubmit.getHref(this, this.getClass());
	}
}
