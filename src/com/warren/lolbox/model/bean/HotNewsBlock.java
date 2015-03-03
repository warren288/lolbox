package com.warren.lolbox.model.bean;

import java.util.List;

public class HotNewsBlock {
	private List<HotNewsBrief> content;
	private String createTime;
	private String des;
	private String id;
	private String title;
	public HotNewsBlock() {
		super();
	}
	/**
	 * @return the content
	 */
	public List<HotNewsBrief> getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(List<HotNewsBrief> content) {
		this.content = content;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}
	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
