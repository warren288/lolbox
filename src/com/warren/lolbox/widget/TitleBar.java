package com.warren.lolbox.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warren.lolbox.R;

/**
 * 标题栏
 * @author yangsheng
 * @date 2015年1月1日
 */
public class TitleBar extends RelativeLayout {

	private ImageView mImgLeft;
	private ImageView mImgRight;
	private TextView mTvTitle;

	private View.OnClickListener mLeftClickListener;
	private View.OnClickListener mRightClickListener;

	public TitleBar(Context context) {
		super(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	/**
	 * 初始化界面，改用xml文件
	 * @param attrs
	 */
	private void init(AttributeSet attrs) {
		LayoutInflater.from(getContext()).inflate(R.layout.titlebar, this, true);
		mImgLeft = (ImageView) findViewById(R.id.img_title_left);
		mImgRight = (ImageView) findViewById(R.id.img_title_right);
		mTvTitle = (TextView) findViewById(R.id.tv_title);

		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
		if (a.getDrawable(R.styleable.TitleBar_left_img) != null) {
			mImgLeft.setImageDrawable(a.getDrawable(R.styleable.TitleBar_left_img));
			mImgLeft.setVisibility(View.VISIBLE);
		} else {
			mImgLeft.setVisibility(View.GONE);
		}
		if (a.getDrawable(R.styleable.TitleBar_right_img) != null) {
			mImgRight.setImageDrawable(a.getDrawable(R.styleable.TitleBar_right_img));
			mImgRight.setVisibility(View.VISIBLE);
		} else {
			mImgRight.setVisibility(View.GONE);
		}
		if (a.getString(R.styleable.TitleBar_title) != null) {
			mTvTitle.setText(a.getString(R.styleable.TitleBar_title));
			mTvTitle.setVisibility(View.VISIBLE);
		} else {
			mTvTitle.setVisibility(View.GONE);
		}

		// 左键的默认操作是返回
		if (mImgLeft.getVisibility() == View.VISIBLE && getContext() instanceof Activity) {
			mLeftClickListener = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					((Activity) getContext()).finish();
				}
			};
			mImgLeft.setOnClickListener(mLeftClickListener);
		}

		a.recycle();
	}

	// private void init(AttributeSet attrs) {
	//
	// // 这里添加控件也可以使用布局文件的形式。
	// // 使用布局文件的方式为
	// // LayoutInflater.from(getContext()).inflate(layoutResId, this, true);
	// // ImageView img = findViewById(imgResId);
	//
	//
	// TypedArray a = getContext().obtainStyledAttributes(attrs,
	// R.styleable.TitleBar);
	//
	// mImgLeft = new ImageView(getContext());
	// RelativeLayout.LayoutParams mImgLeftParam = new
	// RelativeLayout.LayoutParams(
	// RelativeLayout.LayoutParams.WRAP_CONTENT,
	// RelativeLayout.LayoutParams.MATCH_PARENT);
	// mImgLeftParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	// mImgLeftParam.addRule(RelativeLayout.CENTER_VERTICAL);
	// mImgLeft.setLayoutParams(mImgLeftParam);
	// mImgLeft.setPadding(DeviceUtil.dp2Px(getContext(), 10), 0,
	// DeviceUtil.dp2Px(getContext(), 10), 0);
	// mImgLeft.setImageDrawable(a.getDrawable(R.styleable.TitleBar_left_img));
	// if(a.getDrawable(R.styleable.TitleBar_left_imgbackground) != null){
	// mImgLeft.setBackgroundDrawable(a.getDrawable(R.styleable.TitleBar_left_imgbackground));
	// }
	//
	// mImgRight = new ImageView(getContext());
	// RelativeLayout.LayoutParams mImgRightParam = new
	// RelativeLayout.LayoutParams(
	// RelativeLayout.LayoutParams.WRAP_CONTENT,
	// RelativeLayout.LayoutParams.MATCH_PARENT);
	// mImgRightParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	// mImgRightParam.addRule(RelativeLayout.CENTER_VERTICAL);
	// mImgRight.setLayoutParams(mImgRightParam);
	// mImgRight.setPadding(DeviceUtil.dp2Px(getContext(), 10), 0,
	// DeviceUtil.dp2Px(getContext(), 10), 0);
	// mImgRight.setImageDrawable(a.getDrawable(R.styleable.TitleBar_right_img));
	// if(a.getDrawable(R.styleable.TitleBar_right_imgbackground) != null){
	// mImgRight.setBackgroundDrawable(a.getDrawable(R.styleable.TitleBar_right_imgbackground));
	// }
	//
	// mTvTitle = new TextView(getContext());
	// RelativeLayout.LayoutParams mTvTitleParam = new
	// RelativeLayout.LayoutParams(
	// RelativeLayout.LayoutParams.WRAP_CONTENT,
	// RelativeLayout.LayoutParams.WRAP_CONTENT);
	// mTvTitleParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
	// mTvTitleParam.addRule(RelativeLayout.CENTER_VERTICAL);
	// mTvTitle.setLayoutParams(mTvTitleParam);
	// mTvTitle.setText(a.getString(R.styleable.TitleBar_title));
	// mTvTitle.setTextColor(a.getColor(R.styleable.TitleBar_titleColor,
	// R.color.greenblue));
	// mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,
	// a.getDimension(R.styleable.TitleBar_titleSize, 18));
	// addView(mImgLeft);
	// addView(mImgRight);
	// addView(mTvTitle);
	//
	// a.recycle();
	//
	// // 标题栏左边的按钮默认是返回功能
	// if(getContext() instanceof Activity){
	//
	// mLeftClickListener = new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// ((Activity)getContext()).finish();
	// }
	// };
	// mImgLeft.setOnClickListener(mLeftClickListener);
	// }
	//
	// }

	/**
	 * 设置左边ImageView的可见性
	 * @param visibility
	 */
	public void setLeftVisibility(int visibility) {
		this.mImgLeft.setVisibility(visibility);
	}

	/**
	 * 设置右边ImageView的可见性
	 * @param visibility
	 */
	public void setRightVisibility(int visibility) {
		this.mImgRight.setVisibility(visibility);
	}

	/**
	 * 设置左边ImageView的点击事件
	 * @param listener
	 */
	public void setLeftClick(View.OnClickListener listener) {
		this.mLeftClickListener = listener;
		this.mImgLeft.setOnClickListener(mLeftClickListener);
	}

	/**
	 * 设置右边ImageView的点击事件
	 * @param listener
	 */
	public void setRightClick(View.OnClickListener listener) {
		this.mRightClickListener = listener;
		this.mImgRight.setOnClickListener(mRightClickListener);
	}

	/**
	 * 标题栏文字
	 * @param strText
	 */
	public void setText(String strText) {
		this.mTvTitle.setVisibility(View.VISIBLE);
		this.mTvTitle.setText(strText);
	}

	/**
	 * 标题栏文字
	 * @param strResId
	 */
	public void setText(int strResId) {
		this.mTvTitle.setText(strResId);
	}

}
