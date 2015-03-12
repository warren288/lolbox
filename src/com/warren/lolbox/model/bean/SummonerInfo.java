package com.warren.lolbox.model.bean;

import java.util.Map;

public class SummonerInfo {
	private String sn;
	private String pn;
	private String level;
	private String icon;
	private int zdl;
	private int tier;
	private int rank;
	private int league_points;
	private String tierDesc;
	private String snFullName;
	public SummonerInfo() {
		super();
	}
	public SummonerInfo(Map<String, Object> map){
		sn = (String) map.get("sn");
		pn = (String) map.get("pn");
		level = (String) map.get("level");
		icon = (String) map.get("icon");
		zdl = (Integer) map.get("zdl");
		tier = (Integer) map.get("tier");
		rank = (Integer) map.get("rank");
		league_points = (Integer) map.get("league_points");
		tierDesc = (String) map.get("tierDesc");
		snFullName = (String) map.get("snFullName");
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the pn
	 */
	public String getPn() {
		return pn;
	}
	/**
	 * @param pn the pn to set
	 */
	public void setPn(String pn) {
		this.pn = pn;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the zdl
	 */
	public int getZdl() {
		return zdl;
	}
	/**
	 * @param zdl the zdl to set
	 */
	public void setZdl(int zdl) {
		this.zdl = zdl;
	}
	/**
	 * @return the tier
	 */
	public int getTier() {
		return tier;
	}
	/**
	 * @param tier the tier to set
	 */
	public void setTier(int tier) {
		this.tier = tier;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * @return the league_points
	 */
	public int getLeague_points() {
		return league_points;
	}
	/**
	 * @param league_points the league_points to set
	 */
	public void setLeague_points(int league_points) {
		this.league_points = league_points;
	}
	/**
	 * @return the tierDesc
	 */
	public String getTierDesc() {
		return tierDesc;
	}
	/**
	 * @param tierDesc the tierDesc to set
	 */
	public void setTierDesc(String tierDesc) {
		this.tierDesc = tierDesc;
	}
	/**
	 * @return the snFullName
	 */
	public String getSnFullName() {
		return snFullName;
	}
	/**
	 * @param snFullName the snFullName to set
	 */
	public void setSnFullName(String snFullName) {
		this.snFullName = snFullName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SummonerInfo [sn=" + sn + ", pn=" + pn + ", level=" + level + ", icon=" + icon
					+ ", zdl=" + zdl + ", tier=" + tier + ", rank=" + rank + ", league_points="
					+ league_points + ", tierDesc=" + tierDesc + ", snFullName=" + snFullName + "]";
	}
	
}
