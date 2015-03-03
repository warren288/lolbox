package com.warren.lolbox.model;

/**
 * 基础监听器接口
 * @author yangsheng
 * @date 2015年2月24日
 * @param <T>
 */
public interface IListener<T> {
	public void onCall(T t);
}
