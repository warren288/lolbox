package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.AllHeroList;
import com.warren.lolbox.model.bean.FreeHeroList;
import com.warren.lolbox.model.bean.HeroSimple;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.widget.FragmentPagerAdapter;
import com.warren.lolbox.widget.TitleBar;
import com.warren.lolbox.widget.ViewPagerIndicator;

/**
 * 英雄列表
 * @author warren
 * @date 2015年1月1日
 */
public class HeroListActivity extends BaseActivity {

	private TitleBar mTb;
	private ViewPagerIndicator mVpIndicator;
	private ViewPager mVp;
	private FragmentPagerAdapter mAdapter;
	
	private List<HeroSimple> mLstHeroFree;
	private List<HeroSimple> mLstHeroLike;
	private List<HeroSimple> mLstHeroAll;
	
	private boolean[] mArrFinish = new boolean[3];
	
	private List<String> mLstTitles = Arrays.asList("免费", "收藏", "全部");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_herolist);
		
		initCtrl();
	}

	private void initCtrl() {
		
		mTb = (TitleBar) findViewById(R.id.titlebar);
		mTb.setRightVisibility(View.GONE);
		mVpIndicator = (ViewPagerIndicator) findViewById(R.id.vpindicator);
		mVp = (ViewPager) findViewById(R.id.vp);
		
		AppNetManager anm = AppContext.getApp().getNetManager();
		anm.get(URLUtil.getURL_HeroList("free"), new IListener<String>() {
			
			@Override
			public void onCall(String strJson) {
				AppContext.getApp().getJsonManager().parse(strJson, FreeHeroList.class, new IListener<FreeHeroList>() {

					@Override
					public void onCall(FreeHeroList t) {
						
						mArrFinish[0] = true;
						if (t != null) {
							mLstHeroFree = t.getFree();
						}
						checkStartInitFrags();
					}
				});
			}
		});
		
		mArrFinish[1] = true;
		
//		anm.get(URLUtil.getURL_HeroList("like"), new IListener<String>() {
//			
//			@Override
//			public void onCall(String strJson) {
//				AppContext.getApp().getJsonManager().parse(strJson, FreeHeroList.class, new IListener<FreeHeroList>() {
//
//					@Override
//					public void onCall(FreeHeroList t) {
//						
//						mArrFinish[1] = true;
//						
//						if (t == null) {
//							return;
//						}
//						mLstHeroLike = t.getFree();
//						checkStartInitFrags();
//					}
//				});
//			}
//		});
		
		anm.get(URLUtil.getURL_HeroList("all"), new IListener<String>() {
			
			@Override
			public void onCall(String strJson) {
				AppContext.getApp().getJsonManager().parse(strJson, AllHeroList.class, new IListener<AllHeroList>() {

					@Override
					public void onCall(AllHeroList t) {
						
						mArrFinish[2] = true;
						if (t != null) {
							mLstHeroAll = t.getAll();
						}
						checkStartInitFrags();
					}
				});
			}
		});
		
		mTb.setLeftClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	/**
	 * 检查三个List都获取完毕后，更新页面，加载Fragment
	 */
	private void checkStartInitFrags(){
		
		if(mArrFinish[0] && mArrFinish[1] && mArrFinish[2]){
			
			if(mLstHeroFree == null){
				mLstHeroFree = new ArrayList<HeroSimple>();
			}
			if(mLstHeroLike == null){
				mLstHeroLike = new ArrayList<HeroSimple>();
			}
			if(mLstHeroAll == null){
				mLstHeroAll = new ArrayList<HeroSimple>();
			}
			
			final BaseGridFragment[] frags = new BaseGridFragment[3];
			frags[0] = new BaseGridFragment();
			frags[0].setLstHero(mLstHeroFree);
			frags[1] = new BaseGridFragment();
			frags[1].setLstHero(mLstHeroLike);
			frags[2] = new BaseGridFragment();
			frags[2].setLstHero(mLstHeroAll);
			
			
			mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
				
				@Override
				public int getCount() {
					return 3;
				}
				
				@Override
				public Fragment getItem(int position) {
					return frags[position];
				}
			};
			
			mVp.setAdapter(mAdapter);
			mVpIndicator.setTabItemTitles(mLstTitles);
			mVpIndicator.setViewPager(mVp, 0);
			
		}
	}

	@Override
	protected boolean goBack() {
		return false;
	}
	
}
