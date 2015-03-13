package com.warren.lolbox.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.warren.lolbox.util.BitmapUtil;

/**
 * 异步保存图片
 * @author yangsheng
 * @date 2015年2月11日
 */
public class AsyncSaveBitmap extends AsyncTask<Bitmap, Integer, Boolean> {

	private String strPath;
	private IListener<Boolean> listener;
	
	public AsyncSaveBitmap(String strPath){
		this.strPath = strPath;
	}
	
	@Override
	protected Boolean doInBackground(Bitmap... params) {

		Bitmap bitmap = params[0];
		return BitmapUtil.saveToFile(bitmap, strPath);
	}

	@Override
	protected void onPostExecute(Boolean result) {

		if(listener != null){
			listener.onCall(result);
		}
		super.onPostExecute(result);
	}
	
	public AsyncSaveBitmap registerListener(IListener<Boolean> listener){
		this.listener = listener;
		return this;
	}
}
