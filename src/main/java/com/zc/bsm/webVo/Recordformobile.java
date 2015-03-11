package com.zc.bsm.webVo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class Recordformobile implements Serializable{
	private static final long serialVersionUID = 1L;
	public Recordformobile(){}

	private String id;
	private String name;
	private String rateTime;
	private String pendTime;
	private String transAmt;
	private String counttid;
	private String state;
	private String startdays;
	private String pstarttime;
	private String currentmoney;
	private String canrepay;
	private String type;

	
	
	public Recordformobile(String id,String name,String rateTime,String pendTime,String transAmt,String counttid,String state,String startdays,String pstarttime,String currentmoney,String canrepay){
		this.id=id;
		this.name=name;
		this.rateTime=rateTime;
		this.pendTime=pendTime;
		this.transAmt=transAmt;
		this.counttid=counttid;
		this.state=state;
		this.startdays=startdays;
		this.pstarttime=pstarttime;
		this.currentmoney=currentmoney;
		this.canrepay=canrepay;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRateTime() {
		return rateTime;
	}


	public void setRateTime(String rateTime) {
		this.rateTime = rateTime;
	}


	public String getPendTime() {
		return pendTime;
	}


	public void setPendTime(String pendTime) {
		this.pendTime = pendTime;
	}


	public String getTransAmt() {
		return transAmt;
	}


	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}


	public String getCounttid() {
		return counttid;
	}


	public void setCounttid(String counttid) {
		this.counttid = counttid;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getStartdays() {
		return startdays;
	}


	public void setStartdays(String startdays) {
		this.startdays = startdays;
	}


	public String getPstarttime() {
		return pstarttime;
	}


	public void setPstarttime(String pstarttime) {
		this.pstarttime = pstarttime;
	}


	public String getCurrentmoney() {
		return currentmoney;
	}


	public void setCurrentmoney(String currentmoney) {
		this.currentmoney = currentmoney;
	}


	public String getCanrepay() {
		return canrepay;
	}


	public void setCanrepay(String canrepay) {
		this.canrepay = canrepay;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	

}
