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
import javax.persistence.Transient;

import com.zc.base.po.Dict;

/**
 * 充值订单表
 * @author Administrator
 *
 */
@Entity
@Table(name="RechargeOrder")
public class RechargeOrder {
//订单ID
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
//银行卡
@ManyToOne
@JoinColumn(name="bankId")
Dict bankId;
//充值金额
@Column(precision = 16, scale = 2)
BigDecimal amount;
//充值结果 0 成功 1失败
@Column
Integer flag;
//描述
@Column
String orderDesc;
//创建时间
@Column
String createTime;
// 修改时间
@Column
String updateTime;
//用户
@ManyToOne
@JoinColumn(name="userId")
User user;
//商户ID
@Column
String  huifuID;
//手续费金额
@Column
String  feeAmt;
//手续费金额手续费扣款客户号
@Column
String feeCustId;
//手续费扣款子账户号
@Column
String feeAcctId;
@Transient
private String uid;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
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
public String getOrderDesc() {
	return orderDesc;
}
public void setOrderDesc(String orderDesc) {
	this.orderDesc = orderDesc;
}
public Dict getBankId() {
	return bankId;
}
public void setBankId(Dict bankId) {
	this.bankId = bankId;
}
public Integer getFlag() {
	return flag;
}
public void setFlag(Integer flag) {
	this.flag = flag;
}
public String getFeeAmt() {
	return feeAmt;
}
public void setFeeAmt(String feeAmt) {
	this.feeAmt = feeAmt;
}
public String getFeeCustId() {
	return feeCustId;
}
public void setFeeCustId(String feeCustId) {
	this.feeCustId = feeCustId;
}
public String getFeeAcctId() {
	return feeAcctId;
}
public void setFeeAcctId(String feeAcctId) {
	this.feeAcctId = feeAcctId;
}
public String getHuifuID() {
	return huifuID;
}
public void setHuifuID(String huifuID) {
	this.huifuID = huifuID;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}

}
