package com.warren.lolbox.model;

/**
 * 简单元工具
 * @author warren
 * @date 2014年12月28日
 */
public class SimpleTool {
	
	private int imgResId;
	private int txtResId;
	private String strText;
	
	public SimpleTool(int imgResId, int txtResId) {
		super();
		this.imgResId = imgResId;
		this.txtResId = txtResId;
	}
	
	public SimpleTool(int ImgResId, String strText){
		super();
		this.imgResId = ImgResId;
		this.strText = strText;
	}

	/**
	 * @return the imgResId
	 */
	public int getImgResId() {
		return imgResId;
	}

	/**
	 * @param imgResId the imgResId to set
	 */
	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}

	/**
	 * @return the txtResId
	 */
	public int getTxtResId() {
		return txtResId;
	}

	/**
	 * @param txtResId the txtResId to set
	 */
	public void setTxtResId(int txtResId) {
		this.txtResId = txtResId;
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
	
}
