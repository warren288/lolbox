package com.warren.lolbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
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

	private StatefulImageView[] mArrImg;
	private int mImgCount;
	private int mImgUsefulCount;
	private boolean mStateEnable = false;
	private Drawable mDrawableForeground;
	
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
		mStateEnable = a.getBoolean(R.styleable.ImageGroup_img_state_enable, false);
		mDrawableForeground = a.getDrawable(R.styleable.ImageGroup_img_state_foreground);
		a.recycle();
		
		mImgParam = new LayoutParams(a.getDimensionPixelSize(R.styleable.ImageGroup_img_height,
					DEFAULT_HEIGHT), a.getDimensionPixelSize(R.styleable.ImageGroup_img_height,
					DEFAULT_HEIGHT));

		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mArrImg = new StatefulImageView[mImgCount];
		for (int i = 0; i < mImgCount; i++) {
			mArrImg[i] = new StatefulImageView(getContext());
			mArrImg[i].setLayoutParams(mImgParam);
			mArrImg[i].setPadding(2, 2, 2, 2);
			mArrImg[i].setStateEnable(mStateEnable);
			if(mDrawableForeground != null){
				mArrImg[i].setForegroundDrawable(mDrawableForeground);
			}
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

	/**
	 * 设置图片点击事件
	 * @param listener 回调方法中的参数是对应ImageView在该视图中的序号
	 */
	public void setOnClickListener(IListener<Integer> listener) {
		this.mClickListener = listener;
	}

	/**
	 * 显示图片
	 * @param imgLoader
	 * @param arrImgUrl	图片Url列表
	 */
	public void displayImage(ImageLoader imgLoader, String[] arrImgUrl) {

		int countUrl = arrImgUrl.length;
		mImgUsefulCount = countUrl > mImgCount ? mImgCount : countUrl;
		for (int i = 0; i < mImgUsefulCount; i++) {
			mArrImg[i].displayImage(imgLoader, arrImgUrl[i]);
		}
		for(int i = mImgUsefulCount; i < mImgCount; i++){
			mArrImg[i].setVisibility(View.GONE);
		}
	}
	
	/**
	 * 设置本视图中各ImageView的状态
	 * @param arrState
	 */
	public void setImageStates(boolean[] arrState){
		if(arrState == null || arrState.length != mImgUsefulCount){
			return;
		}
		for(int i = 0; i < mImgUsefulCount; i++){
			mArrImg[i].setShowState(arrState[i]);
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
