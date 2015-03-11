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
 * 
* 类描述： 资金流水
* 创建者： zl
* 项目名称： open1.1
* 类名： CashStream.java
* 创建时间： 2014-8-4 
 */
@Entity
@Table(name="cashStream")
public class CashStream {
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
     * 所属orderid
     */
    @Column(length = 20)
    private String ordId;
    /**
     * 时间
     */
    @Column
    private String ordDate;
    /**
     * 金额
     */
    @Column(precision = 16, scale = 2)
    private BigDecimal transAmt;
    /**
     * 6种
     * 种类 55 投标资金冻结(申请投资)  31 解冻资金并放款  32还本 58 付息  33 充值   34  提现   35  平台转账   56  债权购买
     */
    @ManyToOne
    @JoinColumn(name="transtype")
    private Dict type;
	@Column(length = 1)
	private Integer flag;
	/**
	 * 创建时间
	 */
	@Column	
	private String createTime;
	/**
	 * 更新时间
	 */
	@Column	
	private String updateTime;
	//可用余额
    @Column(precision = 16, scale = 2)
    private String avlBal;
	//摘要
	private String   summary;
	@Transient
    private Integer page=1;
	@Transient
    private String uid;
	@Transient
    private String start_time;
	@Transient
    private String end_time;
	@Transient
    private String transtype;
	@Transient
    private String servFee;
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
	public Dict getType() {
		return type;
	}
	public void setType(Dict type) {
		this.type = type;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAvlBal() {
		return avlBal;
	}
	public void setAvlBal(String avlBal) {
		this.avlBal = avlBal;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	public String getServFee() {
		return servFee;
	}
	public void setServFee(String servFee) {
		this.servFee = servFee;
	}
	
}
