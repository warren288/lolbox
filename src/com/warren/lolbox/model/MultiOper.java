package com.warren.lolbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 多次点击操作封装类
 * @author yangsheng
 * @date 2015年3月8日
 */
public class MultiOper {

	public static final long TIME_GAP = 2 * 1000;

	private long lastTime = -1;
	private int keyCode;

	private List<IListener<Integer>> lstListener = new ArrayList<IListener<Integer>>();

	/**
	 * 连续两次点击控制器
	 * @param keyCode 点击的按钮ID
	 */
	public MultiOper(int keyCode) {
		this.keyCode = keyCode;
	}

	/**
	 * 注册监听
	 * @param listener	监听器的回调方法中的参数是连续点击的次数，1/2
	 * @return
	 */
	public MultiOper registerListener(IListener<Integer> listener) {
		if (listener == null) {
			return this;
		}
		lstListener.add(listener);
		return this;
	}

	/**
	 * 取消监听
	 * @param listener
	 * @return
	 */
	public MultiOper unregisterListener(IListener<Long> listener) {
		lstListener.remove(listener);
		return this;
	}

	/**
	 * 取消所有监听
	 * @return
	 */
	public MultiOper unregisterAllListener() {
		lstListener.clear();
		return this;
	}

	/**
	 * 执行指定操作，只有在  keyCode 与 构造方法中的 参数相同时，才能有效
	 * @param keyCode
	 * @return
	 */
	public MultiOper executeOper(int keyCode) {

		if (keyCode != this.keyCode) {
			return this;
		}
		long now = new Date().getTime();
		if (lastTime < 0 || now - lastTime > TIME_GAP) {
			lastTime = now;
			for (IListener<Integer> listener : lstListener) {
				listener.onCall(1);
			}
		} else {
			if (lstListener != null && lstListener.size() > 0) {
				for (IListener<Integer> listener : lstListener) {
					listener.onCall(2);
				}
			}
		}
		return this;
	}

}
