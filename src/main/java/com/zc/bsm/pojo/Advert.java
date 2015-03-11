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
* 类描述： 产品
* 创建者： zl
* 项目名称： open1.1
* 类名： Advert.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="Advert")
public class Advert extends baseModel{
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
    /**
     * 名
     */
    @Column(length = 50)
    private String name;
    /**
	 * 跳转路径
	 */
	@Column(length = 50)
	private String href;
	/**
	 * 图片路径
	 */
	@Column(length = 50)
	private String imgUrl;
	/**
	 * 背景色
	 */
	@Column(length = 50)
	private String color;
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
	 * 状态 0 下线  1上线
	 */
	@Column
	private Integer state;
	
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
}
