package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.warren.lolbox.model.SimpleTool;
import com.warren.lolbox.widget.TitleBar;

/**
 * 游戏百科
 * @author yangsheng
 * @date 2014年12月30日
 */
public class BaikeActivity extends BaseActivity {

	private TitleBar mTb;
	private ListView mLvLst;

	private List<SimpleTool> mLstItems = new ArrayList<SimpleTool>();

	private List<Class<? extends Activity>> mActivitys = new ArrayList<Class<? extends Activity>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_baike);
		initCtrl();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);
		mTb.setText("游戏百科");
		mLvLst = (ListView) findViewById(R.id.lv_types);

		SimpleTool item1 = new SimpleTool(R.drawable.icon_zb, "装备");
		SimpleTool item2 = new SimpleTool(R.drawable.icon_gift, "天赋");
		SimpleTool item3 = new SimpleTool(R.drawable.icon_fuwen, "符文");
		SimpleTool item4 = new SimpleTool(R.drawable.icon_zjzr, "最佳阵容");
		SimpleTool item5 = new SimpleTool(R.drawable.icon_zhsjn, "召唤师技能");

		mLstItems.add(item1);
		mLstItems.add(item2);
		mLstItems.add(item3);
		mLstItems.add(item4);
		mLstItems.add(item5);

		mActivitys.add(MaterialTypesActivity.class);
		mActivitys.add(GiftActivity.class);
		mActivitys.add(RuneActivity.class);
		mActivitys.add(BestGroupActivity.class);
		mActivitys.add(SummonerAbilityActivity.class);

		AdapterList adapter = new AdapterList(LayoutInflater.from(this), mLstItems);

		mLvLst.setAdapter(adapter);
		mLvLst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				openNextActivity(position);
			}
		});
	}

	private void openNextActivity(int position) {

		Intent it = new Intent(this, mActivitys.get(position));
		startActivity(it);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
