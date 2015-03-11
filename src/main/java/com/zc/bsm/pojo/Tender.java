package com.zc.bsm.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zc.base.po.Dict;
import com.zc.base.po.baseModel;
/**
 * 
* 类描述： 投标
* 创建者： zl
* 项目名称： open1.1
* 类名： Tender.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="Tender")
public class Tender {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 投标时间
     */
    @Column(length = 50)
    private String ordDate;
    /**
	 * 交易金额
	 */
   @Column(precision = 16, scale = 2)
	private BigDecimal transAmt;
   //产品
   @ManyToOne
   @JoinColumn(name="projectId")
    private Project project;
   //投标人
    @Column(name="UsrCustId")
    private String  usrCustId;
    @Column
    private String  username;
    //最大手续费率
    @Column
    private String maxTenderRate;
    @ManyToMany
	@JoinTable(name="tender_BorrowerDetail",joinColumns=@JoinColumn(name="tenderId"),inverseJoinColumns=@JoinColumn(name="BorrowerDetailId"))
    private List<BorrowerDetailPo>   borrowerDetails;
    //是否冻结
    @Column
    private String   isFreeze;
    //投标结果
    @Column
    Integer flag;
    //描述
    @Column
    String tenderDesc;
   //冻结订单号
    @Column
    String freezeOrdId;
    
    //冻结标识
    @Column
    String freezeTrxId;
    /**
	 * 状态  -1失败 0 等待  1成功
	 */
	@Column(length=4)
	private Integer state;
	/**
     * 解冻ordId
     */
    @Column
	private String unFreezeOrdId;
    //解冻状态  0  等待响应  1 解冻失败 2 解冻成功
    @Column
    private Integer unFreezeState;
    /**
     * 红包id
     */
    @Column
    private Long luckyId;
    //创建时间
	@Column	
  String createTime;
  //修改时间
	@Column	
   String updateTime;
	// 0 投标 1转让
   Integer type;
   //标的计息开始时间
   String start_time;
   //标的计息结束时间
   String end_time;
   //原标ID
   Long srcTenderId;
   //回款金额
    BigDecimal  repayAmt;
    //所属债权转让
   Long creditAssignTenderId;
   //原投资金额
   BigDecimal  srcTransAmt;
   //父标 债权转让 之后 债权转让标和剩余价值标的 parentTender 为当前标主键
   Long parentTender;
	public Long getLuckyId() {
		return luckyId;
	}
	public void setLuckyId(Long luckyId) {
		this.luckyId = luckyId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public String getMaxTenderRate() {
		return maxTenderRate;
	}
	public void setMaxTenderRate(String maxTenderRate) {
		this.maxTenderRate = maxTenderRate;
	}
	public List<BorrowerDetailPo> getBorrowerDetails() {
		return borrowerDetails;
	}
	public void setBorrowerDetails(List<BorrowerDetailPo> borrowerDetails) {
		this.borrowerDetails = borrowerDetails;
	}
	public String getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getTenderDesc() {
		return tenderDesc;
	}
	public void setTenderDesc(String tenderDesc) {
		this.tenderDesc = tenderDesc;
	}
	public String getFreezeOrdId() {
		return freezeOrdId;
	}
	public void setFreezeOrdId(String freezeOrdId) {
		this.freezeOrdId = freezeOrdId;
	}
	public String getFreezeTrxId() {
		return freezeTrxId;
	}
	public void setFreezeTrxId(String freezeTrxId) {
		this.freezeTrxId = freezeTrxId;
	}
	public String getUnFreezeOrdId() {
		return unFreezeOrdId;
	}
	public void setUnFreezeOrdId(String unFreezeOrdId) {
		this.unFreezeOrdId = unFreezeOrdId;
	}
	public Integer getUnFreezeState() {
		return unFreezeState;
	}
	public void setUnFreezeState(Integer unFreezeState) {
		this.unFreezeState = unFreezeState;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getSrcTenderId() {
		return srcTenderId;
	}
	public void setSrcTenderId(Long srcTenderId) {
		this.srcTenderId = srcTenderId;
	}
	public BigDecimal getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}
	public Long getCreditAssignTenderId() {
		return creditAssignTenderId;
	}
	public void setCreditAssignTenderId(Long creditAssignTenderId) {
		this.creditAssignTenderId = creditAssignTenderId;
	}
	public BigDecimal getSrcTransAmt() {
		return srcTransAmt;
	}
	public void setSrcTransAmt(BigDecimal srcTransAmt) {
		this.srcTransAmt = srcTransAmt;
	}
	public Long getParentTender() {
		return parentTender;
	}
	public void setParentTender(Long parentTender) {
		this.parentTender = parentTender;
	}


}
