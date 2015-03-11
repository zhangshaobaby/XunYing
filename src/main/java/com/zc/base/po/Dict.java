package com.zc.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Dict {
	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	// 数据字典
	@Column
	String dictid;
	// 字典层级
	@Column
	String seqno;
	// 中文层级
	@Column
	String seqCn;
	// 数据字典名称
	@Column
	String dictName;
	@ManyToOne
	@JoinColumn(name = "dictTypeid")
	DictType dictType;
	// 备注
	@Column
	String remark;

	public Dict() {
		super();
	}

	public Dict(Integer id) {
		super();
		this.id = id;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getSeqCn() {
		return seqCn;
	}

	public void setSeqCn(String seqCn) {
		this.seqCn = seqCn;
	}

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
