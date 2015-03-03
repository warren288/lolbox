package com.warren.lolbox.model;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

public class GridParams {
	
	public int rootWidth = ViewGroup.LayoutParams.MATCH_PARENT;
	public int rootHeight = ViewGroup.LayoutParams.MATCH_PARENT;
	public int imgWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
	public int imgHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
	public ScaleType scaleType = ScaleType.FIT_CENTER;
	
	public boolean fillParentHeight = false;
	public boolean imgFillRootWidth = false;
	
	public int txtColor = Color.GREEN;
	public int txtSize = 12;
	
	
	
	public static GridParams createNormal(){
		return new GridParams();
	}
	
}
