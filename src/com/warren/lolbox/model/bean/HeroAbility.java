package com.warren.lolbox.model.bean;

/**
 * 英雄技能Bean
 * @author warren
 * @date 2015年1月1日
 */
public class HeroAbility {
	private String cooldown;
	private String cost;
	private String description;
	private String effect;
	private String id;
	private String name;
	private String range;
	
	public HeroAbility() {
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
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
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
	 * @return the effect
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
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
	 * @return the range
	 */
	public String getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(String range) {
		this.range = range;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeroAbility [cooldown=" + cooldown + ", cost=" + cost + ", description="
					+ description + ", effect=" + effect + ", id=" + id + ", name=" + name
					+ ", range=" + range + "]";
	}
}
