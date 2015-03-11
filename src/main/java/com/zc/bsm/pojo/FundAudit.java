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

/**
 * 资金审核
 * @author Administrator
 *
 */
@Entity
@Table
public class FundAudit {
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
    /**
     * 金额
     */
    @Column(precision = 16, scale = 2)
    private BigDecimal transAmt;
	/**
	 *描述 
	 */
    @Column
    private String fundDesc;
    
    /**
     * 产品
     */
    @ManyToOne
    @JoinColumn(name="projectid")
    private Project project;
    /**
     * 状态 0 批准，1 打回
     */ 
    @Column
    private Integer flag;
    /**
     * 取现申请被批准后，资金冻结后的冻结ID
     */
    @Column
    private String freeid;
    /**
     * 资金冻结后的 申请资金解冻后的解冻id
     */
    @Column
    private String unfreeid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getFundDesc() {
		return fundDesc;
	}
	public void setFundDesc(String fundDesc) {
		this.fundDesc = fundDesc;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getFreeid() {
		return freeid;
	}
	public void setFreeid(String freeid) {
		this.freeid = freeid;
	}
	public String getUnfreeid() {
		return unfreeid;
	}
	public void setUnfreeid(String unfreeid) {
		this.unfreeid = unfreeid;
	}
}
