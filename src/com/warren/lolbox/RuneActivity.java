package com.warren.lolbox;

import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.Rune;
import com.warren.lolbox.model.bean.RuneList;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

/**
 * 符文Activity
 * @author yangsheng
 * @date 2015年3月27日
 */
public class RuneActivity extends BaseActivity {

	private ImageLoader mImgLoader;

	private Spinner mSpLevel;
	private Spinner mSpType;
	private ListView mLvList;

	private AdapterRune mAdapter;
	private RuneList mRuneLst;
	private List<Rune> mRuneCur;

	private int mLevelPos;
	private int mTypePos;

	private static final String[] mArrLevel = { "1级", "2级", "3级" };
	private static final String[] mArrType = { "印记", "雕纹", "符印", "精华" };

	private static final String[] mArrColor = { "red", "blue", "yellow", "purple" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rune);
		mImgLoader = AppContext.getApp().getImgLoader();
		initCtrl();
	}

	private void initCtrl() {
		mSpLevel = (Spinner) findViewById(R.id.sp_level);
		mSpType = (Spinner) findViewById(R.id.sp_type);
		mLvList = (ListView) findViewById(R.id.lv_rune);

		mSpLevel.setAdapter(new ArrayAdapter<String>(this, R.layout.textview_spinner, mArrLevel));
		mSpType.setAdapter(new ArrayAdapter<String>(this, R.layout.textview_spinner, mArrType));
		mAdapter = new AdapterRune();
		mLvList.setAdapter(mAdapter);

		mSpLevel.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (mLevelPos != position) {
					mLevelPos = position;
					refresh();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		mSpType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (mTypePos != position) {
					mTypePos = position;
					refresh();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		requestData();
	}

	private void requestData() {

		httpGet(URLUtil.getUrl_RuneList(), SystemConfig.getIntance().getCommHead(),
					new IListener<String>() {

						@Override
						public void onCall(String strJson) {
							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(RuneActivity.this, "获取数据失败", Toast.LENGTH_SHORT)
											.show();
								return;
							}
							String strJsonLower = strJson.toLowerCase(Locale.CHINA);
							jsonParse(strJsonLower, RuneList.class, new IListener<RuneList>() {

								@Override
								public void onCall(RuneList runeList) {
									if (runeList == null) {
										Toast.makeText(RuneActivity.this, "获取数据失败",
													Toast.LENGTH_SHORT).show();
										return;
									}
									mRuneLst = runeList;
									mLevelPos = 0;
									mTypePos = 0;
									mSpLevel.setSelection(mLevelPos);
									mSpType.setSelection(mTypePos);
									refresh();
								}
							});
						}
					});

	}

	private void refresh() {
		if (mRuneLst == null) {
			return;
		}
		mRuneCur = mRuneLst.obtainList(mArrColor[mTypePos]);
		mAdapter.notifyDataSetChanged();
	}

	class AdapterRune extends BaseAdapter {

		@Override
		public int getCount() {
			return mRuneCur == null ? 0 : mRuneCur.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(RuneActivity.this).inflate(
							R.layout.activity_rune_item, parent, false);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				holder.tvRank = (TextView) convertView.findViewById(R.id.tv_rank);
				holder.tvBenefit = (TextView) convertView.findViewById(R.id.tv_benefit);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Rune rune = mRuneCur.get(position);

			mImgLoader.displayImage(URLUtil.getUrl_RuneImg(rune.getImg(), (mLevelPos + 1)),
						holder.img);
			holder.tvName.setText(rune.getName());
			holder.tvRank.setText(mArrLevel[mLevelPos] + " 游戏币"
						+ rune.obtainExpense(mLevelPos + 1));
			holder.tvBenefit.setText(rune.obtainBenefit(mTypePos + 1) + rune.getUnits()
						+ rune.getProp());

			return convertView;
		}

		private class ViewHolder {
			public ImageView img;
			public TextView tvName;
			public TextView tvBenefit;
			public TextView tvRank;
		}

	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
