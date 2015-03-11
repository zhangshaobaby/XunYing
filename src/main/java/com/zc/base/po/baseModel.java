package com.zc.base.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

public class baseModel {
	//主键 UUID增长
	 @Id
	   @GeneratedValue(generator = "uuid")
	   @GenericGenerator(name = "uuid", strategy = "uuid")
  String id;
  //创建时间
	@Column	
  String createTime;
  //修改时间
	@Column	
  String updateTime;
  //创建者
	@Column	
  String createOper;
  //修改者
	@Column	
  String updateOper;
	//修改者
	@ManyToOne
  Dict flag;
}
