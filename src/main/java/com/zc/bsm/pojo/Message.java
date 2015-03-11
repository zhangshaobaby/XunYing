package com.zc.bsm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.zc.base.po.Dict;
@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private   String id;
	@Column
	private	String createTime;
	// 修改时间
	@Column
	private	String updateTime;
	//消息来源
	@ManyToOne
	@JoinColumn
	private User fromUser;
	//消息送达用户
	@ManyToOne
	@JoinColumn
	private User destUser;
	//消息类型。。。59 系统消息
	@ManyToOne
	@JoinColumn(name="type")
	private Dict type;
	//消息内容
	private String content;
	//是否查看 0 未读 。 1 已读
	private Integer haveLook;
	@Transient
    private Integer page=1;
	@Transient
    private String  mid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getDestUser() {
		return destUser;
	}
	public void setDestUser(User destUser) {
		this.destUser = destUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHaveLook() {
		return haveLook;
	}
	public void setHaveLook(int haveLook) {
		this.haveLook = haveLook;
	}
	public Dict getType() {
		return type;
	}
	public void setType(Dict type) {
		this.type = type;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
}
