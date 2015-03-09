package com.warren.lolbox;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.warren.lolbox.util.LogTool;
import com.warren.lolbox.widget.TitleBar;

/**
 * 战绩详情Activity
 * @author yangsheng
 * @date 2015年3月8日
 */
public class CombatDetailActivity extends BaseActivity {

	public static final String EXTRA_CONTENTDATA = "CONTENTDATA";
	public static final String EXTRA_URL = "FILEPATH";

	private TitleBar mTb;
	private WebView mWvContent;

	private String mStrContent;
	private String mStrURL;
	
	private Map<String, String> mHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combatdetail);

		mStrContent = getIntent().getStringExtra(EXTRA_CONTENTDATA);
		mStrURL = getIntent().getStringExtra(EXTRA_URL);

		mHeader = new HashMap<String, String>();
		mHeader.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7212");
		mHeader.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");

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
			mWvContent.loadUrl(mStrURL, mHeader);
		} else {
			mWvContent.loadData("没有数据", "text/html", "utf-8");
		}

		// mWvContent.setWebViewClient();

	}

	@Override
	protected boolean goBack() {
		return false;
	}

	class SummonerWebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String strUrl) {
			LogTool.i("CombatDetailActivity", "召唤师比赛URL: " + strUrl);
			BaseKitManager.openUrl(CombatDetailActivity.this, strUrl);
			return true;
		}
	}
}
