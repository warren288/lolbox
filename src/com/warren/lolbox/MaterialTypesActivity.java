package com.warren.lolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.warren.lolbox.url.DuowanConfig.EnumZBType;
import com.warren.lolbox.widget.TitleBar;

/**
 * 物品分类
 * @author warren
 * @date 2014年12月31日
 */
public class MaterialTypesActivity extends BaseActivity {

	private TitleBar mTb;
	private ListView mLvLst;

	private String[] mArrTypes = EnumZBType.getStringTypeArray();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baike);
		initCtrl();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);
		mLvLst = (ListView) findViewById(R.id.lv_types);
		
		mTb.setText("物品分类");
		
		AdapterList adapter = new AdapterList(LayoutInflater.from(this), mArrTypes);

		mLvLst.setAdapter(adapter);
		mLvLst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				openNextActivity(position);
			}
		});
	}

	/**
	 * 打开物品列表Activity
	 * @param position
	 */
	private void openNextActivity(int position) {

		Intent it = new Intent(this, MaterialGridWithTypeActivity.class);

		it.putExtra(MaterialGridWithTypeActivity.EXTRA_ZBTYPE, mArrTypes[position]);
		startActivity(it);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	class AdapterList extends BaseAdapter {

		private LayoutInflater mInflater;
		private String[] mArrTypes;

		public AdapterList(LayoutInflater inflater, String[] arrType) {
			this.mInflater = inflater;
			this.mArrTypes = arrType;
		}

		@Override
		public int getCount() {
			return mArrTypes.length;
		}

		@Override
		public Object getItem(int position) {
			return mArrTypes[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.activity_baike_listitem, parent, false);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.img.setVisibility(View.GONE);
			holder.tv.setText(mArrTypes[position]);
			return convertView;
		}

		class ViewHolder {
			public ImageView img;
			public TextView tv;
		}

	}

	@Override
	protected boolean goBack() {
		return false;
	}
}
