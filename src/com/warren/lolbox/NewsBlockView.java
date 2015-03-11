package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsBlock;
import com.warren.lolbox.model.bean.NewsInfo;
import com.warren.lolbox.model.bean.NewsTitle;
import com.warren.lolbox.model.bean.PictureBlock;
import com.warren.lolbox.model.bean.PictureInfo;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

/**
 * 资讯页面单页
 * @author yangsheng
 * @date 2015年3月7日
 */
public class NewsBlockView {

	public static String TITLETYPE_NEWSWITHHEAD = "newsWithHeader";
	public static String TITLETYPE_NEWSWITHOUTHEAD = "news";
	public static String TITLETYPE_ALBUM = "album";

	private Context mContext;
	private NewsTitle mTitle;

	private ViewGroup mRootView;
	private PullToRefreshListView mPtrlv;
	private ListView mLv;
	private ViewPager mVpHead;

	private NewsBlock mNewsBlock;
	private PictureBlock mPicBlock;

	private List<NewsInfo> mLstInfo;
	private List<NewsInfo> mLstHead;
	private List<PictureInfo> mLstPic;
	private int mCount = 1;

	private AdapterInfo mAdapter;
	private AdapterViewPager mAdapterHead;

	private ImageLoader mImgLoader;

	public NewsBlockView(Context context, NewsTitle title) {
		this.mContext = context;
		this.mTitle = title;

		mLstInfo = new ArrayList<NewsInfo>();
		mLstHead = new ArrayList<NewsInfo>();
		mLstPic = new ArrayList<PictureInfo>();

		mImgLoader = AppContext.getApp().getImgLoader();

		initCtrl();

		mAdapter = new AdapterInfo();
		mLv.setAdapter(mAdapter);

		requestData();
	}

	private void initCtrl() {

		mRootView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.frag_news_block,
					null);
		// mVpHead = (ViewPager)
		// LayoutInflater.from(mContext).inflate(R.layout.frags_news_info_head,
		// mLv, false);
		mPtrlv = (PullToRefreshListView) mRootView.findViewById(R.id.ptrlv_newsblock);
		mPtrlv.setMode(Mode.BOTH);
		mLv = mPtrlv.getRefreshableView();
		mLv.setDivider(mContext.getResources().getDrawable(R.color.lightgrey));
		mLv.setDividerHeight(1);

		if (mTitle.getType().equals(TITLETYPE_NEWSWITHHEAD)) {
			mAdapterHead = new AdapterViewPager();
			// mVpHead.setAdapter(mAdapterHead);
			// mLv.addHeaderView(mVpHead);
		}
		mPtrlv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				if (mPtrlv.isHeaderShown()) {
					requestData();
				} else if (mPtrlv.isFooterShown()) {
					requestMore();
				}
			}
		});

		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				// pulltorefresh控件会导致这里的position比实际position大1，而附带的头部会导致一个额外的item
				NewsInfo info = mLstInfo.get(isHasHead() ? position - 2 : position - 1);
				if (info.getIsSubjectEntrance() == 1) {

					String strTopicId = info.getSubjectId();
					BaseKitManager.openNewsTopic((BaseActivity) mContext, strTopicId);
				} else {
					String strNewsId = info.getId();
					BaseKitManager.openNewsDetail((BaseActivity) mContext, strNewsId);
				}
			}
		});
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
					.get(URLUtil.getURL_InfoPage(mTitle.getTag(), 1), map, new IListener<String>() {

						@Override
						public void onCall(String strJson) {
							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(mContext, "请求数据失败", Toast.LENGTH_SHORT).show();
								return;
							}
							AppContext.getApp()
										.getJsonManager()
										.parse(strJson, NewsBlock.class,
													new IListener<NewsBlock>() {

														@Override
														public void onCall(NewsBlock t) {
															if (t == null) {
																Toast.makeText(mContext, "转换数据失败",
																			Toast.LENGTH_SHORT)
																			.show();
																return;
															}

															mLstInfo.clear();
															if (t.getHeaderline() != null) {
																mLstHead.addAll(t.getHeaderline());
																// mLv.addHeaderView(mVpHead);
																mAdapterHead.notifyDataSetChanged();
															}
															mLstInfo.addAll(t.getData());
															mAdapter.notifyDataSetChanged();
															mCount = 1;
															mPtrlv.onRefreshComplete();
															mLv.setSelection(0);
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

		AppContext.getApp()
					.getNetManager()
					.get(URLUtil.getURL_InfoPage(mTitle.getTag(), mCount + 1), map,
								new IListener<String>() {

									@Override
									public void onCall(String strJson) {

										AppContext.getApp()
													.getJsonManager()
													.parse(strJson, NewsBlock.class,
																new IListener<NewsBlock>() {

																	@Override
																	public void onCall(NewsBlock t) {

																		int nBefore = mLstInfo
																					.size();
																		mLstInfo.addAll(t.getData());
																		mAdapter.notifyDataSetChanged();
																		mCount += 1;
																		mPtrlv.onRefreshComplete();
																		mLv.setSelection(nBefore);
																	}

																});
									}
								});
	}

	private boolean isHasHead() {
		return mTitle.getType().equals(TITLETYPE_NEWSWITHHEAD);
	}

	class AdapterViewPager extends PagerAdapter {

		private View[] arrViewRoot;

		public AdapterViewPager() {
			arrViewRoot = new View[mLstHead == null ? 0 : mLstHead.size()];
		}

		@Override
		public void notifyDataSetChanged() {
			if (arrViewRoot == null || (mLstHead != null && arrViewRoot.length != mLstHead.size())) {
				arrViewRoot = new View[mLstHead.size()];
			}
			super.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mLstHead == null ? 0 : mLstHead.size();
		}

		private View initView(final int position) {

			View vRoot = LayoutInflater.from(mContext).inflate(R.layout.frag_news_info_head_item,
						mVpHead, false);
			ImageView img = (ImageView) vRoot.findViewById(R.id.img_head);
			TextView tv = (TextView) vRoot.findViewById(R.id.tv_head);
			mImgLoader.displayImage(mLstHead.get(position).getPhoto(), img);
			tv.setText(mLstHead.get(position).getTitle());

			vRoot.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					BaseKitManager.openNewsDetail((BaseActivity) mContext, mLstHead.get(position)
								.getId());
				}
			});

			return vRoot;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(arrViewRoot[arg1]);
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			if (arrViewRoot[arg1] == null) {
				arrViewRoot[arg1] = initView(arg1);
			}
			((ViewPager) arg0).addView(arrViewRoot[arg1]);
			return arrViewRoot[arg1];
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}

	private class AdapterInfo extends BaseAdapter {

		@Override
		public int getCount() {
			return mLstInfo == null || mLstInfo.size() == 0 ? 0 : mLstInfo.size() + 1;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		private View initHead(ViewGroup parent) {
			mVpHead = (ViewPager) LayoutInflater.from(mContext).inflate(
						R.layout.frags_news_info_head, parent, false);
			mAdapterHead = new AdapterViewPager();
			mVpHead.setAdapter(mAdapterHead);
			return mVpHead;
		}

		@Override
		public int getItemViewType(int position) {
			if (isHasHead()) {
				return position == 1 ? 0 : 1;
			} else {
				return 0;
			}
		}

		@Override
		public int getViewTypeCount() {
			return mTitle.getType().equals(TITLETYPE_NEWSWITHHEAD) ? 2 : 1;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (mTitle.getType().equals(TITLETYPE_NEWSWITHHEAD) && position == 0) {
				initHead(parent);
				return mVpHead;
			}

			ViewHolder holder;
			if (convertView == null || convertView.getTag() == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.frag_news_info_item,
							parent, false);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
				holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
				holder.tvRead = (TextView) convertView.findViewById(R.id.tv_read);
				holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NewsInfo info = mLstInfo.get(isHasHead() ? position - 1 : position);
			mImgLoader.displayImage(info.getPhoto(), holder.img);
			holder.tvTitle.setText(info.getTitle());
			holder.tvContent.setText(info.getContent());
			holder.tvRead.setVisibility(Integer.parseInt(info.getReadCount()) > 0 ? View.VISIBLE
						: View.INVISIBLE);
			holder.tvRead.setText(info.getReadCount() + " 阅读");

			Date time = new Date(1000 * Long.parseLong(info.getTime()));
			String strTime = "" + (time.getMonth() + 1) + "月" + time.getDate() + "日 "
						+ time.getHours() + ":" + time.getMinutes();

			holder.tvTime.setText(strTime);

			return convertView;
		}

		class ViewHolder {

			ImageView img;
			TextView tvTitle;
			TextView tvContent;
			TextView tvRead;
			TextView tvTime;
		}

	}

	public ViewGroup getRootView() {
		return mRootView;
	}
}
