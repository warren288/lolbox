package com.warren.lolbox;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.warren.lolbox.model.BaseContentFragment;
import com.warren.lolbox.model.IListener;

/**
 * 主界面
 * @author warren
 * @date 2014年12月28日
 */
public class MainActivity extends Activity {

	private FootFragment mFootFrag;

	/**
	 * 用于各功能Fragment切换的监听器
	 */
	private IListener<Integer> mListener;
	private BaseContentFragment[] mFragContents = new BaseContentFragment[5];
	private int mCurrentFragIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListener = new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
				
				FragmentTransaction tranc = getFragmentManager().beginTransaction();
				tranc.hide(mFragContents[mCurrentFragIndex]);
				
				// 如果功能Fragment之前从未打开过，则创建并添加之；如果打开过，则显示之。
				if (mFragContents[t] == null) {
					switch (t) {
					case 0:
						mFragContents[t] = new ToolFragment();
						break;
					case 1:
						mFragContents[t] = new NewsFragment();
						break;
					case 2:
						mFragContents[t] = new ChatFragment();
						break;
					case 3:
						mFragContents[t] = new ActionFragment();
						break;
					case 4:
						mFragContents[t] = new FindFragment();
						break;

					default:
						break;
					}
					tranc.add(R.id.fl_main_frags, mFragContents[t], mFragContents[t].getName());
				} else {
					tranc.show(mFragContents[t]);
				}
				tranc.commit();
				mCurrentFragIndex = t;
			}
		};

		initFrags();
	}

	/**
	 * 添加初始Fragment
	 */
	private void initFrags() {

		FragmentTransaction tranc = getFragmentManager().beginTransaction();
		mFootFrag = new FootFragment();
		// 设置工具选中监听器回调。
		mFootFrag.setListeners(mListener);
		tranc.add(R.id.ll_main_foot_root, mFootFrag, mFootFrag.getName());

		mFragContents[0] = new ToolFragment();
		tranc.add(R.id.fl_main_frags, mFragContents[0], mFragContents[0].getName());

		tranc.commit();
	}

}
