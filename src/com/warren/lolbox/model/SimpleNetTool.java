package com.warren.lolbox.model;

import android.content.Context;

public class SimpleNetTool {

	private String strImgUrl;
	private String strText;

	public SimpleNetTool(String strImgUrl, String strText) {
		this.strImgUrl = strImgUrl;
		this.strText = strText;
	}

	public SimpleNetTool(String strImgUrl, int txtResId, Context context) {
		this.strImgUrl = strImgUrl;
		this.strText = context.getResources().getString(txtResId);
	}

	/**
	 * @return the strText
	 */
	public String getStrText() {
		return strText;
	}

	/**
	 * @param strText the strText to set
	 */
	public void setStrText(String strText) {
		this.strText = strText;
	}

	/**
	 * @return the strImgUrl
	 */
	public String getStrImgUrl() {
		return strImgUrl;
	}

	/**
	 * @param strImgUrl the strImgUrl to set
	 */
	public void setStrImgUrl(String strImgUrl) {
		this.strImgUrl = strImgUrl;
	}
}
