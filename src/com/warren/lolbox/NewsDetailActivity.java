package com.warren.lolbox;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.NewsDetail;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

public class NewsDetailActivity extends Activity {

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
		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7212");
		// map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");
		AppContext.getApp().getNetManager().get(strUrl, map, new IListener<String>() {

			@Override
			public void onCall(String t) {
				if (StringUtils.isNullOrZero(t)) {
					Toast.makeText(NewsDetailActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
					return;
				}

				AppContext.getApp().getJsonManager()
							.parse(t, NewsDetail.class, new IListener<NewsDetail>() {

								@Override
								public void onCall(NewsDetail detail) {
									if (detail == null) {
										Toast.makeText(NewsDetailActivity.this, "转换新闻数据失败",
													Toast.LENGTH_SHORT).show();
										return;
									}
									String strHtml = (String) detail.getData().get("content");
									wvDetail.loadDataWithBaseURL(null, strHtml, "text/html",
												"utf-8", null);
								}
							});

			}
		});
	}

}
