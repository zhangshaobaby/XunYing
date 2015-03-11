package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
* 类描述： 产品
* 创建者： zl
* 项目名称： open1.1
* 类名： LuckyMoney.java
* 创建时间： 2014-8-26 
 */
@Entity
@Table(name="LuckyMoney")
public class LuckyMoney {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 红包数额
     */
    @Column	(precision = 8, scale = 2)
    private BigDecimal money;
    /**
	 * 单笔投资金额限制
	 */
	@Column(precision = 8, scale = 2)
	private BigDecimal investLimit;
	/**
	 * 用户id
	 */
	@Column
	private String uid;
	/**
	 * 标id
	 */
	@Column
	private Long tenderId;
	/**
	 * 产品id
	 */
	@Column
	private Long projectId;
	/**
	 * 红包来源
	 */
	@Column(length = 8)
	private Long comeFrom;
	
	/**
	 * 开始时间
	 */
	@Column(length = 20)
	private String start_time;
	/**
	 * 结束时间
	 */
	@Column(length = 20)
	private String end_time;
	/**
	 * 状态   0 未使用  1 使用中  2待审核   3已使用   4已转换为积分
	 */
	@Column(length = 8)
	private Integer state;
	/**
	 * 使用产品类型 0123   逗号分隔
	 */
	@Column(length = 8)
	private String type;
	/**
	 * 手机号（外部推广用）
	 */
	@Column(length = 11)
	private String phone;
	
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 删除标识
	 */
	@Column(length = 10)
	private Integer flag;
	/**
	 * 更新时间
	 */
	@Column	(length = 20)
	private String updateTime;
	
	@Transient
    private Integer page=1;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getInvestLimit() {
		return investLimit;
	}
	public void setInvestLimit(BigDecimal investLimit) {
		this.investLimit = investLimit;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Long getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(Long comeFrom) {
		this.comeFrom = comeFrom;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Long getTenderId() {
		return tenderId;
	}
	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
