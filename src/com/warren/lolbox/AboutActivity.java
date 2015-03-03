package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.SimpleTool;
import com.warren.lolbox.widget.TitleBar;

/**
 * 关于Activity
 * @author yangsheng
 * @date 2015年2月25日
 */
public class AboutActivity extends BaseActivity {

	private TitleBar mTb;
	private ImageView mImgLol;
	private TextView mTvRight;
	private TextView mTvCurVersion;
	private ListView mLvTypes;

	private TextView mTvInfo;
	private List<SimpleTool> mLstTool;
	private IListener<Integer> mListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		mLstTool = new ArrayList<SimpleTool>();
		mLstTool.add(new SimpleTool(-1, "作者信息"));
		mLstTool.add(new SimpleTool(-1, "声明"));
		mLstTool.add(new SimpleTool(-1, "帮助与反馈"));

		mListener = new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
				switch (t) {
				case 0:
					mTvInfo.setText(R.string.author_info);
					break;
				case 1:
					mTvInfo.setText(R.string.author_reference);
					break;
				case 2:
					mTvInfo.setText(R.string.help_feedback);
					break;
				default:
					break;
				}
				mTvInfo.startAnimation(AnimationUtils.loadAnimation(AboutActivity.this,
							android.R.anim.slide_in_left));
				mTvInfo.setVisibility(View.VISIBLE);
			}
		};
		
		initCtrl();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);
		mImgLol = (ImageView) findViewById(R.id.img_lolbox);
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mLvTypes = (ListView) findViewById(R.id.lv_types);
		mTvInfo = (TextView) findViewById(R.id.tv_info);
		
		AdapterList mAdapter = new AdapterList(getLayoutInflater(), mLstTool);
		mLvTypes.setAdapter(mAdapter);

		mTb.setLeftClick(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if( ! goBack()){
					finish();
				}
			}
		});

		mLvTypes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mListener.onCall(position);
			}
		});
	}

	@Override
	protected boolean goBack() {
		if (mTvInfo.getVisibility() == View.VISIBLE) {
			mTvInfo.startAnimation(AnimationUtils.loadAnimation(AboutActivity.this,
						android.R.anim.slide_out_right));
			mTvInfo.setVisibility(View.GONE);
			return true;
		}
		return false;
	}
}
