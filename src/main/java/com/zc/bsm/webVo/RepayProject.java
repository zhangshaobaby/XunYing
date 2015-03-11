package com.zc.bsm.webVo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author Administrator
 *
 */
public class RepayProject implements Serializable{
	private static final long serialVersionUID = 1L;
	public RepayProject(){}                     
	public RepayProject(String repayTime,BigDecimal repayMoney,Integer repayCount,Integer state,String start_time,String end_time,String cType){
		this.repayTime=repayTime;
		this.repayMoney=repayMoney;
		this.repayCount=repayCount;
		this.state=state;
		this.start_time=start_time;
		this.end_time=end_time;
		this.ctype=cType;
	}
	public RepayProject(Long id,Integer repaycount,String updateTime,String name,String repay_type,Integer borrowType,String borrowName,BigDecimal totalRepay,BigDecimal repay_money,BigDecimal repayMoney,String repayTime,String pEndTime){
		this.id=id;
		this.name=name;
		this.repay_type=repay_type;
		this.borrowType=borrowType;
		this.borrowName=borrowName;
		this.totalRepay=totalRepay;
		this.repay_money=repay_money;
		this.repayMoney=repayMoney;
		this.repayTime=repayTime;
		this.pendTime=pEndTime;
		this.repayCount=repaycount;
		this.updateTime=updateTime;
	}
	public RepayProject(Long id,String name,String repay_type,Integer borrowType,String borrowName,BigDecimal totalRepay,BigDecimal repay_money,BigDecimal repayMoney,String repayTime,String pEndTime){
		this.id=id;
		this.name=name;
		this.repay_type=repay_type;
		this.borrowType=borrowType;
		this.borrowName=borrowName;
		this.totalRepay=totalRepay;
		this.repay_money=repay_money;
		this.repayMoney=repayMoney;
		this.repayTime=repayTime;
		this.pendTime=pEndTime;
	}
	public RepayProject(Long id,String name,String repay_type,Integer borrowType,String borrowName,BigDecimal totalRepay,BigDecimal repay_money,String pEndTime,String pStartTime,Integer repayCount,Integer totalCount,Integer state){
		this.id=id;
		this.name=name;
		this.repay_type=repay_type;
		this.borrowType=borrowType;
		this.borrowName=borrowName;
		this.totalRepay=totalRepay;
		this.repay_money=repay_money;
		this.pendTime=pEndTime;
		this.pstartTime=pStartTime;
		this.repayCount = repayCount;
		this.totalCount = totalCount;
		this.state=state;
	}
	private Long id;
	private String name;
	private String repay_type;
	private Integer borrowType;
	private String borrowName;
	private BigDecimal totalRepay;
	private BigDecimal repay_money;
	private String updateTime;
	private String start_time;
	private String end_time;
	
	//计划/记录特有字段
	private BigDecimal repayMoney;
	private String repayTime;
	//计划/记录特有字段

	private String pendTime;
	private String pstartTime;
	//产品列表特有字段
	private Integer repayCount;
	private Integer totalCount;
	//审批通过待还款数
	private Integer accRepayCount;
	
	
	private Integer state;
	
	private Integer verifyState;
	
	private String ctype;
	

	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public Integer getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}
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
	public String getRepay_type() {
		return repay_type;
	}
	public void setRepay_type(String repay_type) {
		this.repay_type = repay_type;
	}
	public Integer getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}
	public BigDecimal getRepay_money() {
		return repay_money;
	}
	public void setRepay_money(BigDecimal repay_money) {
		this.repay_money = repay_money;
	}
	public BigDecimal getRepayMoney() {
		return repayMoney;
	}
	public void setRepayMoney(BigDecimal repayMoney) {
		this.repayMoney = repayMoney;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public Integer getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(Integer repayCount) {
		this.repayCount = repayCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getPendTime() {
		return pendTime;
	}
	public void setPendTime(String pendTime) {
		this.pendTime = pendTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPstartTime() {
		return pstartTime;
	}
	public void setPstartTime(String pstartTime) {
		this.pstartTime = pstartTime;
	}
	public Integer getAccRepayCount() {
		return accRepayCount;
	}
	public void setAccRepayCount(Integer accRepayCount) {
		this.accRepayCount = accRepayCount;
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
	
}
