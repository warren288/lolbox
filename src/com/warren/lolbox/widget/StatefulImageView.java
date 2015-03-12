package com.warren.lolbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.R;

/**
 * 带状态的ImageView
 * @author yangsheng
 * @date 2015年3月12日
 */
public class StatefulImageView extends ImageView {

	private Drawable mDrawableForeground;

	private boolean mStateEnable = false;
	private boolean mShowState = false;

	public StatefulImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public StatefulImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public StatefulImageView(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext()
						.obtainStyledAttributes(attrs, R.styleable.StatefulImageView);
			mStateEnable = a.getBoolean(R.styleable.StatefulImageView_state_enable, false);
			mDrawableForeground = a.getDrawable(R.styleable.StatefulImageView_state_foreground);
			a.recycle();
		}
		if (mDrawableForeground == null) {
			mDrawableForeground = new ColorDrawable(getContext().getResources().getColor(
						android.R.color.darker_gray));
		}
	}

	/**
	 * 设置前景颜色
	 * @param color
	 */
	public void setForegroundColor(int color) {
		mDrawableForeground = new ColorDrawable(color);
	}

	/**
	 * 设置前景视图
	 * @param drawable
	 */
	public void setForegroundDrawable(Drawable drawable) {
		mDrawableForeground = drawable;
	}

	/**
	 * 设置是否激活状态功能
	 * @param enable
	 */
	public void setStateEnable(boolean enable) {
		mStateEnable = enable;
	}

	/**
	 * 设置当前状态
	 * @param bShow 是否开启前景
	 */
	public void setShowState(boolean bShow) {
		if (mStateEnable) {
			mShowState = bShow;
			invalidate();
		}
	}

	/**
	 * 显示图片
	 * @param imgLoader
	 * @param strUrl
	 */
	public void displayImage(ImageLoader imgLoader, String strUrl) {
		imgLoader.displayImage(strUrl, this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mStateEnable && mShowState && mDrawableForeground != null) {
			mDrawableForeground.setBounds(0, 0, getWidth(), getHeight());
			mDrawableForeground.setAlpha(150);
			mDrawableForeground.draw(canvas);
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

}
