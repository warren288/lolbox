package com.warren.lolbox.model.bean;

import java.util.List;

/**
 * 所有英雄列表
 * @author warren
 * @date 2015年1月1日
 */
public class AllHeroList {
	
	private List<HeroSimple> all;
	
	public AllHeroList() {
		super();
	}

	/**
	 * @return the all
	 */
	public List<HeroSimple> getAll() {
		return all;
	}

	/**
	 * @param all the all to set
	 */
	public void setAll(List<HeroSimple> all) {
		this.all = all;
	}

	
}
