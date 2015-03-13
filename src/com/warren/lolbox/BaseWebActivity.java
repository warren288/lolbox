package com.warren.lolbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.warren.lolbox.model.AsyncSaveBitmap;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.SelectPopWindow;
import com.warren.lolbox.model.SimpleUserTool;
import com.warren.lolbox.widget.TitleBar;

/**
 * 使用WebView的基础Activity，能满足大多数网页界面的要求
 * @author yangsheng
 * @date 2015年3月13日
 */
public class BaseWebActivity extends BaseActivity {

	public static final String EXTRA_CONTENTDATA = "CONTENTDATA";
	public static final String EXTRA_URL = "FILEPATH";
	public static final String EXTRA_TITLE = "EXTRA_TITLE";

	private TitleBar mTb;
	private WebView mWvContent;

	private String mStrTitle;
	private String mStrContent;
	private String mStrURL;

	private List<SimpleUserTool> mLstTool;
	private IListener<String> mListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_combatdetail);

		mStrContent = getIntent().getStringExtra(EXTRA_CONTENTDATA);
		mStrURL = getIntent().getStringExtra(EXTRA_URL);
		mStrTitle = getIntent().getStringExtra(EXTRA_TITLE);
		if (mStrTitle == null) {
			mStrTitle = "";
		}

		initCtrl();
	}

	private void initCtrl() {
		
		mTb = (TitleBar) findViewById(R.id.titlebar);
		mTb.setText(mStrTitle);

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
			mWvContent.loadUrl(mStrURL, SystemConfig.getIntance().getCommHead());
		} else {
			mWvContent.loadData("没有数据", "text/html", "utf-8");
		}

		mListener = new IListener<String>() {
			@Override
			public void onCall(String t) {
				if (t.equals("刷新")) {
					mWvContent.reload();
				} else if (t.equals("截图")) {
					captureScreen();
				}
			}
		};
		SimpleUserTool sutRefresh = new SimpleUserTool("刷新", mListener);
		SimpleUserTool sutCapture = new SimpleUserTool("截图", mListener);
		mLstTool = new ArrayList<SimpleUserTool>();
		mLstTool.add(sutCapture);
		mLstTool.add(sutRefresh);
		
		mTb.setLeftClick(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!goBack()) {
					finish();
				}
			}
		});

		mTb.setRightClick(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectPopWindow.create(v, mLstTool).show();
			}
		});
	}

	private void captureScreen() {

		ViewGroup root = (ViewGroup) mWvContent.getParent();
		root.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
		mWvContent.setDrawingCacheEnabled(false);

		String strTime = new Date().toLocaleString();

		new AsyncSaveBitmap(AppContext.PICTURE_FOLDER + strTime + ".jpg").registerListener(
					new IListener<Boolean>() {

						@Override
						public void onCall(Boolean t) {

							Toast.makeText(BaseWebActivity.this, t ? "截图保存成功" : "截图保存失败",
										Toast.LENGTH_SHORT).show();
						}
					}).execute(bitmap);
	}

	@Override
	protected boolean goBack() {
		return false;
	}

	class SummonerWebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String strUrl) {
			BaseKitManager.openUrl(BaseWebActivity.this, strUrl);
			return true;
		}
	}
}
