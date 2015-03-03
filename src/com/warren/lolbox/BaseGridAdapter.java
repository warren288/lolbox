package com.warren.lolbox;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.SimpleNetTool;
import com.warren.lolbox.model.SimpleTool;

/**
 * 通用的GridView的Adapter，支持 {@link SimpleTool} 和 {@link SimpleNetTool}列表的显示
 * @author yangsheng
 * @date 2014年12月31日
 */
public class BaseGridAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<SimpleTool> lstTools;
	private List<SimpleNetTool> lstNetTools;
	private ImageLoader imgLoader;
	private DisplayImageOptions options;

	private int numColumn = 3;

	public BaseGridAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	public void setLstTools(List<SimpleTool> lstTools) {
		this.lstTools = lstTools;
	}

	public void setLstNetTools(List<SimpleNetTool> lstNetTools, ImageLoader imgLoader, DisplayImageOptions options) {
		this.lstNetTools = lstNetTools;
		this.imgLoader = imgLoader;
		this.options = options;
	}

	/**
	 * @return the numColumn
	 */
	public int getNumColumn() {
		return numColumn;
	}

	/**
	 * @param numColumn the numColumn to set
	 */
	public void setNumColumn(int numColumn) {
		this.numColumn = numColumn;
	}

	@Override
	public int getCount() {
		return lstTools != null ? lstTools.size() : (lstNetTools != null ? lstNetTools.size() : 0);
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
			convertView = inflater.inflate(R.layout.griditem, parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 优先显示 lstTools，如果 lstTools 为设置，则显示 lstNetTools
		if (lstTools != null) {
			holder.img.setImageResource(lstTools.get(position).getImgResId());
			holder.tv.setText(lstTools.get(position).getTxtResId());

		} else {
			if(options != null){
				imgLoader.displayImage(lstNetTools.get(position).getStrImgUrl(), holder.img, options);
			} else {
				imgLoader.displayImage(lstNetTools.get(position).getStrImgUrl(), holder.img);
			}
			
			holder.tv.setText(lstNetTools.get(position).getStrText());
		}

		AbsListView.LayoutParams param = new AbsListView.LayoutParams(
					parent.getWidth() / numColumn, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		convertView.setLayoutParams(param);

		return convertView;
	}

	class ViewHolder {
		ImageView img;
		TextView tv;
	}

}
