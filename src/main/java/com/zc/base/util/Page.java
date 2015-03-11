/**   
 * @Title: Page.java
 * 
 * @Package com.tjopen.authority.util
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2010 tjopen.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Jan 13, 2010 3:33:07 PM
 * 
 * @version: V1.0   
 */

package com.zc.base.util;

import java.util.List;

/**
 * 分页对象
 * 
 * @pdOid 8539b44c-6883-459d-98f4-d59e2cc1e11c
 * @ClassName: Page
 * 
 * @Description:
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Jan 13, 2010 3:33:07 PM
 */
public class Page<E> extends QueryParameter {
	/** @pdOid 403db809-ea86-4c98-98c7-8234d7f94224 */
	private List<E> result = null;
	// 当前页
	private int currPage = 1;
	// 当前页
	private int currPages = 1;
	// 上一页
	private int prePage = 1;
	// 下一页
	private int nextPage = 1;
	// 总页数
	private int totalPage = 1;
	// 总记录数
	private int totalRecord = 0;
	// 每页记录数
	private int rows = 10;
	// 当前页记录开始位置
	private int startRecord = 0;
   //查询条件
	private String searchconditionStr="";
	public Page() {
	}

	public Page(int currPage, int rows, int totalRecord) {
		// 2元表达式
		this.totalRecord = totalRecord < 0 ? 0 : totalRecord;

		this.rows = rows < 1 ? 20 : rows;

		if (this.totalRecord % this.rows == 0) {
			this.totalPage = this.totalRecord / this.rows < 1 ? 1
					: this.totalRecord / this.rows;
		} else {
			this.totalPage = this.totalRecord / this.rows + 1;
		}

		this.currPage = currPage < 1 ? 1 : currPage;
		this.currPage = this.currPage > this.totalPage ? this.totalPage
				: this.currPage;

		this.prePage = this.currPage == 1 ? 1 : this.currPage - 1;
		this.nextPage = this.currPage == this.totalPage ? this.totalPage
				: this.currPage + 1;

		this.startRecord = (this.currPage - 1) * this.rows;
	}

	public void processPage(int currPage, int rows, int totalRecord) {
		this.totalRecord = totalRecord < 0 ? 0 : totalRecord;

		this.rows = rows < 1 ? 20 : rows;

		if (this.totalRecord % this.rows == 0) {
			this.totalPage = this.totalRecord / this.rows < 1 ? 1
					: this.totalRecord / this.rows;
		} else {
			this.totalPage = this.totalRecord / this.rows + 1;
		}

		this.currPage = currPage < 1 ? 1 : currPage;
		this.currPages = this.currPage > this.totalPage ? -1
				: this.currPage;
		this.currPage = this.currPage > this.totalPage ? this.totalPage
				: this.currPage;


		this.prePage = this.currPage == 1 ? 1 : this.currPage - 1;
		this.nextPage = this.currPage == this.totalPage ? this.totalPage
				: this.currPage + 1;

		this.startRecord = (this.currPage - 1) * this.rows;
	}

	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public static void main(String args[]) {
		Page page = new Page(0, 0, 0);
		//System.out.println(page);

	}

	public String getSearchconditionStr() {
		return searchconditionStr;
	}

	public void setSearchconditionStr(String searchconditionStr) {
		this.searchconditionStr = searchconditionStr;
	}

	public int getCurrPages() {
		return currPages;
	}

	public void setCurrPages(int currPages) {
		this.currPages = currPages;
	}


}