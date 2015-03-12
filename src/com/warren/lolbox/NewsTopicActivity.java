package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsInfo;
import com.warren.lolbox.model.bean.NewsTopic;
import com.warren.lolbox.model.bean.NewsTopicRoot;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.widget.TitleBar;

/**
 * 专题界面
 * @author yangsheng
 * @date 2015年3月11日
 */
public class NewsTopicActivity extends BaseActivity {

	public static final String EXTRA_TOPICID = "EXTRA_TOPICID";

	private TitleBar mTb;
	private PullToRefreshListView mPtrlv;
	private ListView mLv;

	private String mStrTopicId;
	private NewsTopic mTopic;

	private List<NewsInfo> mLstInfo;
	private AdapterInfo mAdapter;
	private ImageLoader mImgLoader;

	private LinearLayout mLlHead;
	private ImageView mImgHead;
	private TextView mTvHead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic);

		mStrTopicId = getIntent().getStringExtra(EXTRA_TOPICID);

		initCtrl();
		mImgLoader = AppContext.getApp().getImgLoader();
		requestData();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);
		mPtrlv = (PullToRefreshListView) findViewById(R.id.ptrlv_newsblock);
		mLv = mPtrlv.getRefreshableView();
		mLv.setDivider(getResources().getDrawable(R.color.lightgrey));
		mLv.setDividerHeight(1);

		mLlHead = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_topic_head,
					mLv, false);
		mImgHead = (ImageView) mLlHead.findViewById(R.id.img_head);
		mTvHead = (TextView) mLlHead.findViewById(R.id.tv_head);

		mAdapter = new AdapterInfo();
		mLv.setAdapter(mAdapter);

		mPtrlv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				if (mPtrlv.isHeaderShown()) {
					requestData();
				}
			}
		});

		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// pulltorefresh控件会导致这里的position比实际position大1，而附带的头部会导致一个额外的item
				if (position < 2) {
					return;
				}
				String strNewsId = mLstInfo.get(position - 2).getId();
				BaseKitManager.openNewsDetail(NewsTopicActivity.this, strNewsId);
			}
		});
	}

	private void requestData() {

		httpGetAndParse(URLUtil.getURL_NewsTopic(mStrTopicId), SystemConfig.getIntance()
					.getCommHead(), NewsTopicRoot.class, new IListener<NewsTopicRoot>() {

			@Override
			public void onCall(NewsTopicRoot ntr) {
				if (ntr == null) {
					Toast.makeText(NewsTopicActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
					return;
				}
				NewsTopic topic = ntr.getData().get(0).getData();
				mPtrlv.onRefreshComplete();
				setView(topic);
			}
		});
	}

	private void setView(NewsTopic topic) {
		mTopic = topic;
		mLstInfo = new ArrayList<NewsInfo>();
		mLstInfo.addAll(mTopic.getNews());
		mAdapter.notifyDataSetChanged();
		mLv.setSelection(0);

		mTb.setText(mTopic.getTitle());
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

			mImgLoader.displayImage(mTopic.getPhoto(), mImgHead);
			mTvHead.setText(mTopic.getContent());
			return mLlHead;
		}

		@Override
		public int getItemViewType(int position) {
			return position == 0 ? 0 : 1;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (position == 0) {
				initHead(parent);
				return mLlHead;
			}

			ViewHolder holder;
			if (convertView == null || convertView.getTag() == null) {
				convertView = LayoutInflater.from(NewsTopicActivity.this).inflate(
							R.layout.frag_news_info_item, parent, false);
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
			NewsInfo info = mLstInfo.get(position - 1);
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

	@Override
	protected boolean goBack() {
		return false;
	}

}
