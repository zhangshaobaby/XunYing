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
* 类描述： 屌丝宝动态非到期日利率表
* 创建者： zl
* 项目名称： open1.1
* 类名： DynamicRate.java
* 创建时间： 2014-9-5
 */
@Entity
@Table(name="DynamicRate")
public class DynamicRate {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    /**
     * 起始时间
     */
    @Column(length = 50)
    private String startTime;
    /**
	 * 非到期日利率（真实计算）
	 */
	@Column(precision = 10, scale = 8)
	private BigDecimal day_rate;
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
	//创建者
	@Column	
	private String createOper;
	//修改者
	@Column	
	private String updateOper;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public BigDecimal getDay_rate() {
		return day_rate;
	}
	public void setDay_rate(BigDecimal day_rate) {
		this.day_rate = day_rate;
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
}
