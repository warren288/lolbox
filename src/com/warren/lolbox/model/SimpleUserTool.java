package com.warren.lolbox.model;

/**
 * 简单工具，持有一个字符串和一个回调接口
 * @author yangsheng
 * @date 2015年3月13日
 */
public class SimpleUserTool {

	private String name;
	private IListener<String> callBack;

	public SimpleUserTool(String name, IListener<String> callBack) {
		super();
		this.name = name;
		this.callBack = callBack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IListener<String> getCallBack() {
		return callBack;
	}

	public void setCallBack(IListener<String> callBack) {
		this.callBack = callBack;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof SimpleUserTool) {
			SimpleUserTool utO = (SimpleUserTool) o;
			if (utO.getName().equals(this.name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 重写，使用 {@link #name} 作为唯一标志
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
