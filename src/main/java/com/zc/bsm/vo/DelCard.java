package com.zc.bsm.vo;



import java.util.Map;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;


public class DelCard {
	private String Version = "10";
	private String CmdId = "DelCard";
	//商户客户号，由汇付生成，商户的唯一性标识
	private String MerCustId = SignUtils.MER_CUST_ID;
	//商户下的平台用户号，在每个商户下唯一（必须是6-25)
	private String UsrCustId;
	//真实姓名
	private String CardId;
	
	private String checkCode = "Version + CmdId + MerCustId + UsrCustId + CardId  + ";

	public Map getParam(){
		return TransSubmit.getMap(this,this.getClass());
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


	public String getUsrCustId() {
		return UsrCustId;
	}


	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}


	public String getCardId() {
		return CardId;
	}


	public void setCardId(String cardId) {
		CardId = cardId;
	}
	
}
