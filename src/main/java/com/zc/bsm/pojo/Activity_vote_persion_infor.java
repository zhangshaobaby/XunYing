package com.zc.bsm.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 投票女神信息表
 * @author lyh
 *
 */
@Entity
@Table(name = "activity_vote_persion_infor")
public class Activity_vote_persion_infor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	@Column
	//姓名
	String username;
	@Column
	//年龄
	String age;
	//血型
	@Column
	String blood;
	//身高
	@Column
	String height ;
	@Column
	//星座
	String constellation;
	@Column
	//体重
	String weight;
	@Column
	//三围
	String vitalstatistics;
	@Column
	//简介
	String summery;
	// 头像地址
	@Column
	String headpic;
	// 大图展示地址
	@Column
	String showpic;
	
	// 写真地址
	@Column
	String imgUrl;
	// 所得票数
	@Column
	Long totalvote;
	// 标志位
	@Column
	String flag;
	//时间
	@Column
	String inserttime;
	//备注
	@Column
	String remark;
	@Transient
    private String picpath;
	@Transient
    private String persent;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVitalstatistics() {
		return vitalstatistics;
	}
	public void setVitalstatistics(String vitalstatistics) {
		this.vitalstatistics = vitalstatistics;
	}
	public String getSummery() {
		return summery;
	}
	public void setSummery(String summery) {
		this.summery = summery;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public Long getTotalvote() {
		return totalvote;
	}
	public void setTotalvote(Long totalvote) {
		this.totalvote = totalvote;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getInserttime() {
		return inserttime;
	}
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPersent() {
		return persent;
	}
	public void setPersent(String persent) {
		this.persent = persent;
	}
	public String getShowpic() {
		return showpic;
	}
	public void setShowpic(String showpic) {
		this.showpic = showpic;
	}

}
