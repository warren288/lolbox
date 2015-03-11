package com.warren.lolbox.model.bean;

import java.util.List;

public class NewsTopicRoot {
	private String code;
	private String title;
	private String desc;
	private List<NewsTopicOuter> data;

	public NewsTopicRoot() {
		super();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the data
	 */
	public List<NewsTopicOuter> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<NewsTopicOuter> data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsTopicRoot [code=" + code + ", title=" + title + ", desc=" + desc + ", data="
					+ data + "]";
	}
}
