package com.warren.lolbox;

import android.content.Intent;

import com.warren.lolbox.util.StringUtils;

/**
 * 基础功能界面管理
 * @author yangsheng
 * @date 2015年3月9日
 */
public class BaseKitManager {
	/**
	 * 打开英雄详情页面
	 * @param activity
	 * @param strHeroEnName
	 */
	public static void openHeroDetail(BaseActivity activity, String strHeroEnName) {

		Intent it = new Intent(activity, HeroDetailActivity.class);
		it.putExtra(HeroDetailActivity.EXTRA_HEROENNAME, strHeroEnName);
		activity.startActivity(it);
	}
	/**
	 * 打开物品详情页面
	 * @param activity
	 * @param strMaterialId
	 */
	public static void openMaterialDetail(BaseActivity activity, String strMaterialId) {

		Intent it = new Intent(activity, MaterialDetailActivity.class);
		it.putExtra(MaterialDetailActivity.EXTRA_MATERIALID, strMaterialId);
		activity.startActivity(it);
	}
	/**
	 * 打开比赛列表页面
	 * @param activity
	 * @param strUrl
	 */
	public static void openMatchList(BaseActivity activity, String strUrl) {

		Intent intent = new Intent(activity, CombatListActivity.class);
		intent.putExtra(CombatListActivity.EXTRA_URL, strUrl);
		activity.startActivity(intent);
	}

	/**
	 * 打开比赛详情页面
	 * @param activity
	 * @param strUrl
	 */
	public static void openMatchDetail(BaseActivity activity, String strUrl) {

		Intent intent = new Intent(activity, CombatDetailActivity.class);
		intent.putExtra(CombatDetailActivity.EXTRA_URL, strUrl);
		activity.startActivity(intent);
	}

	/**
	 * 打开指定URL相应的界面，用于webview中链接拦截
	 * @date 2015.3.9 目前支持 查看装备详情、查看英雄详情、查看比赛列表、查看比赛详情
	 * @param activity
	 * @param strUrl
	 * @return 如果URL是当前支持的集中类型之一，则返回true，否则返回false
	 */
	public static boolean openUrl(BaseActivity activity, String strUrl) {
		/*
		 * 比赛详情URL
		 * http://zdl.mbox.duowan.com/phone/matchDetailNew.php?lolboxAction=
		 * toMatchDetail
		 * &shareKey=aXmLpobX3EiLd719189mVm14eQ&sn=%E7%94%B5%E4%
		 * BF%A1%E4%B8%80
		 * &pn=%E8%BF%98%E5%9C%A8%E7%AD%89%E5%BE%85&v=0&sk=92628711
		 * T&queueType=RANKED"
		 */
		/*
		 * 查看全部比赛URL
		 * http://zdl.mbox.duowan.com/phone/matchListNew.php?sn=%E7%94%B5%E4%
		 * BF
		 * %A1%E4%B8%80&pn=%E8%BF%98%E5%9C%A8%E7%AD%89%E5%BE%85&v=213&hero=
		 * &timestamp=1425826517
		 */
		/**
		 * 查看召唤师指定英雄的最近比赛
		 * http://zdl.mbox.duowan.com/phone/matchListNew.php?lolboxAction=
		 * toMatchList
		 * &sn=%E7%94%B5%E4%BF%A1%E4%B8%80&pn=%E8%BF%98%E5%9C%A8%E7
		 * %AD%89%E5%BE%85&hero=LeeSin&timestamp=1425902414
		 */
		/**
		 * 查看英雄信息
		 * http://box.dwstatic.com/unsupport.php?lolboxAction=toHeroDetail&
		 * heroEnName=Tryndamere
		 */
		/**
		 * 查看装备信息
		 * http://box.dwstatic.com/unsupport.php?lolboxAction=toZBDetail&zbId=
		 * 3270
		 */

		if (StringUtils.isNullOrZero(strUrl)) {
			return false;
		}
		if (strUrl.contains("matchDetailNew.php")) {
			BaseKitManager.openMatchDetail(activity, strUrl);

		} else if (strUrl.contains("matchListNew.php")) {
			BaseKitManager.openMatchList(activity, strUrl);

		} else if (strUrl.contains("lolboxAction=toHeroDetail")) {
			String strHeroName = strUrl.substring(strUrl.lastIndexOf("=") + 1);
			BaseKitManager.openHeroDetail(activity, strHeroName);

		} else if (strUrl.contains("lolboxAction=toZBDetail")) {
			String strMaterialId = strUrl.substring(strUrl.lastIndexOf("=") + 1);
			BaseKitManager.openMaterialDetail(activity, strMaterialId);
		} else {
			return false;
		}
		return true;
	}

}