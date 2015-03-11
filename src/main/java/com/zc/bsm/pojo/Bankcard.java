package com.zc.bsm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zc.base.po.baseModel;

/**
 * 
 * 类描述：银行卡信息 创建者： 张少 项目名称： openMvc 类名： Bankcard.java 创建时间： 2014-07-03 上午10:52:29
 * 版本号： v1.0
 */
@Entity
@Table(name = "bankcard")
public class Bankcard {
	// 主键 UUID增长
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	// 创建时间
	@Column
	String createTime;
	// 修改时间
	@Column
	String updateTime;
	// 创建者
	@Column
	String createOper;
	// 修改者
	@Column
	String updateOper;
	@Column
	String cardNumber;
	@Column
	String bankId;
	@Column
	String bankName;
	@Column
	String usrCustId;
	@ManyToOne
	@JoinColumn(name = "userid")
	User user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCreateOper() {
		return createOper;
	}
	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}
	public String getUpdateOper() {
		return updateOper;
	}
	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}


}
