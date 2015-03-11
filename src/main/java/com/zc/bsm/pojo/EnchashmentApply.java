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
 * 提现申请表
 * @author Administrator
 *
 */
@Entity
@Table
public class EnchashmentApply {
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
	@ManyToOne
	@JoinColumn(name="borrowerid")
	private Borrower borrower;
    //取现银行账号
	@Column	
    private String openAcctId;
    //开户银行代号
	@Column	
    private String openBankId;
	//审批结果
	@ManyToOne
	@JoinColumn(name="state")
	private Dict state;
	//审批意见
	private String examineOption;
	//提示信息列、用于审批时 故障提醒
	private String tipmes;
	//冻结记录
	@ManyToOne
	@JoinColumn(name="freezeId")
	private Freeze freeze;
	//解冻记录
	@ManyToOne
	@JoinColumn(name="unfreezeId")
	private Freeze unfreeze;
    //备注
	@Column	
    private String cashDesc;
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
	public String getCashDesc() {
		return cashDesc;
	}
	public void setCashDesc(String cashDesc) {
		this.cashDesc = cashDesc;
	}
	public Dict getState() {
		return state;
	}
	public void setState(Dict state) {
		this.state = state;
	}
	public String getExamineOption() {
		return examineOption;
	}
	public void setExamineOption(String examineOption) {
		this.examineOption = examineOption;
	}
	public String getTipmes() {
		return tipmes;
	}
	public void setTipmes(String tipmes) {
		this.tipmes = tipmes;
	}
	public Freeze getFreeze() {
		return freeze;
	}
	public void setFreeze(Freeze freeze) {
		this.freeze = freeze;
	}
	public Freeze getUnfreeze() {
		return unfreeze;
	}
	public void setUnfreeze(Freeze unfreeze) {
		this.unfreeze = unfreeze;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	
    
}
