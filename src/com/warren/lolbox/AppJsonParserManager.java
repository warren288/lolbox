package com.warren.lolbox;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.AsyncTask;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.util.LogTool;

/**
 * Json解析管理器
 * @author warren
 * @date 2014年12月31日
 */
public class AppJsonParserManager {

	private static AppJsonParserManager jpm;

	public static AppJsonParserManager getInstance() {

		if (jpm == null) {
			synchronized (AppJsonParserManager.class) {
				if (jpm == null) {
					jpm = new AppJsonParserManager();
				}
			}
		}
		return jpm;
	}

	private AppJsonParserManager() {

	}

	/**
	 * 异步解析Json，同步回调。解析成功则回调方法中的参数是解析结果，否则是 null
	 * @param strJson
	 * @param cls
	 * @param listener
	 */
	public <T> void parse(final String strJson, final Class<T> cls, final IListener<T> listener) {

		new AsyncTaskParser<T>(listener, cls).execute(strJson);
	}

	/**
	 * 异步解析列表Json，同步回调。解析成功则回调方法中的参数是解析结果，否则是 null。
	 * @param strJson
	 * @param cls
	 * @param listener
	 */
	public <T> void parseList(final String strJson, final Class<T> cls,
				final IListener<List<T>> listener) {

		new AsyncTaskParserList<T>(cls, listener).execute(strJson);
	}

	/**
	 * 异步解析列表Json，同步回调
	 * @author warren
	 * @date 2014年12月31日
	 * @param <T>
	 */
	class AsyncTaskParserList<T> extends AsyncTask<String, Integer, List<T>> {

		private Class<T> cls;
		private IListener<List<T>> listener;

		public AsyncTaskParserList(Class<T> cls, IListener<List<T>> listener) {
			this.listener = listener;
			this.cls = cls;
		}

		@Override
		protected List<T> doInBackground(String... params) {

			StringReader sr = new StringReader(params[0]);

			List<T> lst = new ArrayList<T>();
			try {
				sr.reset();
				// 解析成List时，无法直接解析成指定类型的列表，所以需要把解析得到的List<HashMap<String,
				// Object>>每一项单独再转成指定类型的对象。
				JsonFactory factory = new ObjectMapper().getJsonFactory();
				JsonParser jpar = factory.createJsonParser(sr);
				List<HashMap<String, Object>> mapLst = jpar.readValueAs(List.class);
				for (HashMap<String, Object> map : mapLst) {

					// 先将Map转换为Json字符串，再把Json字符串重新转换为指定类型的对象。
					StringWriter sw = new StringWriter();
					factory.createJsonGenerator(sw).writeObject(map);
					T t = factory.createJsonParser(sw.toString()).readValueAs(cls);
					lst.add(t);
					sw.close();
				}

			} catch (Exception e) {
				LogTool.exception(e);
			}
			sr.close();
			return lst;
		}

		@Override
		protected void onPostExecute(List<T> result) {
			super.onPostExecute(result);
			listener.onCall(result);
		}

	}

	/**
	 * 异步解析Json，同步回调
	 * @author warren
	 * @date 2014年12月31日
	 * @param <T>
	 */
	class AsyncTaskParser<T> extends AsyncTask<String, Integer, T> {

		private IListener<T> listener;;
		private Class<T> cls;

		public AsyncTaskParser(IListener<T> listener, Class<T> cls) {
			this.listener = listener;
			this.cls = cls;
		}

		@Override
		protected T doInBackground(String... params) {

			StringReader sr = new StringReader(params[0]);
			T obj = null;
			try {
				sr.reset();
				JsonParser jpar = new ObjectMapper().getJsonFactory().createJsonParser(sr);
				obj = jpar.readValueAs(cls);

			} catch (Exception e) {
				LogTool.exception(e);
			}
			sr.close();
			return obj;
		}

		@Override
		protected void onPostExecute(T result) {
			super.onPostExecute(result);
			listener.onCall(result);
		}
	}
}
