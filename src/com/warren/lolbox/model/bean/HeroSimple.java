package com.warren.lolbox.model.bean;


/**
 * 英雄简要信息Bean
 * @author warren
 * @date 2015年1月1日
 */
public class HeroSimple implements ISimple{

	private String cnName;
	private String enName;
	private String location;
	private String price;
	private String rating;
	private String tags;
	private String title;

	public HeroSimple() {
	}

	/**
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}

	/**
	 * @param cnName the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	/**
	 * @return the enName
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * @param enName the enName to set
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeroSimple [cnName=" + cnName + ", enName=" + enName + ", location=" + location
					+ ", price=" + price + ", rating=" + rating + ", tags=" + tags + ", title="
					+ title + "]";
	}

}
