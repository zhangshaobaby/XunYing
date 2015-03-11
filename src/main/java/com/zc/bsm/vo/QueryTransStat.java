package com.zc.bsm.vo;

import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

/**
 * 交易状态查询
 * @author Administrator
 *
 */
public class QueryTransStat {private String Version = "10";
private String CmdId = "QueryTransStat";
private String MerCustId = SignUtils.MER_CUST_ID;
private String OrdId  ;
private String OrdDate  ;
private String QueryTransType ;
private String checkCode ="Version +CmdId + MerCustId+ OrdId+ OrdDate+ QueryTransType+";


public QueryTransStat() {
	super();
	// TODO Auto-generated constructor stub
}
public QueryTransStat(String ordId, String ordDate, String queryTransType) {
	super();
	OrdId = ordId;
	OrdDate = ordDate;
	QueryTransType = queryTransType;
}
public Map<String,String> getParam() {
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
public String getQueryTransType() {
	return QueryTransType;
}
public void setQueryTransType(String queryTransType) {
	QueryTransType = queryTransType;
}
public String getCheckCode() {
	return checkCode;
}
public void setCheckCode(String checkCode) {
	this.checkCode = checkCode;
}
}
