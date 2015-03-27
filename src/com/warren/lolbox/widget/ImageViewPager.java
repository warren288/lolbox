package com.warren.lolbox.widget;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.AppContext;
import com.warren.lolbox.BaseActivity;
import com.warren.lolbox.BaseKitManager;
import com.warren.lolbox.R;
import com.warren.lolbox.model.bean.NewsInfo;

/**
 * 简单ViewPager封装，暂时只有资讯页-头条 界面中用上
 * @author yangsheng
 * @date 2015年3月27日
 */
public class ImageViewPager extends RelativeLayout {

	private static final int MSG_CHANGETONEXT = 0x0001;
	private static final int TIME_DELAY = 5000;
	private static final int TIME_PERIOD = 5000;

	private ImageLoader mImgLoader;
	private List<NewsInfo> mLstHead;
	private AdapterViewPager mAdapter;
	private ViewPager mVp;
	private SmallDotIndicator mSdi;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_CHANGETONEXT:
				if (mLstHead == null || mLstHead.size() == 0) {
					break;
				}
				int currentItem = mVp.getCurrentItem();
				if (currentItem == mLstHead.size() - 1) {
					mVp.setCurrentItem(0);
				} else {
					mVp.setCurrentItem(currentItem + 1);
				}
				break;

			default:
				break;
			}
		};
	};

	public ImageViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public ImageViewPager(Context context) {
		super(context);
		init();
	}

	private void init() {

		LayoutInflater.from(getContext()).inflate(R.layout.newsinfo_head, this, true);
		mSdi = (SmallDotIndicator) findViewById(R.id.sdi);
		mVp = (ViewPager) findViewById(R.id.vp);
		mImgLoader = AppContext.getApp().getImgLoader();
		mAdapter = new AdapterViewPager();
		mVp.setAdapter(mAdapter);
		initTimer();

		mVp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				mSdi.setCurrentIndex(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/**
	 * 设新闻数据
	 * @param lstNews
	 */
	public void setNewsInfo(List<NewsInfo> lstNews) {
		this.mLstHead = lstNews;
		mSdi.setCount(mLstHead == null ? 0 : mLstHead.size());
		refresh();
	}

	/**
	 * 刷新内容
	 */
	public void refresh() {
		mAdapter.notifyDataSetChanged();
		mSdi.setCurrentIndex(0);
	}

	/**
	 * 定时翻页
	 */
	private void initTimer() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(MSG_CHANGETONEXT);
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, TIME_DELAY, TIME_PERIOD);
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

			View vRoot = LayoutInflater.from(getContext()).inflate(
						R.layout.frag_news_info_head_item, ImageViewPager.this, false);
			ImageView img = (ImageView) vRoot.findViewById(R.id.img_head);
			TextView tv = (TextView) vRoot.findViewById(R.id.tv_head);
			mImgLoader.displayImage(mLstHead.get(position).getPhoto(), img);
			tv.setText(mLstHead.get(position).getTitle());

			vRoot.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					BaseKitManager.openNewsDetail((BaseActivity) getContext(),
								mLstHead.get(position).getId());
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

}
