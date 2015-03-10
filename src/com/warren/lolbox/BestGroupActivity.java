package com.warren.lolbox;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.BestGroup;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.LogTool;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.TitleBar;

/**
 * 最佳阵容列表Activity
 * @author warren
 * @date 2015年1月3日
 */
public class BestGroupActivity extends BaseActivity {

	private TitleBar mTb;
	private ListView mLv;
	private List<BestGroup> mLstBg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bestgrouplist);
		initCtrl();
		requestData();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);
		mTb.setRightVisibility(View.GONE);

		mLv = (ListView) findViewById(R.id.lv_groups);

		mTb.setLeftClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (mLstBg == null || mLstBg.size() < position) {
					LogTool.w("BestGroupActivity", "mLstBg == null || mLstBg.size() < position");
					return;
				}

				Intent it = new Intent(BestGroupActivity.this, BestGroupDetailActivity.class);
				it.putExtra(BestGroupDetailActivity.EXTRA_BESTGROUP, mLstBg.get(position));
				startActivity(it);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
		});
	}

	/**
	 * 请求数据，请求完毕后更新ListView
	 */
	private void requestData() {

		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_BestGroup(), new IListener<String>() {

						@Override
						public void onCall(String strJson) {

							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(BestGroupActivity.this, "请求服务器数据失败",
											Toast.LENGTH_SHORT).show();
								return;
							}
							AppContext.getApp()
										.getJsonManager()
										.parseList(strJson, BestGroup.class,
													new IListener<List<BestGroup>>() {

														@Override
														public void onCall(List<BestGroup> t) {

															if (t == null || t.size() == 0) {
																Toast.makeText(
																			BestGroupActivity.this,
																			"请求服务器数据失败",
																			Toast.LENGTH_SHORT)
																			.show();
																return;
															}

															mLstBg = t;
															AdapterGroups adapter = new AdapterGroups(
																		getLayoutInflater(), mLstBg);
															mLv.setAdapter(adapter);
														}
													});
						}
					});
	}

	/**
	 * 阵容列表Adapter
	 * @author warren
	 * @date 2015年1月3日
	 */
	class AdapterGroups extends BaseAdapter {

		private LayoutInflater mInflater;
		private List<BestGroup> mLstData;
		private ImageLoader mImgLoader;

		public AdapterGroups(LayoutInflater mInflater, List<BestGroup> mLstData) {
			this.mInflater = mInflater;
			this.mLstData = mLstData;
			this.mImgLoader = AppContext.getApp().getImgLoader();
		}

		@Override
		public int getCount() {
			return mLstData == null ? 0 : mLstData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.activity_bestgroup_listitem, parent, false);
				holder = new ViewHolder();
				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
				holder.img1 = (ImageView) convertView.findViewById(R.id.img_1);
				holder.img2 = (ImageView) convertView.findViewById(R.id.img_2);
				holder.img3 = (ImageView) convertView.findViewById(R.id.img_3);
				holder.img4 = (ImageView) convertView.findViewById(R.id.img_4);
				holder.img5 = (ImageView) convertView.findViewById(R.id.img_5);
				holder.tvDes = (TextView) convertView.findViewById(R.id.tv_des);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder)convertView.getTag();
			}
			BestGroup bg = mLstData.get(position);
			holder.tvTitle.setText(bg.getTitle());
			holder.tvDes.setText(bg.getDes());
			
			mImgLoader.displayImage(URLUtil.getURL_HeroImg(bg.getHero1(), EnumDPI.DPI120x120), holder.img1);
			mImgLoader.displayImage(URLUtil.getURL_HeroImg(bg.getHero2(), EnumDPI.DPI120x120), holder.img2);
			mImgLoader.displayImage(URLUtil.getURL_HeroImg(bg.getHero3(), EnumDPI.DPI120x120), holder.img3);
			mImgLoader.displayImage(URLUtil.getURL_HeroImg(bg.getHero4(), EnumDPI.DPI120x120), holder.img4);
			mImgLoader.displayImage(URLUtil.getURL_HeroImg(bg.getHero5(), EnumDPI.DPI120x120), holder.img5);
			
			return convertView;
		}

		class ViewHolder {

			public TextView tvTitle;
			public ImageView img1;
			public ImageView img2;
			public ImageView img3;
			public ImageView img4;
			public ImageView img5;
			public TextView tvDes;

		}

	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
