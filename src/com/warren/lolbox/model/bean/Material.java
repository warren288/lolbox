package com.warren.lolbox.model.bean;

import java.util.Map;

/**
 * 物品bean
 * @author warren
 * @date 2014年12月30日
 */
public class Material {

	private String id;
	private String name;
	private String description;
	private Map<String, String> extAttrs;
	private String need;
	private String compose;
	private String extDesc;
	private int price;
	private int allPrice;
	private int sellPrice;
	private String tags;
	
	public Material() {
		super();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the extAttrs
	 */
	public Map<String, String> getExtAttrs() {
		return extAttrs;
	}
	/**
	 * @param extAttrs the extAttrs to set
	 */
	public void setExtAttrs(Map<String, String> extAttrs) {
		this.extAttrs = extAttrs;
	}
	/**
	 * @return the need
	 */
	public String getNeed() {
		return need;
	}
	/**
	 * @param need the need to set
	 */
	public void setNeed(String need) {
		this.need = need;
	}
	/**
	 * @return the compose
	 */
	public String getCompose() {
		return compose;
	}
	/**
	 * @param compose the compose to set
	 */
	public void setCompose(String compose) {
		this.compose = compose;
	}
	/**
	 * @return the extDesc
	 */
	public String getExtDesc() {
		return extDesc;
	}
	/**
	 * @param exDesc the exDesc to set
	 */
	public void setExtDesc(String extDesc) {
		this.extDesc = extDesc;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the allPrice
	 */
	public int getAllPrice() {
		return allPrice;
	}
	/**
	 * @param allPrice the allPrice to set
	 */
	public void setAllPrice(int allPrice) {
		this.allPrice = allPrice;
	}
	/**
	 * @return the sellPrice
	 */
	public int getSellPrice() {
		return sellPrice;
	}
	/**
	 * @param sellPrice the sellPrice to set
	 */
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
