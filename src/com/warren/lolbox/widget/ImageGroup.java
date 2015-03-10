package com.warren.lolbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.R;
import com.warren.lolbox.model.IListener;

/**
 * 用于展示一组图片，并支持每个图片的点击事件
 * @author yangsheng
 * @date 2015年3月10日
 */
public class ImageGroup extends LinearLayout {

	private static final int DEFAULT_HEIGHT = 30;
	private static final int DEFAULT_COUNT = 0;

	private ImageView[] mArrImg;
	private int mImgCount;
	private LayoutParams mImgParam;

	private IListener<Integer> mClickListener;

	public ImageGroup(Context context) {
		super(context);
	}

	public ImageGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		initCtrl(attrs);
	}

	public ImageGroup(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initCtrl(attrs);
	}

	private void initCtrl(AttributeSet attrs) {

		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageGroup);
		mImgCount = a.getInteger(R.styleable.ImageGroup_img_count, DEFAULT_COUNT);
		mImgParam = new LayoutParams(a.getDimensionPixelSize(R.styleable.ImageGroup_img_height,
					DEFAULT_HEIGHT), a.getDimensionPixelSize(R.styleable.ImageGroup_img_height,
					DEFAULT_HEIGHT));
		a.recycle();

		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mArrImg = new ImageView[mImgCount];
		for (int i = 0; i < mImgCount; i++) {
			mArrImg[i] = new ImageView(getContext());
			mArrImg[i].setLayoutParams(mImgParam);
			mArrImg[i].setPadding(2, 2, 2, 2);
			addView(mArrImg[i]);
			final int j = i;
			mArrImg[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mClickListener != null) {
						mClickListener.onCall(j);
					}
				}
			});
		}
	}

	public void setOnClickListener(IListener<Integer> listener) {
		this.mClickListener = listener;
	}

	public void displayImage(ImageLoader imgLoader, String[] arrImgUrl) {

		int countUrl = arrImgUrl.length;
		int countUseful = countUrl > mImgCount ? mImgCount : countUrl;

		for (int i = 0; i < countUseful; i++) {
			imgLoader.displayImage(arrImgUrl[i], mArrImg[i]);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
