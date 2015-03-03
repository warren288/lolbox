package com.warren.lolbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.warren.lolbox.R;

public class ImageChooser extends LinearLayout {

	private FrameLayout[] mArrFrameLayout;
	private ImageView[] mArrImageView;
	private View[] mArrViewForeground;

	private int mCount;
	private int mImgWidth;
	private int mImgHeight;
	private Drawable mDrawableForeGroundSelect;
	private Drawable mDrawableForeGroundUnSelect;
	
	private OnImageClickListener mClickListener;
	
	private int mIndexBefore;
	private int mIndexCurrent;

	public ImageChooser(Context context) {
		super(context);
	}

	public ImageChooser(Context context, AttributeSet attrs) {
		super(context);
		init(attrs);
	}

	public ImageChooser(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(AttributeSet attrs) {

		setOrientation(LinearLayout.HORIZONTAL);
		
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageChooser);

		mDrawableForeGroundSelect = a.getDrawable(R.styleable.ImageChooser_foreground_choosed);
		mDrawableForeGroundUnSelect = a.getDrawable(R.styleable.ImageChooser_foreground_unchoosed);
		mImgWidth = (int) a.getDimension(R.styleable.ImageChooser_img_width, 40);
		mImgHeight = (int) a.getDimension(R.styleable.ImageChooser_img_height, 40);
		mCount = a.getInteger(R.styleable.ImageChooser_count, 5);

		mArrFrameLayout = new FrameLayout[mCount];
		mArrImageView = new ImageView[mCount];
		mArrViewForeground = new View[mCount];

		for (int i = 0; i < mCount; i++) {

			mArrFrameLayout[i] = new FrameLayout(getContext());
			mArrFrameLayout[i]
						.setLayoutParams(new LinearLayout.LayoutParams(mImgWidth, mImgHeight));
			mArrFrameLayout[i].setPadding(1, 0, 1, 0);

			mArrImageView[i] = new ImageView(getContext());
			mArrImageView[i].setLayoutParams(new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.MATCH_PARENT,
						FrameLayout.LayoutParams.MATCH_PARENT));

			mArrViewForeground[i] = new View(getContext());
			mArrViewForeground[i].setLayoutParams(new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.MATCH_PARENT,
						FrameLayout.LayoutParams.MATCH_PARENT));
			if (mDrawableForeGroundUnSelect == null) {
				mArrViewForeground[i].setBackgroundDrawable(getContext().getResources()
							.getDrawable(R.color.halftransparent_white));
			} else {
				mArrViewForeground[i].setBackgroundDrawable(mDrawableForeGroundUnSelect);
			}
			mArrFrameLayout[i].addView(mArrImageView[i]);
			mArrFrameLayout[i].addView(mArrViewForeground[i]);
			addView(mArrFrameLayout[i]);
		}
		a.recycle();
	}
	
	public ImageView[] getImageViews(){
		return mArrImageView;
	}
	
	/**
	 * 设置选中项
	 * @param index
	 */
	public void setSelction(int index){
		
		if(index < 0 || index > mCount){
			return;
		}
		if(mClickListener != null){
			mClickListener.onClick(mIndexCurrent, index);
		}
		mIndexBefore = mIndexCurrent;
		mIndexCurrent = index;
		
		mArrViewForeground[mIndexCurrent].setVisibility(View.GONE);
		mArrViewForeground[mIndexBefore].setVisibility(View.VISIBLE);
	}
	
	/**
	 * 设置点击事件
	 * @param listenr
	 */
	public void setOnItemClickListener(OnImageClickListener listenr){
		
		this.mClickListener = listenr;
		
		for(int i = 0; i < mCount; i++){
			
			final int index = i;
			
			mArrFrameLayout[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setSelction(index);
				}
			});
		}
	}
	
	/**
	 * 点击事件
	 * @author yangsheng
	 * @date 2015年1月1日
	 */
	public static interface OnImageClickListener{
		/**
		 * 
		 * @param before 点击之前的选中项
		 * @param now 点击之后的选中项
		 */
		public void onClick(int before, int now);
	}

}
