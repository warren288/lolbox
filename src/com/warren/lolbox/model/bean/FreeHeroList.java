package com.warren.lolbox.model.bean;

import java.util.List;

/**
 * 免费英雄列表
 * @author warren
 * @date 2015年1月1日
 */
public class FreeHeroList {
	
	private String desc;
	private List<HeroSimple> free;

	public FreeHeroList() {
		super();
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
	 * @return the free
	 */
	public List<HeroSimple> getFree() {
		return free;
	}

	/**
	 * @param free the free to set
	 */
	public void setFree(List<HeroSimple> free) {
		this.free = free;
	}

}
