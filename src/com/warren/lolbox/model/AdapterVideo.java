package com.warren.lolbox.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.R;
import com.warren.lolbox.model.bean.Video;
import com.warren.lolbox.util.LogTool;

/**
 * 视频列表Adapter
 * @author yangsheng
 * @date 2015年1月31日
 */
public class AdapterVideo extends BaseAdapter {

	private List<Video> mLstData;
	private ImageLoader mImgLoader;
	private LayoutInflater mInflater;
	
	private SimpleDateFormat mSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);

	public AdapterVideo(Context context, ImageLoader imgLoader) {
		this.mInflater = LayoutInflater.from(context);
		this.mImgLoader = imgLoader;
	}

	public void setData(List<Video> lstData) {
		this.mLstData = lstData;
	}

	@Override
	public int getCount() {
		return this.mLstData == null ? 0 : this.mLstData.size();
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
			convertView = mInflater.inflate(R.layout.videoitem, parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.img_video);
			holder.title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.length = (TextView) convertView.findViewById(R.id.tv_length);
			holder.time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.imgDown = (ImageView) convertView.findViewById(R.id.img_download);
			holder.imgDown.setOnClickListener(new DownClickListener(holder));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.imgDown.setTag(position);
		
		Video video = mLstData.get(position);
		mImgLoader.displayImage(video.getCover_url(), holder.img);
		holder.title.setText(video.getTitle());

		int length = Integer.parseInt(video.getVideo_length());
		holder.length.setText("" + length / 60 + "-" + length % 60);

		try {
			Date date = mSdf.parse(video.getUpload_time());
			holder.time.setText( "" + (date.getMonth() + 1) + date.getDate());
		} catch (ParseException e) {
			LogTool.exception(e);
		}
		
		return convertView;
	}
	
	class DownClickListener implements View.OnClickListener{

		private ViewHolder holder;
		
		public DownClickListener(ViewHolder holder){
			this.holder = holder;
		}
		
		@Override
		public void onClick(View v) {
			int position = (Integer) holder.imgDown.getTag();
			
		}
		
	}
	
	class ViewHolder {
		ImageView img;
		TextView title;
		TextView length;
		TextView time;
		ImageView imgDown;
	}

}
