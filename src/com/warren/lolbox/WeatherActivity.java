package com.warren.lolbox;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.widget.TitleBar;

/**
 * 天气Activity，使用腾讯的网页
 * @author yangsheng
 * @date 2015年3月12日
 */
public class WeatherActivity extends BaseActivity {

	private static String mStrHomeUrl = "http://weather.html5.qq.com/";
	private static final String TITLE = "天气";
	private static final String WEATHERHOME = "主页";

	private TitleBar mTb;
	private WebView mWv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		initCtrl();
	}

	private void initCtrl() {
		mTb = (TitleBar) findViewById(R.id.titlebar);
		mWv = (WebView) findViewById(R.id.wv_detail);

		mTb.setText(TITLE);
		mTb.setRightOpers(WEATHERHOME);
		mTb.setRightOperListener(new IListener<String>() {

			@Override
			public void onCall(String t) {
				if (WEATHERHOME.equals(t)) {
					mWv.loadUrl(mStrHomeUrl);
				}
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

		WebSettings wbSet = mWv.getSettings();
		wbSet.setDefaultTextEncodingName("utf-8");
		wbSet.setJavaScriptEnabled(true);
		wbSet.setJavaScriptCanOpenWindowsAutomatically(false);
		wbSet.setBuiltInZoomControls(true);
		wbSet.setDisplayZoomControls(false);

		mWv.loadUrl(mStrHomeUrl);

		mWv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	protected boolean goBack() {
		if (mWv.getUrl().equals(mStrHomeUrl)) {
			return false;
		}
		if (mWv.canGoBack()) {
			mWv.goBack();
		}
		return true;
	}

}
