package com.zc.bsm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zc.base.po.baseModel;

/**
 * 
* 类描述： 借款方
* 创建者： zl
* 项目名称： open1.1
* 类名： Borrower.java
* 创建时间： 2014-7-30 
 */
@Entity
@Table(name="Borrower")
public class Borrower extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 名
     */
    @Column(length = 50)
    private String account;
    /**
     * 名
     */
    @Column(length = 50)
    private String pwd;
    /**
     * 名
     */
    @Column(length = 50)
    private String name;
    //组织机构代码
    @Column(length = 50)
	private String instuCode;
	
	//营业执照编号
	@Column(length = 50)
	private String busiCode;
	
	//税务登记号
	@Column(length = 50)
	private String taxCode;

	
	//用户客户号，由汇付生成，用户的唯一性标识
	@Column(length = 16)
	private String usrCustId;
	//用户登陆名
	@Column(length = 50)
	private String usrCustAccount;
	/**
	 * 状态 0 下线  1上线
	 */
	@Column
	private Integer state;
	/**
	 * 公司/个人 0公司  1个人
	 */
	@Column
	private Integer type;
	/**
	 * 用户关联id
	 */
	@Column
	private String uid;
	/**
	 * 银行卡id
	 */
	@Column
	private String bankId;
	/**
	 * 银行卡卡号
	 */
	@Column
	private String cardId;
	
	/**
	 * 创建人id
	 */
	@Column(length = 10)
	private Integer create_user;
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 创建人名称
	 */
	@Column(length = 20)
	private String create_username;
	/**
	 * 删除标识
	 */
	@Column(length = 10)
	private String flag;
	/**
	 * 更新时间
	 */
	@Column	(length = 20)
	private String updateTime;
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
	public String getInstuCode() {
		return instuCode;
	}
	public void setInstuCode(String instuCode) {
		this.instuCode = instuCode;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreate_username() {
		return create_username;
	}
	public void setCreate_username(String create_username) {
		this.create_username = create_username;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsrCustAccount() {
		return usrCustAccount;
	}
	public void setUsrCustAccount(String usrCustAccount) {
		this.usrCustAccount = usrCustAccount;
	}
}
