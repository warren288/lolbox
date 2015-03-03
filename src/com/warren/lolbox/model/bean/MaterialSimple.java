package com.warren.lolbox.model.bean;

/**
 * 简化版的物品bean
 * @author warren
 * @date 2014年12月31日
 */
public class MaterialSimple implements ISimple{
	
	private int id;
	private String text;

	public MaterialSimple(){
		
	}
	
	public MaterialSimple(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
