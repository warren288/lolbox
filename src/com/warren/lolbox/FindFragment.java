package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.warren.lolbox.model.BaseContentFragment;
import com.warren.lolbox.model.SimpleTool;

/**
 * 发现Fragment
 * @author warren
 * @date 2014年12月28日
 */
public class FindFragment extends BaseContentFragment {

	private View mVRoot;
	
	private ListView mLvFind;

	private String[] mArrFind = { "关于" };
	
	private AdapterList mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mVRoot = inflater.inflate(R.layout.frag_find, container, false);
		initCtrl();
		return mVRoot;

		//
		// TextView tv = new TextView(inflater.getContext());
		// tv.setText("发现");
		// return tv;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
	}
	
	private void initCtrl(){
		mLvFind = (ListView) mVRoot.findViewById(R.id.lv_find);
		
		List<SimpleTool> items = new ArrayList<SimpleTool>();
		items.add(new SimpleTool(-1, "关于"));
		
		mAdapter = new AdapterList(LayoutInflater.from(getActivity()), items);
		mLvFind.setAdapter(mAdapter);
		mLvFind.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent it = new Intent(getActivity(), AboutActivity.class);
				startActivity(it);
				getActivity().overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
			}
		});
	}

	@Override
	public String getName() {
		return null;
	}

}
