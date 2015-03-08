package com.warren.lolbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warren.lolbox.model.BaseContentFragment;

/**
 * 动态Fragment
 * @author warren
 * @date 2014年12月28日
 */
public class ActionFragment extends BaseContentFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		TextView tv = new TextView(inflater.getContext());
		tv.setText("动态");
		return tv;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public View getRootView() {
		return null;
	}
}
