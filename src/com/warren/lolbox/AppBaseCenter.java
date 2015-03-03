package com.warren.lolbox;

import android.app.Activity;
import android.content.Intent;

/**
 * 基础的程序控制中心
 * @author warren
 * @date 2015年1月3日
 */
public class AppBaseCenter {
	
	public static AppBaseCenter abc = null;
	public static AppBaseCenter getInstance(){
		if(abc == null){
			synchronized (AppBaseCenter.class) {
				if(abc == null){
					abc = new AppBaseCenter();
				}
			}
		}
		return abc;
	}
	
	public AppBaseCenter(){
		
	}
	/**
	 * 打开物品详情界面
	 * @param activity
	 * @param strMaterialId
	 */
	public void openMaterialDetail(Activity activity, String strMaterialId){

		Intent it = new Intent(activity, MaterialDetailActivity.class);
		it.putExtra(MaterialDetailActivity.EXTRA_MATERIALID, strMaterialId);
		activity.startActivity(it);
		activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
	/**
	 * 打开英雄详情界面
	 * @param activity
	 * @param strHeroEnName
	 */
	public void openHeroDetail(Activity activity, String strHeroEnName){
		
		Intent it = new Intent(activity, HeroDetailActivity.class);
		it.putExtra(HeroDetailActivity.EXTRA_HEROENNAME, strHeroEnName);
		activity.startActivity(it);
		activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
}
