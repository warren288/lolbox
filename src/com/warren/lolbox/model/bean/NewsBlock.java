package com.warren.lolbox.model.bean;

import java.util.List;

public class NewsBlock {
	private String totalRecord;
	private String totalPage;
	private List<NewsInfo> data;
	private boolean rs;
	private String msg;
	private String time;
	private int pageSize;
	private int pageNum;
	private List<NewsInfo> headerline;
	private String order;

	public NewsBlock() {
		super();
	}

	/**
	 * @return the totalRecord
	 */
	public String getTotalRecord() {
		return totalRecord;
	}

	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * @return the totalPage
	 */
	public String getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the data
	 */
	public List<NewsInfo> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<NewsInfo> data) {
		this.data = data;
	}

	/**
	 * @return the rs
	 */
	public boolean isRs() {
		return rs;
	}

	/**
	 * @param rs the rs to set
	 */
	public void setRs(boolean rs) {
		this.rs = rs;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the headline
	 */
	public List<NewsInfo> getHeaderline() {
		return headerline;
	}

	/**
	 * @param headline the headline to set
	 */
	public void setHeaderline(List<NewsInfo> headerline) {
		this.headerline = headerline;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsBlock [totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", data="
					+ data + ", rs=" + rs + ", msg=" + msg + ", time=" + time + ", pageSize="
					+ pageSize + ", pageNum=" + pageNum + ", headerline=" + headerline + ", order="
					+ order + "]";
	}

}
