package com.warren.lolbox;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsDetail;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

public class NewsDetailActivity extends BaseActivity {

	public static final String EXTRA_NEWSID = "EXTRA_NEWSID";

	private WebView wvDetail;

	private String strNewsId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		strNewsId = getIntent().getStringExtra(EXTRA_NEWSID);
		initCtrl();
		if (!StringUtils.isNullOrZero(strNewsId)) {
			requestData();
		}
	}

	private void initCtrl() {
		wvDetail = (WebView) findViewById(R.id.wv_detail);
	}

	private void requestData() {

		String strUrl = URLUtil.getURL_HotNewsDetail(strNewsId);
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
							wvDetail.loadDataWithBaseURL(null, strHtml, "text/html", "utf-8", null);
						}
					});
	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
