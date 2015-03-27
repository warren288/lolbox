package com.warren.lolbox.model.bean;

/**
 * 符文
 * @author yangsheng
 * @date 2015年3月27日
 */
public class Rune {
	private String name;
	private String alias;
	private String lev1;
	private String lev2;
	private String lev3;
	private String iplev1;
	private String iplev2;
	private String iplev3;
	private String prop;
	private int type;
	private int recom;
	private String img;
	private String units;
	private String standby;

	public Rune() {
		super();
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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the lev1
	 */
	public String getLev1() {
		return lev1;
	}

	/**
	 * @param lev1 the lev1 to set
	 */
	public void setLev1(String lev1) {
		this.lev1 = lev1;
	}

	/**
	 * @return the lev2
	 */
	public String getLev2() {
		return lev2;
	}

	/**
	 * @param lev2 the lev2 to set
	 */
	public void setLev2(String lev2) {
		this.lev2 = lev2;
	}

	/**
	 * @return the lev3
	 */
	public String getLev3() {
		return lev3;
	}

	/**
	 * @param lev3 the lev3 to set
	 */
	public void setLev3(String lev3) {
		this.lev3 = lev3;
	}

	/**
	 * @return the iplev1
	 */
	public String getIplev1() {
		return iplev1;
	}

	/**
	 * @param iplev1 the iplev1 to set
	 */
	public void setIplev1(String iplev1) {
		this.iplev1 = iplev1;
	}

	/**
	 * @return the iplev2
	 */
	public String getIplev2() {
		return iplev2;
	}

	/**
	 * @param iplev2 the iplev2 to set
	 */
	public void setIplev2(String iplev2) {
		this.iplev2 = iplev2;
	}

	/**
	 * @return the iplev3
	 */
	public String getIplev3() {
		return iplev3;
	}

	/**
	 * @param iplev3 the iplev3 to set
	 */
	public void setIplev3(String iplev3) {
		this.iplev3 = iplev3;
	}

	/**
	 * @return the prop
	 */
	public String getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(String prop) {
		this.prop = prop;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the recom
	 */
	public int getRecom() {
		return recom;
	}

	/**
	 * @param recom the recom to set
	 */
	public void setRecom(int recom) {
		this.recom = recom;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * @return the standby
	 */
	public String getStandby() {
		return standby;
	}

	/**
	 * @param standby the standby to set
	 */
	public void setStandby(String standby) {
		this.standby = standby;
	}

	/**
	 * 取收益
	 * @param level
	 * @return
	 */
	public String obtainBenefit(int level) {
		if (level == 1) {
			return lev1;
		} else if (level == 2) {
			return lev2;
		} else {
			return lev3;
		}
	}
	
	/**
	 * 取花费
	 * @param level
	 * @return
	 */
	public String obtainExpense(int level){
		if (level == 1) {
			return iplev1;
		} else if (level == 2) {
			return iplev2;
		} else {
			return iplev3;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rune [name=" + name + ", alias=" + alias + ", lev1=" + lev1 + ", lev2=" + lev2
					+ ", lev3=" + lev3 + ", iplev1=" + iplev1 + ", iplev2=" + iplev2 + ", iplev3="
					+ iplev3 + ", prop=" + prop + ", type=" + type + ", recom=" + recom + ", img="
					+ img + ", units=" + units + ", standby=" + standby + "]";
	}
}
