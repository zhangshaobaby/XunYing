package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户表
 * @author 张少
 *
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	//主键
	String id;
	@Column
	//用户名
	String username;
	@Column
	//昵称
	String ninkName;
	//真名
	@Column
	String realName;
	//身份证
	@Column
	String identification ;
	@Column
	//手机
	String phone;
	@Column
	//登录密码
	String pwd;
	@Column
	//支付密码
	String paypwd;
	@Column
	//邮件
	String email;
	// 创建时间
	@Column
	String createTime;
	// 修改时间
	@Column
	String updateTime;
	// 创建者
	@Column
	String createOper;
	// 修改者
	@Column
	String updateOper;
	//实名认证时间
	@Column
	String realNameAuthentDate;
	//手机认证时间
	@Column
	String phoneAuthentDate;
	//汇付账号
	@Column
	String huifuAccount;
	//汇付ID
	@Column
	String huifuID;
	//积分
	Integer score;
	//推广人
	String  agent;
	//理财顾问
	String advisor; 
	//好友
	@ManyToOne
	@JoinColumn(name="friend")
    User  friend;
    //上次签到时间
	String lastsignDate;
    //累计投资额
   @Column(precision = 16, scale = 2)
	private BigDecimal alltransAmt;
   //手机验证码
   @Transient
   private String vercode;
   //验证标志位
   @Transient
   private String flag;
   @Transient
   private String oldpwd;
   //用户类型 1 普通用户 2经纪人用户
   private Integer type;
 //注册来源 空为平台 1为手机端 2为屌丝活动 3为线下手机端（微信号）
	@Column
	private String source;
   
   
   
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNinkName() {
		return ninkName;
	}
	public void setNinkName(String ninkName) {
		this.ninkName = ninkName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getRealNameAuthentDate() {
		return realNameAuthentDate;
	}
	public void setRealNameAuthentDate(String realNameAuthentDate) {
		this.realNameAuthentDate = realNameAuthentDate;
	}
	public String getPhoneAuthentDate() {
		return phoneAuthentDate;
	}
	public void setPhoneAuthentDate(String phoneAuthentDate) {
		this.phoneAuthentDate = phoneAuthentDate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getPaypwd() {
		return paypwd;
	}
	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}
	public String getHuifuAccount() {
		return huifuAccount;
	}
	public void setHuifuAccount(String huifuAccount) {
		this.huifuAccount = huifuAccount;
	}
	public String getHuifuID() {
		return huifuID;
	}
	public void setHuifuID(String huifuID) {
		this.huifuID = huifuID;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getLastsignDate() {
		return lastsignDate;
	}
	public void setLastsignDate(String lastsignDate) {
		this.lastsignDate = lastsignDate;
	}
	public BigDecimal getAlltransAmt() {
		return alltransAmt;
	}
	public void setAlltransAmt(BigDecimal alltransAmt) {
		this.alltransAmt = alltransAmt;
	}
	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAdvisor() {
		return advisor;
	}
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	

}
