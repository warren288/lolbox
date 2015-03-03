package com.warren.lolbox.url;

public class DuowanConfig {

	/**
	 * 装备分类
	 * @author warren
	 * @date 2014年12月30日
	 */
	public enum EnumZBType {

		new_, // 新增装备
		Tools, // 工具装备
		GoldPer, // 工资装备
		consumable, // 消耗品
		Vision, // 视野&饰品装备
		fumo, // 附魔装备
		movement, // 移动速度
		mana_regen, // 法力回复
		mana, // 法力值
		health_regen, // 生命回复
		health, // 生命值
		critical_strike, // 暴击伤害
		spell_damage, // 法术伤害
		armor, // 防御
		spell_block, // 魔抗
		damage, // 物理伤害
		cooldown_reduction, // 冷却缩减
		life_steal, // 吸血
		other, // 其他
		all; // 所有

		@Override
		public String toString() {
			switch (this) {
			case new_:
				return "new";

			default:
				return super.toString();
			}

		};

		public static final String[] getStringTypeArray() {
			return new String[] { "新增装备", "工具装备", "工资装备", "消耗品", "视野&饰品装备", "附魔装备", "移动速度", "法力回复",
					"法力值", "生命回复", "生命值", "暴击伤害", "法术伤害", "防御", "魔抗", "物理伤害", "冷却缩减", "吸血", "其他",
					"所有" };
		}
		
		public static final EnumZBType getZbType(int index){
			
			return EnumZBType.values()[index];
		}
	}

	/**
	 * 图片分辨率
	 * @author warren
	 * @date 2014年12月30日
	 */
	public enum EnumDPI {

		DPI120x120, DPI64x64, DPI40x40;

		public String toDPIString() {
			switch (this) {
			case DPI40x40:
				return "40x40";
			case DPI64x64:
				return "64x64";
			case DPI120x120:
				return "120x120";
			default:
				return null;
			}
		}
	}
	
	/**
	 * 英雄技能
	 * @author warren
	 * @date 2014年12月31日
	 */
	public enum EnumAbility{
		
		B, Q, W, E, R;
	}
	
	
}
