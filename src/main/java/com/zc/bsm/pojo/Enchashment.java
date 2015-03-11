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
 * 取现
 * @author Administrator
 *
 */
@Entity
@Table
public class Enchashment {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	/**
	 * 创建时间
	 */
	@Column	
	private String createTime;
    //取现金额
	@Column	
    private BigDecimal transAmt;
    //汇付客户号
	@Column	
    private String  usrCustId;
    //取现银行账号
	@Column	
    private String openAcctId;
    //开户银行代号
	@Column	
    private String openBankId;
    //手续费
	@Column	
    private BigDecimal feeAmt;
	@Column
	private String feeCustId;
	@Column
	private String feeAcctId;
	 //取现结果
	@Column	
	Integer flag;
    //备注
	@Column	
    private String cashDesc;
	//取现申请
	@ManyToOne
	@JoinColumn(name="enchashmentApply")
	private EnchashmentApply enchashmentApply;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public String getOpenAcctId() {
		return openAcctId;
	}
	public void setOpenAcctId(String openAcctId) {
		this.openAcctId = openAcctId;
	}
	public String getOpenBankId() {
		return openBankId;
	}
	public void setOpenBankId(String openBankId) {
		this.openBankId = openBankId;
	}
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getCashDesc() {
		return cashDesc;
	}
	public void setCashDesc(String cashDesc) {
		this.cashDesc = cashDesc;
	}
	public EnchashmentApply getEnchashmentApply() {
		return enchashmentApply;
	}
	public void setEnchashmentApply(EnchashmentApply enchashmentApply) {
		this.enchashmentApply = enchashmentApply;
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
}
