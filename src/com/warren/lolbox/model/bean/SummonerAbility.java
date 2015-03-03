package com.warren.lolbox.model.bean;

import java.io.Serializable;

/**
 * 召唤师技能
 * @author warren
 * @date 2015年1月4日
 */
public class SummonerAbility implements Serializable {

	private static final long serialVersionUID = -1195836253652127175L;

	private String cooldown;
	private String des;
	private String id;
	private String level;
	private String name;
	private String strong;
	private String tips;

	public SummonerAbility() {
		super();
	}

	/**
	 * @return the cooldown
	 */
	public String getCooldown() {
		return cooldown;
	}

	/**
	 * @param cooldown the cooldown to set
	 */
	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
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
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
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
	 * @return the strong
	 */
	public String getStrong() {
		return strong;
	}

	/**
	 * @param strong the strong to set
	 */
	public void setStrong(String strong) {
		this.strong = strong;
	}

	/**
	 * @return the tips
	 */
	public String getTips() {
		return tips;
	}

	/**
	 * @param tips the tips to set
	 */
	public void setTips(String tips) {
		this.tips = tips;
	}

}
