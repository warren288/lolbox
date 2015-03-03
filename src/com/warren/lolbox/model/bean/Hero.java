package com.warren.lolbox.model.bean;

import java.util.HashMap;
import java.util.List;

/**
 * 英雄详细信息Bean
 * @author warren
 * @date 2015年1月1日
 */
public class Hero {

	private String armorBase;
	private String armorLevel;
	private String attackBase;
	private String attackLevel;
	private String criticalChanceBase;
	private String criticalChanceLevel;
	private String danceVideoPath;
	private String description;
	private String displayName;
	private List<HashMap<String, String>> hate;
	private String healthBase;
	private String healthLevel;
	private String healthRegenBase;
	private String healthRegenLevel;
	private String iconPath;
	private String id;
	private List<HashMap<String, String>> like;
	private String magicResistBase;
	private String magicResistLevel;
	private String manaBase;
	private String manaLevel;
	private String manaRegenBase;
	private String manaRegenLevel;
	private String moveSpeed;
	private String name;
	private String opponentTips;
	private String portraitPath;
	private String price;
	private String quote;
	private String quoteAuthor;
	private String range;
	private String ratingAttack;
	private String ratingDefense;
	private String ratingDifficulty;
	private String ratingMagic;
	private String selectSoundPath;
	private String splashPath;
	private String tags;
	private HeroAbility b;
	private HeroAbility q;
	private HeroAbility w;
	private HeroAbility e;
	private HeroAbility r;
	private String tips;
	private String title;

	public Hero() {
	}

	/**
	 * @return the armorBase
	 */
	public String getArmorBase() {
		return armorBase;
	}

	/**
	 * @param armorBase the armorBase to set
	 */
	public void setArmorBase(String armorBase) {
		this.armorBase = armorBase;
	}

	/**
	 * @return the armorLevel
	 */
	public String getArmorLevel() {
		return armorLevel;
	}

	/**
	 * @param armorLevel the armorLevel to set
	 */
	public void setArmorLevel(String armorLevel) {
		this.armorLevel = armorLevel;
	}

	/**
	 * @return the attackBase
	 */
	public String getAttackBase() {
		return attackBase;
	}

	/**
	 * @param attackBase the attackBase to set
	 */
	public void setAttackBase(String attackBase) {
		this.attackBase = attackBase;
	}

	/**
	 * @return the attackLevel
	 */
	public String getAttackLevel() {
		return attackLevel;
	}

	/**
	 * @param attackLevel the attackLevel to set
	 */
	public void setAttackLevel(String attackLevel) {
		this.attackLevel = attackLevel;
	}

	/**
	 * @return the criticalChanceBase
	 */
	public String getCriticalChanceBase() {
		return criticalChanceBase;
	}

	/**
	 * @param criticalChanceBase the criticalChanceBase to set
	 */
	public void setCriticalChanceBase(String criticalChanceBase) {
		this.criticalChanceBase = criticalChanceBase;
	}

	/**
	 * @return the criticalChanceLevel
	 */
	public String getCriticalChanceLevel() {
		return criticalChanceLevel;
	}

	/**
	 * @param criticalChanceLevel the criticalChanceLevel to set
	 */
	public void setCriticalChanceLevel(String criticalChanceLevel) {
		this.criticalChanceLevel = criticalChanceLevel;
	}

	/**
	 * @return the danceVideoPath
	 */
	public String getDanceVideoPath() {
		return danceVideoPath;
	}

	/**
	 * @param danceVideoPath the danceVideoPath to set
	 */
	public void setDanceVideoPath(String danceVideoPath) {
		this.danceVideoPath = danceVideoPath;
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the hate
	 */
	public List<HashMap<String, String>> getHate() {
		return hate;
	}

	/**
	 * @param hate the hate to set
	 */
	public void setHate(List<HashMap<String, String>> hate) {
		this.hate = hate;
	}

	/**
	 * @return the healthBase
	 */
	public String getHealthBase() {
		return healthBase;
	}

	/**
	 * @param healthBase the healthBase to set
	 */
	public void setHealthBase(String healthBase) {
		this.healthBase = healthBase;
	}

	/**
	 * @return the healthLevel
	 */
	public String getHealthLevel() {
		return healthLevel;
	}

	/**
	 * @param healthLevel the healthLevel to set
	 */
	public void setHealthLevel(String healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * @return the healthRegenBase
	 */
	public String getHealthRegenBase() {
		return healthRegenBase;
	}

	/**
	 * @param healthRegenBase the healthRegenBase to set
	 */
	public void setHealthRegenBase(String healthRegenBase) {
		this.healthRegenBase = healthRegenBase;
	}

	/**
	 * @return the healthRegenLevel
	 */
	public String getHealthRegenLevel() {
		return healthRegenLevel;
	}

	/**
	 * @param healthRegenLevel the healthRegenLevel to set
	 */
	public void setHealthRegenLevel(String healthRegenLevel) {
		this.healthRegenLevel = healthRegenLevel;
	}

	/**
	 * @return the iconPath
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * @param iconPath the iconPath to set
	 */
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
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
	 * @return the like
	 */
	public List<HashMap<String, String>> getLike() {
		return like;
	}

	/**
	 * @param like the like to set
	 */
	public void setLike(List<HashMap<String, String>> like) {
		this.like = like;
	}

	/**
	 * @return the magicResistBase
	 */
	public String getMagicResistBase() {
		return magicResistBase;
	}

	/**
	 * @param magicResistBase the magicResistBase to set
	 */
	public void setMagicResistBase(String magicResistBase) {
		this.magicResistBase = magicResistBase;
	}

	/**
	 * @return the magicResistLevel
	 */
	public String getMagicResistLevel() {
		return magicResistLevel;
	}

	/**
	 * @param magicResistLevel the magicResistLevel to set
	 */
	public void setMagicResistLevel(String magicResistLevel) {
		this.magicResistLevel = magicResistLevel;
	}

	/**
	 * @return the manaBase
	 */
	public String getManaBase() {
		return manaBase;
	}

	/**
	 * @param manaBase the manaBase to set
	 */
	public void setManaBase(String manaBase) {
		this.manaBase = manaBase;
	}

	/**
	 * @return the manaLevel
	 */
	public String getManaLevel() {
		return manaLevel;
	}

	/**
	 * @param manaLevel the manaLevel to set
	 */
	public void setManaLevel(String manaLevel) {
		this.manaLevel = manaLevel;
	}

	/**
	 * @return the manaRegenBase
	 */
	public String getManaRegenBase() {
		return manaRegenBase;
	}

	/**
	 * @param manaRegenBase the manaRegenBase to set
	 */
	public void setManaRegenBase(String manaRegenBase) {
		this.manaRegenBase = manaRegenBase;
	}

	/**
	 * @return the manaRegenLevel
	 */
	public String getManaRegenLevel() {
		return manaRegenLevel;
	}

	/**
	 * @param manaRegenLevel the manaRegenLevel to set
	 */
	public void setManaRegenLevel(String manaRegenLevel) {
		this.manaRegenLevel = manaRegenLevel;
	}

	/**
	 * @return the moveSpeed
	 */
	public String getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * @param moveSpeed the moveSpeed to set
	 */
	public void setMoveSpeed(String moveSpeed) {
		this.moveSpeed = moveSpeed;
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
	 * @return the opponentTips
	 */
	public String getOpponentTips() {
		return opponentTips;
	}

	/**
	 * @param opponentTips the opponentTips to set
	 */
	public void setOpponentTips(String opponentTips) {
		this.opponentTips = opponentTips;
	}

	/**
	 * @return the portraitPath
	 */
	public String getPortraitPath() {
		return portraitPath;
	}

	/**
	 * @param portraitPath the portraitPath to set
	 */
	public void setPortraitPath(String portraitPath) {
		this.portraitPath = portraitPath;
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
	 * @return the quote
	 */
	public String getQuote() {
		return quote;
	}

	/**
	 * @param quote the quote to set
	 */
	public void setQuote(String quote) {
		this.quote = quote;
	}

	/**
	 * @return the quoteAuthor
	 */
	public String getQuoteAuthor() {
		return quoteAuthor;
	}

	/**
	 * @param quoteAuthor the quoteAuthor to set
	 */
	public void setQuoteAuthor(String quoteAuthor) {
		this.quoteAuthor = quoteAuthor;
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

	/**
	 * @return the ratingAttack
	 */
	public String getRatingAttack() {
		return ratingAttack;
	}

	/**
	 * @param ratingAttack the ratingAttack to set
	 */
	public void setRatingAttack(String ratingAttack) {
		this.ratingAttack = ratingAttack;
	}

	/**
	 * @return the ratingDefense
	 */
	public String getRatingDefense() {
		return ratingDefense;
	}

	/**
	 * @param ratingDefense the ratingDefense to set
	 */
	public void setRatingDefense(String ratingDefense) {
		this.ratingDefense = ratingDefense;
	}

	/**
	 * @return the ratingDifficulty
	 */
	public String getRatingDifficulty() {
		return ratingDifficulty;
	}

	/**
	 * @param ratingDifficulty the ratingDifficulty to set
	 */
	public void setRatingDifficulty(String ratingDifficulty) {
		this.ratingDifficulty = ratingDifficulty;
	}

	/**
	 * @return the ratingMagic
	 */
	public String getRatingMagic() {
		return ratingMagic;
	}

	/**
	 * @param ratingMagic the ratingMagic to set
	 */
	public void setRatingMagic(String ratingMagic) {
		this.ratingMagic = ratingMagic;
	}

	/**
	 * @return the selectSoundPath
	 */
	public String getSelectSoundPath() {
		return selectSoundPath;
	}

	/**
	 * @param selectSoundPath the selectSoundPath to set
	 */
	public void setSelectSoundPath(String selectSoundPath) {
		this.selectSoundPath = selectSoundPath;
	}

	/**
	 * @return the splashPath
	 */
	public String getSplashPath() {
		return splashPath;
	}

	/**
	 * @param splashPath the splashPath to set
	 */
	public void setSplashPath(String splashPath) {
		this.splashPath = splashPath;
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
	 * @return the b
	 */
	public HeroAbility getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(HeroAbility b) {
		this.b = b;
	}

	/**
	 * @return the q
	 */
	public HeroAbility getQ() {
		return q;
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(HeroAbility q) {
		this.q = q;
	}

	/**
	 * @return the w
	 */
	public HeroAbility getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(HeroAbility w) {
		this.w = w;
	}

	/**
	 * @return the e
	 */
	public HeroAbility getE() {
		return e;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(HeroAbility e) {
		this.e = e;
	}

	/**
	 * @return the r
	 */
	public HeroAbility getR() {
		return r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(HeroAbility r) {
		this.r = r;
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
		return "Hero [armorBase=" + armorBase + ", armorLevel=" + armorLevel + ", attackBase="
					+ attackBase + ", attackLevel=" + attackLevel + ", criticalChanceBase="
					+ criticalChanceBase + ", criticalChanceLevel=" + criticalChanceLevel
					+ ", danceVideoPath=" + danceVideoPath + ", description=" + description
					+ ", displayName=" + displayName + ", hate=" + hate + ", healthBase="
					+ healthBase + ", healthLevel=" + healthLevel + ", healthRegenBase="
					+ healthRegenBase + ", healthRegenLevel=" + healthRegenLevel + ", iconPath="
					+ iconPath + ", id=" + id + ", like=" + like + ", magicResistBase="
					+ magicResistBase + ", magicResistLevel=" + magicResistLevel + ", manaBase="
					+ manaBase + ", manaLevel=" + manaLevel + ", manaRegenBase=" + manaRegenBase
					+ ", manaRegenLevel=" + manaRegenLevel + ", moveSpeed=" + moveSpeed + ", name="
					+ name + ", opponentTips=" + opponentTips + ", portraitPath=" + portraitPath
					+ ", price=" + price + ", quote=" + quote + ", quoteAuthor=" + quoteAuthor
					+ ", range=" + range + ", ratingAttack=" + ratingAttack + ", ratingDefense="
					+ ratingDefense + ", ratingDifficulty=" + ratingDifficulty + ", ratingMagic="
					+ ratingMagic + ", selectSoundPath=" + selectSoundPath + ", splashPath="
					+ splashPath + ", tags=" + tags + ", B=" + b + ", Q=" + q + ", W=" + w + ", E="
					+ e + ", R=" + r + ", tips=" + tips + ", title=" + title + "]";
	}
}
