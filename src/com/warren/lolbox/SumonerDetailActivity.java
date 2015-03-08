package com.warren.lolbox;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.warren.lolbox.util.LogTool;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.TitleBar;

public class SumonerDetailActivity extends BaseActivity {

	public static final String EXTRA_CONTENTDATA = "CONTENTDATA";
	public static final String EXTRA_URL = "FILEPATH";

	private TitleBar mTb;
	private WebView mWvContent;

	private String mStrContent;
	private String mStrURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summonerdetail);

		mStrContent = getIntent().getStringExtra(EXTRA_CONTENTDATA);
		mStrURL = getIntent().getStringExtra(EXTRA_URL);

		initCtrl();
	}

	private void initCtrl() {
		mTb = (TitleBar) findViewById(R.id.titlebar);
		mWvContent = (WebView) findViewById(R.id.wv_content);

		WebSettings wbSet = mWvContent.getSettings();
		wbSet.setDefaultTextEncodingName("utf-8");
		wbSet.setJavaScriptEnabled(true);
		wbSet.setJavaScriptCanOpenWindowsAutomatically(false);
		wbSet.setBuiltInZoomControls(true);
		wbSet.setDisplayZoomControls(false);

		mWvContent.setWebViewClient(new SummonerWebClient());

		if (mStrContent != null) {
			mWvContent.loadDataWithBaseURL(null, mStrContent, "text/html", "utf-8", null);
		} else if (mStrURL != null) {
			mWvContent.loadUrl(mStrURL);
		} else {
			mWvContent.loadData("没有数据", "text/html", "utf-8");
		}

		// mWvContent.setWebViewClient();

	}

	class SummonerWebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			LogTool.i("SummonerDetailActivity", "召唤师比赛URL: " + url);

			/*
			 * 战绩详情URL
			 * http://zdl.mbox.duowan.com/phone/matchDetailNew.php?lolboxAction=
			 * toMatchDetail
			 * &shareKey=aXmLpobX3EiLd719189mVm14eQ&sn=%E7%94%B5%E4%
			 * BF%A1%E4%B8%80
			 * &pn=%E8%BF%98%E5%9C%A8%E7%AD%89%E5%BE%85&v=0&sk=92628711
			 * T&queueType=RANKED"
			 */

			/*
			 * 查看全部URL
			 * http://zdl.mbox.duowan.com/phone/matchListNew.php?sn=%E7%94%B5%E4%
			 * BF
			 * %A1%E4%B8%80&pn=%E8%BF%98%E5%9C%A8%E7%AD%89%E5%BE%85&v=213&hero=
			 * &timestamp=1425826517
			 */
			if (StringUtils.isNullOrZero(url)) {
				return true;
			}
			if (url.contains("lolboxAction=toMatchDetail")) {
				openCombatDetail(url);
			}

			return true;
		}
	}

	private void openCombatDetail(String strUrl) {

		Intent intent = new Intent(this, CombatDetailActivity.class);
		intent.putExtra(CombatDetailActivity.EXTRA_URL, strUrl);
		startActivity(intent);
	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
