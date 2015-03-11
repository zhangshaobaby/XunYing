package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zc.base.po.Dict;

/**
 * 自动扣款转账（平台专用）
 * @author Administrator
 *
 */
@Entity
@Table(name="PlatformTransfer")
public class PlatformTransfer {
//订单ID
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
//平台出帐账户ID
@Column
String outCustId;
//平台出帐账户子帐号
@Column
String outAcctId;
//支付金额
@Column(precision = 16, scale = 2)
BigDecimal transAmt;
//入账账户
@Column
String inCustId;
//入账账户子账户
@Column
String inAcctId;

//支付结果 0 支付成功 1支付失败
@Column
Integer flag;
//返回错误代码
@Column(length=4)
private String errorCode;
//描述
@Column
String orderDesc;
//创建时间
@Column
String createTime;
// 修改时间
@Column
String updateTime;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getOutCustId() {
	return outCustId;
}
public void setOutCustId(String outCustId) {
	this.outCustId = outCustId;
}
public String getOutAcctId() {
	return outAcctId;
}
public void setOutAcctId(String outAcctId) {
	this.outAcctId = outAcctId;
}
public BigDecimal getTransAmt() {
	return transAmt;
}
public void setTransAmt(BigDecimal transAmt) {
	this.transAmt = transAmt;
}
public String getInCustId() {
	return inCustId;
}
public void setInCustId(String inCustId) {
	this.inCustId = inCustId;
}
public String getInAcctId() {
	return inAcctId;
}
public void setInAcctId(String inAcctId) {
	this.inAcctId = inAcctId;
}
public Integer getFlag() {
	return flag;
}
public void setFlag(Integer flag) {
	this.flag = flag;
}
public String getOrderDesc() {
	return orderDesc;
}
public void setOrderDesc(String orderDesc) {
	this.orderDesc = orderDesc;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}
public String getErrorCode() {
	return errorCode;
}
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}


}
