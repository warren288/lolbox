package com.warren.lolbox.model.bean;

import java.util.Map;

public class NewsDetail {
	private boolean rs;
	private String msg;
	private Map<String, Object> data;

	public NewsDetail() {
		super();
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
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
