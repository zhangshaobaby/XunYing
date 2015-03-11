/**   
 * @Title: QueryParameter.java
 * 
 * @Package com.tjopen.authority.util
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2010 tjopen.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Jan 13, 2010 3:30:54 PM
 * 
 * @version: V1.0   
 */

package com.zc.base.util;

import java.util.List;
import java.util.Map;



/**
 * 查询参数
 * 
 * @pdOid 817fc4dc-1614-4a81-8911-c57d620d8bb7
 * @ClassName: QueryParameter
 * 
 * @Description: 本类封装分页和排序查询请求参数.参考自springside的ORM封装设计
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Jan 13, 2010 3:30:54 PM
 */
public class QueryParameter {
	// 当前查询实体
	protected String entityName;
	// 查询条件,当前查询实体属性的键值对,支持级联属性(以xxx.xxx的形式)
	protected Map<String, String> searchMap;
	// 查询条件,当前查询实体属性的条件列表,支持级联属性(以xxx.xxx的形式)
	protected List<SearchCondition> searchCondition;

	// 排序条件,Key为当前查询实体的属性名称,支持级联属性(以xxx.xxx的形式)
	protected Map<String, String> sortMap;
	protected List<String> groupList;
	// 特殊处理 查询HQL
	protected String hql;
	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> Map) {
		this.searchMap = Map;
	}

	public Map<String, String> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}

	public List<SearchCondition> getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(List<SearchCondition> searchCondition) {
		this.searchCondition = searchCondition;
	}

	public List<String> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}

	



}