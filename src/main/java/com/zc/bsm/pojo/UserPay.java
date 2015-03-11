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
 * 用户支付表
 * @author Administrator
 *
 */
@Entity
@Table(name="UserPay")
public class UserPay {
//订单ID
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
//支付人客户号（汇付id）
@Column
String usrCustId;
//支付金额
@Column(precision = 16, scale = 2)
BigDecimal transAmt;
//收款人账户子账户（默认填写：MDT000001）
@Column
String inAcctId;
//收款人账户类型：BASEDT （ 基 本 借 记 户 ）； DEP( 保 证 金 账 户 ),MERDT(专属借记帐户) 默认填写：MERDT
@Column
String inAcctType;
//支付结果 0 成功 1失败
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
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUsrCustId() {
	return usrCustId;
}
public void setUsrCustId(String usrCustId) {
	this.usrCustId = usrCustId;
}
public BigDecimal getTransAmt() {
	return transAmt;
}
public void setTransAmt(BigDecimal transAmt) {
	this.transAmt = transAmt;
}
public String getInAcctId() {
	return inAcctId;
}
public void setInAcctId(String inAcctId) {
	this.inAcctId = inAcctId;
}
public String getInAcctType() {
	return inAcctType;
}
public void setInAcctType(String inAcctType) {
	this.inAcctType = inAcctType;
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


}
