package com.warren.lolbox.model.bean;

import java.util.List;

/**
 * 符文列表
 * @author yangsheng
 * @date 2015年3月27日
 */
public class RuneList {
	private List<Rune> red;
	private List<Rune> yellow;
	private List<Rune> blue;
	private List<Rune> purple;

	public RuneList() {
		super();
	}

	/**
	 * @return the red
	 */
	public List<Rune> getRed() {
		return red;
	}

	/**
	 * @param red the red to set
	 */
	public void setRed(List<Rune> red) {
		this.red = red;
	}

	/**
	 * @return the yellow
	 */
	public List<Rune> getYellow() {
		return yellow;
	}

	/**
	 * @param yellow the yellow to set
	 */
	public void setYellow(List<Rune> yellow) {
		this.yellow = yellow;
	}

	/**
	 * @return the blue
	 */
	public List<Rune> getBlue() {
		return blue;
	}

	/**
	 * @param blue the blue to set
	 */
	public void setBlue(List<Rune> blue) {
		this.blue = blue;
	}

	/**
	 * @return the purple
	 */
	public List<Rune> getPurple() {
		return purple;
	}

	/**
	 * @param purple the purple to set
	 */
	public void setPurple(List<Rune> purple) {
		this.purple = purple;
	}

	/**
	 * 取指定颜色的符文列表
	 * @param strColor
	 * @return
	 */
	public List<Rune> obtainList(String strColor) {
		if ("red".equalsIgnoreCase(strColor)) {
			return red;
		} else if ("yellow".equalsIgnoreCase(strColor)) {
			return yellow;
		} else if ("blue".equalsIgnoreCase(strColor)) {
			return blue;
		} else {
			return purple;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RuneList [red=" + red + ", yellow=" + yellow + ", blue=" + blue + ", purple="
					+ purple + "]";
	}

}
