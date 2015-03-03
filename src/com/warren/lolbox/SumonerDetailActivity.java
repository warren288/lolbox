package com.warren.lolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class SumonerDetailActivity extends Activity {

	public static final String EXTRA_CONTENTDATA = "CONTENTDATA";
	public static final String EXTRA_URL = "FILEPATH";
	
	private ImageView mImgLeft;
	private ImageView mImgRight;
	private TextView mTvTitle;
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
	
	private void initCtrl(){
		
		mImgLeft = (ImageView) findViewById(R.id.img_title_left);
		mImgRight = (ImageView) findViewById(R.id.img_title_right);
		mTvTitle = (TextView) findViewById(R.id.tv_title);
		
		mImgLeft.setImageResource(R.drawable.lolbox_titleview_return_default);
		mTvTitle.setText("召唤师");
		
		mWvContent = (WebView) findViewById(R.id.wv_content);
		
		WebSettings wbSet = mWvContent.getSettings();
		wbSet.setDefaultTextEncodingName("utf-8");
		wbSet.setJavaScriptEnabled(true);
		wbSet.setJavaScriptCanOpenWindowsAutomatically(false);
		wbSet.setBuiltInZoomControls(true);
		wbSet.setDisplayZoomControls(false);
		
		if(mStrContent != null){
			mWvContent.loadDataWithBaseURL(null, mStrContent, "text/html", "utf-8", null);
		} else if(mStrURL != null){
			mWvContent.loadUrl(mStrURL);
		} else {
			mWvContent.loadData("没有数据", "text/html", "utf-8");
		}
		
		
		mImgLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
//		mWvContent.setWebViewClient();
		
		
	}
	
	
}
