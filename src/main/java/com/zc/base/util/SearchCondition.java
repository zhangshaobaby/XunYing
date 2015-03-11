/**
 * 
 */
package com.zc.base.util;

/**
 * @author Administrator
 * 
 */
public class SearchCondition {
	private String key = "";
	private String operator = "=";
	private String value = "";

	private String conditionalRaletion = "AND";
	private String group;
	//查询条件的引用类型 多对多还是多对一
    private String refType;
	public String getConditionalRaletion() {
		return conditionalRaletion;
	}

	public String getGroup() {
		return group;
	}

	public String getKey() {
		return key;
	}

	public String getOperator() {
		return operator;
	}

	public String getValue() {
		return value;
	}

	public void setConditionalRaletion(String conditionalRaletion) {
		this.conditionalRaletion = conditionalRaletion;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setValue(String value) {
		this.value = value;
	}
	// 重写equals
	public boolean equals(SearchCondition obj) {
		if (this.getKey().trim().equals(obj.getKey().trim())
				/*&& this.getConditionalRaletion().trim().equals(
						obj.getConditionalRaletion().trim())
				&& this.getOperator().trim().equals(obj.getOperator().trim())
				&& this.getValue().trim().equals(obj.getValue().trim())*/) {
			return true;
		}
			return false;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

}
