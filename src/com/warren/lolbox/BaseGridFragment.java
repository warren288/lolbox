package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.warren.lolbox.model.BaseContentFragment;
import com.warren.lolbox.model.SimpleNetTool;
import com.warren.lolbox.model.bean.HeroSimple;
import com.warren.lolbox.model.bean.MaterialSimple;
import com.warren.lolbox.model.bean.SummonerAbility;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;

/**
 * 基本的GridView Fragment，支持 “英雄/物品/技能” 列表
 * @author warren
 * @date 2015年1月1日
 */
public class BaseGridFragment extends BaseContentFragment {

	public static final String FRAGMENTNAME = "BaseGridFragment";
	public static final String EXTRA_JSON = "EXTRA_JSON";
	public static final String EXTRA_TYPE = "EXTRA_TYPE";
	public static final int DATATYPE_HERO = 1;
	public static final int DATATYPE_MATERIAL = 2;

	private View mVRoot;

	private GridView mGv;
	private BaseGridAdapter mAdapter;

	private List<HeroSimple> mLstHero;
	private List<MaterialSimple> mLstMaterial;
	private List<SummonerAbility> mLstSumAbility;

	private DisplayImageOptions mDisPlayOption;

	/**
	 * 0/1/2 : 英雄/物品/技能
	 */
	private int mType;

	private List<SimpleNetTool> mLstSnt = new ArrayList<SimpleNetTool>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mVRoot = inflater.inflate(R.layout.fragment_gridonly, container, false);

		initCtrl();

		return mVRoot;
	}

	@Override
	public void onResume() {
		super.onResume();
		mAdapter.notifyDataSetChanged();
	}

	private void initCtrl() {

		mGv = (GridView) mVRoot.findViewById(R.id.gv);

		mGv.setNumColumns(4);

		mAdapter = new BaseGridAdapter(LayoutInflater.from(mVRoot.getContext()));
		mGv.setAdapter(mAdapter);

		mAdapter.setLstNetTools(mLstSnt, AppContext.getApp().getImgLoader(), mDisPlayOption);
		mAdapter.setNumColumn(4);
		mAdapter.notifyDataSetChanged();

		mGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (mType == 0) {
					BaseKitManager.openHeroDetail((BaseActivity) getActivity(),
								mLstHero.get(position).getEnName());
				} else if (mType == 1) {
					BaseKitManager.openMaterialDetail((BaseActivity) getActivity(), ""
								+ mLstMaterial.get(position).getId());
				} else if (mType == 2) {
					BaseKitManager.openSummonerSkillDetail((BaseActivity) getActivity(),
								mLstSumAbility.get(position));
				}
			}
		});
	}

	public void setLstHero(List<HeroSimple> lstHero) {
		this.mLstHero = lstHero;
		this.mType = 0;

		mLstSnt.clear();
		for (HeroSimple hero : this.mLstHero) {
			SimpleNetTool snt = new SimpleNetTool(URLUtil.getURL_HeroImg(hero.getEnName(),
						EnumDPI.DPI120x120), hero.getCnName());
			mLstSnt.add(snt);
		}

	}

	public void setLstMaterial(List<MaterialSimple> lstMaterial) {

		this.mLstMaterial = lstMaterial;
		this.mType = 1;
		mLstSnt.clear();
		for (MaterialSimple material : this.mLstMaterial) {
			SimpleNetTool snt = new SimpleNetTool(URLUtil.getURL_ZBImg(material.getId(),
						EnumDPI.DPI64x64), material.getText());
			mLstSnt.add(snt);
		}
	}

	public void setLstSumAbility(List<SummonerAbility> lstSumAbility) {

		this.mLstSumAbility = lstSumAbility;
		this.mType = 2;
		mLstSnt.clear();
		for (SummonerAbility material : this.mLstSumAbility) {
			SimpleNetTool snt = new SimpleNetTool(
						URLUtil.getURL_SummonerSkillImg(material.getId()), material.getName());
			mLstSnt.add(snt);
		}

		Options opt = new Options();
		opt.inInputShareable = true;
		opt.inPurgeable = true;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inSampleSize = 1;

		mDisPlayOption = new DisplayImageOptions.Builder()
					.considerExifParams(true)
					.bitmapConfig(Config.RGB_565)
					.decodingOptions(opt)
					.displayer(new BitmapDisplayer() {

						@Override
						public void display(Bitmap arg0, ImageAware arg1, LoadedFrom arg2) {

							// 由于请求的图片较小，在分辨率较大的设备上显示不美观。这里在显示前先放大到两倍，再显示。
							// 放大到两倍后，经检查大部分手机分辨率都能较好地展示，而放大后的图片对某一分辨率来说太大时，
							// 可通过设置ImageView的ScaleType来将自动将图片缩小。

							Matrix matrix = new Matrix();
							matrix.postScale(2, 2);
							Bitmap bitResize = Bitmap.createBitmap(arg0, 0, 0, arg0.getWidth(),
										arg0.getHeight(), matrix, true);
							arg1.setImageBitmap(bitResize);
						}
					}).cacheInMemory(true).showImageOnLoading(R.drawable.dl_loading_img)
					.imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
	}

	@Override
	public String getName() {
		return FRAGMENTNAME;
	}

	@Override
	public View getRootView() {
		return null;
	}

}
