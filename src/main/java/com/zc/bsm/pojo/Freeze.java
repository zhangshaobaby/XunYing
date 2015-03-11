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
 * 资金冻结表
 * @author Administrator
 *
 */
@Entity
@Table
public class Freeze {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	private String usrCustId;
	private String subAcctType;
	private String subAcctId;
    /**
     * 金额
     */
    @Column(precision = 16, scale = 2)
    private BigDecimal transAmt;
    private String trxId;
    //冻结或者解冻 40 冻结 41解冻
    @ManyToOne
    @JoinColumn(name="type")
    private Dict type;
	private String createTime;
	private String updateTime;
	private Integer flag;
	//资金申请id
    private Long applyid;
    
    private String respCode;
    
    private String respDesc;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public String getSubAcctType() {
		return subAcctType;
	}
	public void setSubAcctType(String subAcctType) {
		this.subAcctType = subAcctType;
	}
	public String getSubAcctId() {
		return subAcctId;
	}
	public void setSubAcctId(String subAcctId) {
		this.subAcctId = subAcctId;
	}
	public Long getApplyid() {
		return applyid;
	}
	public void setApplyid(Long applyid) {
		this.applyid = applyid;
	}
	public Dict getType() {
		return type;
	}
	public void setType(Dict type) {
		this.type = type;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	
	
}
