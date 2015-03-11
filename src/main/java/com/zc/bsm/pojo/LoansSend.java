package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zc.base.po.baseModel;

/**
 * 
* 类描述： 产品
* 创建者： zl
* 项目名称： open1.1
* 类名： LoansSend.java
* 创建时间： 2014-8-10 
 */
@Entity
@Table(name="loansSend")
public class LoansSend extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    /**
     * 入账汇付号
     */
    @Column(length = 20)
    private String inCustId;
    /**
     * 出账汇付号
     */
    @Column(length = 20)
    private String outCustId;
    
    /**
     * 所属subOrdId  即标id
     */
    @Column(length = 20)
    private String ordId;
    /**
     * 时间
     */
    @Column(length = 10)
    private String ordDate;
    /**
     * 金额
     */
    @Column(precision = 16, scale = 2)
    private BigDecimal transAmt;
    /**
	 * 状态 -1失败 0 等待  1成功
	 */
	@Column(length=4)
	private Integer state;
	/**
	 * 返回错误代码
	 */
	@Column(length=4)
	private String errorCode;
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 更新时间
	 */
	@Column	(length = 20)
	private String updateTime;
	/**
	 * flag
	 */
	@Column	(length = 10)
	private String flag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInCustId() {
		return inCustId;
	}
	public void setInCustId(String inCustId) {
		this.inCustId = inCustId;
	}
	public String getOutCustId() {
		return outCustId;
	}
	public void setOutCustId(String outCustId) {
		this.outCustId = outCustId;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
