package com.warren.lolbox;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;

import android.util.LruCache;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.util.LogTool;

/**
 * 全局的网络请求管理器
 * @author warren
 * @date 2014年12月30日
 */
public class AppNetManager {
	
	private static AppNetManager netManager;
	
	private LruCache<String, String> cache = new LruCache<String, String>(1000);

	/**
	 * 线程安全地取唯一实例
	 * @return
	 */
	public static AppNetManager getInstance(){
		
		if(netManager == null){
			synchronized (AppNetManager.class) {
				if(netManager == null){
					netManager = new AppNetManager();
				}
			}
		}
		return netManager;
	}
	
	private AppNetManager(){
		
	}
	
	/**
	 * 异步请求指定URL，同步回调
	 * @param strUrl 请求路径
	 * @param headers 请求头
	 * @param listener
	 */
	public void get(final String strUrl, final Map<String, String> headers, final IListener<String> listener){
		
		String strHistory = cache.get(strUrl);
		if(strHistory != null){
			listener.onCall(strHistory);
			return;
		}
		
		AsyncHttpClient httpClient = new AsyncHttpClient();
		if(headers != null && ! headers.isEmpty()){
			
			Set<Entry<String, String>> entrySet = headers.entrySet();
			for(Entry<String, String> entry : entrySet){

				httpClient.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		httpClient.get(strUrl, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				super.onSuccess(arg0, arg1, arg2);
				try {
					String strResult = new String(arg2, "UTF-8");
					cache.put(strUrl, strResult);
					listener.onCall(strResult);
					
				} catch (UnsupportedEncodingException e) {
					LogTool.exception(e);
					listener.onCall(null);
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				super.onFailure(arg0, arg1, arg2, arg3);
				listener.onCall(null);
			}
			
		});
	
	}
	
	/**
	 * 异步请求指定URL，同步回调
	 * @param strUrl
	 * @param listener
	 */
	public void get(final String strUrl, final IListener<String> listener){
		
		String strHistory = cache.get(strUrl);
		if(strHistory != null){
			listener.onCall(strHistory);
			return;
		}
		
		AsyncHttpClient httpClient = new AsyncHttpClient();
		httpClient.get(strUrl, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				super.onSuccess(arg0, arg1, arg2);
				try {
					String strResult = new String(arg2, "UTF-8");
					cache.put(strUrl, strResult);
					listener.onCall(strResult);
					
				} catch (UnsupportedEncodingException e) {
					LogTool.exception(e);
					listener.onCall(null);
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				super.onFailure(arg0, arg1, arg2, arg3);
				listener.onCall(null);
			}
			
		});
	}
	
	
}
