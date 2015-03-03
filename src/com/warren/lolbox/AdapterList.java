package com.warren.lolbox;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.warren.lolbox.model.SimpleTool;

public class AdapterList extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<SimpleTool> mItems;

	public AdapterList(LayoutInflater inflater, List<SimpleTool> items) {
		this.mInflater = inflater;
		this.mItems = items;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
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
		if (mItems.get(position).getImgResId() < 0) {
			holder.img.setVisibility(View.GONE);
		} else {
			holder.img.setImageResource(mItems.get(position).getImgResId());
		}
		holder.tv.setText(mItems.get(position).getStrText() == null ? mInflater.getContext()
					.getResources().getString(mItems.get(position).getTxtResId()) : mItems.get(
					position).getStrText());
		return convertView;
	}

	class ViewHolder {
		public ImageView img;
		public TextView tv;
	}

}
