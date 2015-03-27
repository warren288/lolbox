package com.warren.lolbox.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆点指示器
 * @author yangsheng
 * @date 2015年3月27日
 */
public class SmallDotIndicator extends View {

	private int mCount;
	private int mCurIndex;

	private Paint paintNormal;
	private Paint paintSelect;

	public SmallDotIndicator(Context context) {
		super(context);
		init();
	}

	public SmallDotIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		paintNormal = new Paint();
		paintNormal.setColor(getContext().getResources().getColor(android.R.color.darker_gray));
		paintNormal.setAntiAlias(true);
		paintSelect = new Paint();
		paintSelect.setColor(getContext().getResources().getColor(android.R.color.white));
		paintSelect.setAntiAlias(true);
	}

	public void setCount(int count) {
		this.mCount = count;
	}

	public void setCurrentIndex(int currentIndex) {
		mCurIndex = currentIndex;
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mCount <= 0) {
			return;
		}
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		int dotradius = width / (4 * mCount);

		for (int index = 0; index < mCount; index++) {
			if (index == mCurIndex) {
				canvas.drawCircle(dotradius * (index * 4 + 2), height / 2, dotradius, paintSelect);
			} else {
				canvas.drawCircle(dotradius * (index * 4 + 2), height / 2, dotradius, paintNormal);
			}
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
