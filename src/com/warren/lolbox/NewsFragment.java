package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.warren.lolbox.model.BaseContentFragment;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsTitle;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.ViewPagerIndicator;

/**
 * 新闻Fragment
 * @author warren
 * @date 2014年12月28日
 */
public class NewsFragment extends BaseContentFragment {

	public static final String FRAGMENTNAME = "NewsFragment";
	private View mVRoot;
	private ViewPagerIndicator mIndicator;
	private ViewPager mPager;

	private static final String[] ARRAY_TITLE = new String[] { "头条", "视频", "赛事"/*, "靓照", "囧图", "壁纸" */};
	private static final String[] ARRAY_TITLE_REQUEST = new String[] { "头条", "视频", "赛事", "靓照",
			"囧图", "壁纸" };

	private List<NewsTitle> mLstTitles = new ArrayList<NewsTitle>();

	private AdapterViewPager mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mVRoot = inflater.inflate(R.layout.frag_news, container, false);
		initCtrl();
		return mVRoot;
	}

	private void initCtrl() {

		mIndicator = (ViewPagerIndicator) getRootView().findViewById(R.id.vpindicator);
		mPager = (ViewPager) getRootView().findViewById(R.id.vp);

		mIndicator.setTabItemTitles(Arrays.asList(ARRAY_TITLE));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7212");
		// map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");
		
		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_InfoTitle(), map, new IListener<String>() {

						@Override
						public void onCall(String strJson) {
							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(getRootView().getContext(), "请求标题错误",
											Toast.LENGTH_SHORT).show();
								return;
							}
							AppContext.getApp()
										.getJsonManager()
										.parseList(strJson, NewsTitle.class,
													new IListener<List<NewsTitle>>() {

														@Override
														public void onCall(
																	List<NewsTitle> lstNewsTitle) {
															if (lstNewsTitle == null
																		|| lstNewsTitle.size() == 0) {
																Toast.makeText(
																			getRootView()
																						.getContext(),
																			"请求标题错误",
																			Toast.LENGTH_SHORT)
																			.show();
																return;
															}
															mLstTitles = lstNewsTitle;
															
															mAdapter = new AdapterViewPager();
															mPager.setAdapter(mAdapter);
														}
													});
						}
					});

		mIndicator.setViewPager(mPager, 0);
	}

	class AdapterViewPager extends PagerAdapter {

		private View[] arrViewRoot = new View[3];

		public AdapterViewPager() {
			for (int i = 0; i < getCount(); i++) {
				arrViewRoot[i] = new NewsBlockView(getRootView().getContext(), mLstTitles.get(i))
							.getRootView();
			}
		}

		@Override
		public int getCount() {
			return 3;
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

	@Override
	public String getName() {
		return FRAGMENTNAME;
	}

	@Override
	public View getRootView() {
		return mVRoot;
	}

}
