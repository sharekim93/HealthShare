package com.cafe24.sharekim93.entity;

import java.util.List;

public class JsonList {
	private int count;
	private int pageLimit;
	private int pageAll;
	private int bottomList;
	private int page;
	private int startNum;
	private int lastNum;
	private List<Board> list;
	
	public JsonList() {	}

	public JsonList(int count, int pageLimit, int pageAll, int bottomList, int page, int startNum, int lastNum,
			List<Board> list) {
		super();
		this.count = count;
		this.pageLimit = pageLimit;
		this.pageAll = pageAll;
		this.bottomList = bottomList;
		this.page = page;
		this.startNum = startNum;
		this.lastNum = lastNum;
		this.list = list;
	}

	@Override
	public String toString() {
		return "List [count=" + count + ", pageLimit=" + pageLimit + ", pageAll=" + pageAll + ", bottomList="
				+ bottomList + ", page=" + page + ", startNum=" + startNum + ", lastNum=" + lastNum + ", list=" + list
				+ "]";
	}

	public int getCount() {
		return count;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getPageAll() {
		return pageAll;
	}

	public int getBottomList() {
		return bottomList;
	}

	public int getPage() {
		return page;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public List<Board> getList() {
		return list;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}

	public void setBottomList(int bottomList) {
		this.bottomList = bottomList;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}

	public void setList(List<Board> list) {
		this.list = list;
	}
}
