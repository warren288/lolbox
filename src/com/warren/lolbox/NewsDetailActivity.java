package com.warren.lolbox;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsDetail;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.TitleBar;

/**
 * 新闻详情页面，网页内视频播放暂未支持
 * @author yangsheng
 * @date 2015年3月13日
 */
public class NewsDetailActivity extends BaseActivity {

	public static final String EXTRA_NEWSID = "EXTRA_NEWSID";

	private TitleBar mTb;
	private WebView mWvDetail;

	private String mStrNewsId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		mStrNewsId = getIntent().getStringExtra(EXTRA_NEWSID);
		initCtrl();
		if (!StringUtils.isNullOrZero(mStrNewsId)) {
			requestData();
		}
	}

	private void initCtrl() {
		mTb = (TitleBar) findViewById(R.id.titlebar);
		mWvDetail = (WebView) findViewById(R.id.wv_detail);

		mWvDetail.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url, SystemConfig.getIntance().getCommHead());
				return true;
			}
		});

		mTb.setLeftClick(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!goBack()) {
					finish();
				}
			}
		});
	}

	private void requestData() {

		String strUrl = URLUtil.getURL_HotNewsDetail(mStrNewsId);
		httpGetAndParse(strUrl, SystemConfig.getIntance().getCommHead(), NewsDetail.class,
					new IListener<NewsDetail>() {

						@Override
						public void onCall(NewsDetail detail) {
							if (detail == null) {
								Toast.makeText(NewsDetailActivity.this, "转换新闻数据失败",
											Toast.LENGTH_SHORT).show();
								return;
							}
							String strHtml = (String) detail.getData().get("content");
							mWvDetail.loadDataWithBaseURL(null, strHtml, "text/html", "utf-8", null);
						}
					});
	}

	@Override
	protected boolean goBack() {
		if (mWvDetail.canGoBack()) {
			mWvDetail.goBack();
			return true;
		}
		return false;
	}

}
