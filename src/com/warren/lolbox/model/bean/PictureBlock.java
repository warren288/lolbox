package com.warren.lolbox.model.bean;

import java.util.List;

public class PictureBlock {
	private int totalPage;
	private int totalRecord;
	private int pageNum;
	private int pageSize;
	private List<PictureInfo> data;
	public PictureBlock() {
		super();
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @return the totalRecord
	 */
	public int getTotalRecord() {
		return totalRecord;
	}
	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
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
	 * @return the data
	 */
	public List<PictureInfo> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<PictureInfo> data) {
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PictureBlock [totalPage=" + totalPage + ", totalRecord=" + totalRecord
					+ ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", data=" + data + "]";
	}
	
	
}
