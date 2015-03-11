package com.zc.bsm.webVo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;

public class Record implements Serializable{
	private static final long serialVersionUID = 1L;
	public Record(){}
	public Record(Long id,String name,BigDecimal rate,BigDecimal db_name){
		this.id=id;
		this.name=name;
		this.rate=rate;
		this.db_name=db_name;
	}
	//sum(t.transAmt),count(t.id),
	//,Object transAmt,Integer count,
	private Long id;
	private String name;
	private BigDecimal rate;
	private Object transAmt;
	private Integer count;
	private BigDecimal db_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Object getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Object transAmt) {
		this.transAmt = transAmt;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getDb_name() {
		return db_name;
	}
	public void setDb_name(BigDecimal db_name) {
		this.db_name = db_name;
	}
	
}
