package com.warren.lolbox;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * 基础Activity，所有Activity均应继承该类
 * @author yangsheng
 * @date 2015年2月24日
 */
public abstract class BaseActivity extends Activity {

	private static Map<String, Object> arguments = new HashMap<String, Object>();

	/**
	 * Activity间传递参数用。添加参数，为避免覆盖，使用该方法时应确保参数 strExtraName 的唯一性
	 * @param strExtraName
	 * @param objExtra
	 * @return
	 */
	protected static Object putArgument(String strExtraName, Object objExtra) {
		return arguments.put(strExtraName, objExtra);
	}

	/**
	 * Activity间传递参数用。取参数，一旦成功取出参数对应的对象，该键值对将从参数Map中移除
	 * @param strExtraName
	 * @param objDefault
	 * @return
	 */
	protected static Object getArgument(String strExtraName, Object objDefault) {
		Object obj = arguments.get(strExtraName);
		if (obj != null) {
			arguments.remove(strExtraName);
		} else {
			obj = objDefault;
		}
		return obj;
	}

	/**
	 * 返回操作。
	 * @description {@link #BaseActivity()} 的 {@link #onKeyDown(int, KeyEvent)} 中重写了返回键的事件处理，先判断
	 * 本方法的返回值，true 表示返回操作处理完毕；false表示返回操作尚未处理完毕，将继续执行  {@link Activity}的返回键事件。
	 * @return
	 */
	protected abstract boolean goBack();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (goBack()) {
				return true;
			} else {
				return super.onKeyDown(keyCode, event);
			}
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 默认使用切换动画 android.R.anim.slide_in_left, android.R.anim.slide_out_right
	 */
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
	
	/**
	 * 使用指定的切换动画
	 * @param intent
	 * @param animIn
	 * @param animOut
	 */
	public void startActivity(Intent intent, int animIn, int animOut){
		super.startActivity(intent);
		if(animIn < 0){
			animIn = android.R.anim.slide_in_left;
		}
		if(animOut < 0){
			animOut = android.R.anim.slide_out_right;
		}
		overridePendingTransition(animIn, animOut);
	}
}
