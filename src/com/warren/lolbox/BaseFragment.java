package com.warren.lolbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;

import com.warren.lolbox.model.IBaseFragment;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.util.StringUtils;

public abstract class BaseFragment extends Fragment implements IBaseFragment {

	/**
	 * 异步访问指定路径，然后再异步解析Json
	 * @param strUrl
	 * @param headers
	 * @param cls
	 * @param listener
	 */
	public <T> void httpGetAndParse(final String strUrl, final Map<String, String> headers,
				final Class<T> cls, final IListener<T> listener) {
		httpGet(strUrl, headers, new IListener<String>() {

			@Override
			public void onCall(String strResult) {
				if (StringUtils.isNullOrZero(strResult)) {
					listener.onCall(null);
					return;
				}
				jsonParse(strResult, cls, listener);
			}
		});
	}

	/**
	 * 异步访问指定路径，然后再异步解析Json，解析为列表
	 * @param strUrl
	 * @param headers
	 * @param cls
	 * @param listener
	 */
	public <T> void httpGetAndParseList(final String strUrl, final Map<String, String> headers,
				final Class<T> cls, final IListener<List<T>> listener) {
		httpGet(strUrl, headers, new IListener<String>() {

			@Override
			public void onCall(String strResult) {
				if (StringUtils.isNullOrZero(strResult)) {
					listener.onCall(null);
					return;
				}
				jsonParseList(strResult, cls, listener);
			}
		});
	}

	/**
	 * 异步访问指定路径
	 * @param strUrl
	 * @param headers
	 * @param listener
	 */
	public void httpGet(final String strUrl, final Map<String, String> headers,
				final IListener<String> listener) {
		AppContext.getApp().getNetManager().get(strUrl, headers, listener);
	}

	/**
	 * 异步解析指定Json
	 * @param strJson
	 * @param cls
	 * @param listener
	 */
	public <T> void jsonParse(final String strJson, final Class<T> cls, final IListener<T> listener) {
		AppContext.getApp().getJsonManager().parse(strJson, cls, listener);
	}

	/**
	 * 异步解析指定Json
	 * @param strJson
	 * @param cls
	 * @param listener
	 */
	public <T> void jsonParseList(final String strJson, final Class<T> cls,
				final IListener<List<T>> listener) {
		AppContext.getApp().getJsonManager().parseList(strJson, cls, listener);
	}

	/**
	 * 异步解析指定Json
	 * @param strJson
	 * @param listener
	 */
	public <T> void jsonParseMap(final String strJson,
				final IListener<Map<String, HashMap<String, Object>>> listener) {

		AppContext.getApp().getJsonManager().parseMap(strJson, listener);
	}
}
