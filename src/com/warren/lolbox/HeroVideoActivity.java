package com.warren.lolbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.warren.lolbox.model.AdapterVideo;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.Video;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.widget.TitleBar;

/**
 * 英雄视频
 * @author yangsheng
 * @date 2015年1月31日
 */
public class HeroVideoActivity extends BaseActivity {
	
	public static final String EXTRA_HEROENG = "EXTRA_HEROENG";
	public static final String EXTRA_HEROCHN = "EXTRA_HEROCHN";
	
	private TitleBar mTb;
	private PullToRefreshListView mPtrLv;
	private ListView mLvVideos;
	private List<Video> mLstVideos;
	private AdapterVideo mAdapter;
	
	private String mStrNameEng;
	private String mStrNameChn;
	
	private int mCount;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_herovideo);
		
		mStrNameChn = getIntent().getStringExtra(EXTRA_HEROCHN);
		mStrNameEng = getIntent().getStringExtra(EXTRA_HEROENG);
		
		if(mStrNameEng == null || mStrNameChn == null){
			Toast.makeText(this, "英雄未指定！", Toast.LENGTH_LONG).show();
			return;
		}
		
		mPtrLv = (PullToRefreshListView) findViewById(R.id.ptrlv_hotnews);
		mPtrLv.setMode(Mode.BOTH);
		mLvVideos = mPtrLv.getRefreshableView();
		mLvVideos.setDivider(null);
		mLvVideos.setDividerHeight(0);
		
		mAdapter = new AdapterVideo(this, AppContext.getApp().getImgLoader());
		
		mLvVideos.setAdapter(mAdapter);
		
		mPtrLv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				if(mPtrLv.isHeaderShown()){
					requestData();
				} else if(mPtrLv.isFooterShown()){
					requestMore();
				}
			}
		});
		
		mLvVideos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
		
		requestData();
	}
	
	/**
	 * 刷新数据
	 */
	private void requestData(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7206");
//		map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");
		
		AppContext.getApp().getNetManager().get(URLUtil.getURL_Video(mStrNameEng, 1), map, new IListener<String>() {
			
			@Override
			public void onCall(String strJson) {
				
				AppContext.getApp().getJsonManager().parseList(strJson, Video.class, new IListener<List<Video>>(){

					@Override
					public void onCall(List<Video> t) {
						
						mLstVideos.clear();
						mLstVideos.addAll(t);
						mAdapter.notifyDataSetChanged();
						mCount = 1;
						mPtrLv.onRefreshComplete();
						mLvVideos.setSelection(mLstVideos.size());
					}
					
				});
			}
		});
	}
	
	/**
	 * 请求其他页的数据
	 */
	private void requestMore(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Dw-Guid", "0A1520BAA4D48A54755261EA62EA7206");
//		map.put("Dw-Ua", "lolbox&2.0.9d-209&adr&xiaomi");
		map.put("Dw-Ua", "");
		
		AppContext.getApp().getNetManager().get(URLUtil.getURL_Video(mStrNameEng, mCount + 1), map, new IListener<String>() {
			
			@Override
			public void onCall(String strJson) {
				
				AppContext.getApp().getJsonManager().parseList(strJson, Video.class, new IListener<List<Video>>(){

					@Override
					public void onCall(List<Video> t) {
						
						int nBefore = mLstVideos.size();
						mLstVideos.addAll(t);
						mAdapter.notifyDataSetChanged();
						mCount++;
						mPtrLv.onRefreshComplete();
						mLvVideos.setSelection(nBefore);
					}
					
				});
			}
		});
	}

	@Override
	protected boolean goBack() {
		return false;
	}
}
