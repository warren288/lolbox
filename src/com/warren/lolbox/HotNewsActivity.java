package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.HotNewsBlock;
import com.warren.lolbox.model.bean.HotNewsBrief;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

/**
 * 新闻推荐
 * @author warren
 * @date 2015年1月6日
 */
public class HotNewsActivity extends BaseActivity {

	private List<HotNewsBlock> mNewsBlock = new ArrayList<HotNewsBlock>();

	private AdapterNews mAdapter;
	private ListView mLvHotNews;
	private PullToRefreshListView mPtrLv;

	private int mCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_hotnews);
		mPtrLv = (PullToRefreshListView) findViewById(R.id.ptrlv_hotnews);
		mPtrLv.setMode(Mode.BOTH);
		mLvHotNews = mPtrLv.getRefreshableView();
		mLvHotNews.setDivider(null);
		mLvHotNews.setDividerHeight(0);

		mAdapter = new AdapterNews();

		mLvHotNews.setAdapter(mAdapter);

		mPtrLv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				if (mPtrLv.isHeaderShown()) {
					requestMore();
				} else if (mPtrLv.isFooterShown()) {
					requestData();
				}
			}
		});
		requestData();
	}

	/**
	 * 请求第一页数据
	 */
	private void requestData() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7212");
		// map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");

		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_HotNews(1), map, new IListener<String>() {

						@Override
						public void onCall(String strJson) {
							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(HotNewsActivity.this, "请求数据失败", Toast.LENGTH_SHORT)
											.show();
							}
							AppContext.getApp()
										.getJsonManager()
										.parseList(strJson, HotNewsBlock.class,
													new IListener<List<HotNewsBlock>>() {

														@Override
														public void onCall(List<HotNewsBlock> t) {
															if (t == null || t.size() == 0) {
																Toast.makeText(
																			HotNewsActivity.this,
																			"转换数据失败",
																			Toast.LENGTH_SHORT)
																			.show();
															}

															mNewsBlock.clear();
															mNewsBlock.addAll(t);
															mAdapter.notifyDataSetChanged();
															mCount = 1;
															mPtrLv.onRefreshComplete();
															mLvHotNews.setSelection(mNewsBlock
																		.size());
														}
													});
						}
					});
	}

	/**
	 * 请求更多页数据，请求页基于当前已显示的页数
	 */
	private void requestMore() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7206");
		// map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");

		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_HotNews(mCount + 1), map, new IListener<String>() {

						@Override
						public void onCall(String strJson) {

							AppContext.getApp()
										.getJsonManager()
										.parseList(strJson, HotNewsBlock.class,
													new IListener<List<HotNewsBlock>>() {

														@Override
														public void onCall(List<HotNewsBlock> t) {

															int nBefore = mNewsBlock.size();
															mNewsBlock.addAll(t);
															mAdapter.notifyDataSetChanged();
															mCount += 1;
															mPtrLv.onRefreshComplete();
															mLvHotNews.setSelection(nBefore);
														}

													});
						}
					});
	}

	private void openNewsDetail(String strNewsId) {
		Intent it = new Intent(this, NewsDetailActivity.class);
		it.putExtra(NewsDetailActivity.EXTRA_NEWSID, strNewsId);
		startActivity(it);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	/**
	 * 新闻推荐列表
	 * @author warren
	 * @date 2015年1月6日
	 */
	class AdapterNews extends BaseAdapter {

		@Override
		public int getCount() {
			return mNewsBlock.size();
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

			ViewHolder holder;
			if (convertView == null) {

				convertView = getLayoutInflater().inflate(R.layout.card_item, parent, false);
				holder = new ViewHolder();

				holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
				holder.rlMain = (RelativeLayout) convertView.findViewById(R.id.rl_main);
				holder.imgMain = (ImageView) convertView.findViewById(R.id.img_main);
				holder.tvMain = (TextView) convertView.findViewById(R.id.tv_main);
				holder.rl1 = (RelativeLayout) convertView.findViewById(R.id.rl_1);
				holder.img1 = (ImageView) convertView.findViewById(R.id.img_1);
				holder.tv1 = (TextView) convertView.findViewById(R.id.tv_1);
				holder.rl2 = (RelativeLayout) convertView.findViewById(R.id.rl_2);
				holder.img2 = (ImageView) convertView.findViewById(R.id.img_2);
				holder.tv2 = (TextView) convertView.findViewById(R.id.tv_2);
				holder.rl3 = (RelativeLayout) convertView.findViewById(R.id.rl_3);
				holder.img3 = (ImageView) convertView.findViewById(R.id.img_3);
				holder.tv3 = (TextView) convertView.findViewById(R.id.tv_3);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			HotNewsBlock block = mNewsBlock.get(mNewsBlock.size() - 1 - position);
			final List<HotNewsBrief> lstNewsBrief = block.getContent();

			holder.tvTime.setText(new Date(1000 * Long.parseLong(block.getCreateTime()))
						.toLocaleString());
			holder.tvMain.setText(lstNewsBrief.get(0).getTitle());
			AppContext.getApp().getImgLoader()
						.displayImage(lstNewsBrief.get(0).getPic(), holder.imgMain);

			holder.tv1.setText(lstNewsBrief.get(1).getTitle());
			AppContext.getApp().getImgLoader()
						.displayImage(lstNewsBrief.get(1).getPic(), holder.img1);

			if (lstNewsBrief.size() < 3) {
				holder.rl2.setVisibility(View.GONE);
			} else {
				holder.tv2.setText(lstNewsBrief.get(2).getTitle());
				AppContext.getApp().getImgLoader()
							.displayImage(lstNewsBrief.get(2).getPic(), holder.img2);
			}
			if (lstNewsBrief.size() < 4) {
				holder.rl3.setVisibility(View.GONE);
			} else {
				holder.tv3.setText(lstNewsBrief.get(3).getTitle());
				AppContext.getApp().getImgLoader()
							.displayImage(lstNewsBrief.get(3).getPic(), holder.img3);
			}
			holder.rlMain.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String strNewsId = lstNewsBrief.get(0).getNewsId();
					openNewsDetail(strNewsId);
				}
			});
			holder.rl1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String strNewsId = lstNewsBrief.get(1).getNewsId();
					openNewsDetail(strNewsId);
				}
			});
			holder.rl2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String strNewsId = lstNewsBrief.get(2).getNewsId();
					openNewsDetail(strNewsId);
				}
			});
			holder.rl3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String strNewsId = lstNewsBrief.get(3).getNewsId();
					openNewsDetail(strNewsId);
				}
			});

			return convertView;
		}

		class ViewHolder {
			TextView tvTime;
			RelativeLayout rlMain;
			ImageView imgMain;
			TextView tvMain;
			RelativeLayout rl1;
			ImageView img1;
			TextView tv1;
			RelativeLayout rl2;
			ImageView img2;
			TextView tv2;
			RelativeLayout rl3;
			ImageView img3;
			TextView tv3;
		}

	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
