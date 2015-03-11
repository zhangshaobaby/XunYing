package com.zc.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class DictType {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	// 数据字典类型编号
	@Column	
	String dictTypeid;
	// 数据字典类型名称
	@Column
	String dictTypeName;

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDictTypeid() {
		return dictTypeid;
	}

	public void setDictTypeid(String dictTypeid) {
		this.dictTypeid = dictTypeid;
	}
}
