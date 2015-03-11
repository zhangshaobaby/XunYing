package com.zc.bsm.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 投票流水表
 * @author lyh
 *
 */
@Entity
@Table(name = "activity_vote")
public class Activity_vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	@Column
	//女神id
	Long persionid;
	@Column
	//投票人id
	String uid;
	// 标志位
	@Column
	String flag;
	//时间
	@Column
	String inserttime;
	//备注
	@Column
	String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPersionid() {
		return persionid;
	}
	public void setPersionid(Long persionid) {
		this.persionid = persionid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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

}
