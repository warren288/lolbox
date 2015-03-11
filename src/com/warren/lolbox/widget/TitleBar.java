package com.warren.lolbox.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warren.lolbox.R;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.util.DeviceUtil;

/**
 * 标题栏
 * @author yangsheng
 * @date 2015年1月1日
 */
public class TitleBar extends RelativeLayout {

	private static final String SPLIT_OPER = "\\|";

	private ImageView mImgLeft;
	private ImageView mImgRight;
	private TextView mTvTitle;

	private View.OnClickListener mLeftClickListener;
	private View.OnClickListener mRightClickListener;
	private View.OnClickListener mTitleClickListener;

	private IListener<String> mListenerOper;

	private LinearLayout mLlRightOpers;
	private List<TextView> mLstRightOperViews;
	private List<String> mLstRightOpers;

	android.widget.LinearLayout.LayoutParams mParamTv;

	// private LayoutParams mParamRightOpersNormal;
	// private LayoutParams mParamRightOpersNoRightImg;

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
		mLlRightOpers = (LinearLayout) findViewById(R.id.ll_opers);

		// mParamRightOpersNormal = new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.MATCH_PARENT);
		// mParamRightOpersNormal.addRule(RelativeLayout.LEFT_OF,
		// R.id.img_title_right);
		//
		// mParamRightOpersNoRightImg = new
		// LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.MATCH_PARENT);
		// mParamRightOpersNoRightImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
		if (a.getDrawable(R.styleable.TitleBar_left_img) != null) {
			mImgLeft.setImageDrawable(a.getDrawable(R.styleable.TitleBar_left_img));
			mImgLeft.setVisibility(View.VISIBLE);
		} else {
			mImgLeft.setVisibility(View.INVISIBLE);
		}
		if (a.getDrawable(R.styleable.TitleBar_right_img) != null) {
			mImgRight.setImageDrawable(a.getDrawable(R.styleable.TitleBar_right_img));
			mImgRight.setVisibility(View.VISIBLE);
		} else {
			mImgRight.setVisibility(View.INVISIBLE);
		}
		if (a.getString(R.styleable.TitleBar_title) != null) {
			mTvTitle.setText(a.getString(R.styleable.TitleBar_title));
			mTvTitle.setVisibility(View.VISIBLE);
		} else {
			mTvTitle.setVisibility(View.INVISIBLE);
		}

		mParamTv = new android.widget.LinearLayout.LayoutParams(
					android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
					android.widget.LinearLayout.LayoutParams.MATCH_PARENT);

		String strRightOper = a.getString(R.styleable.TitleBar_rightopers);
		setRightOpers(strRightOper);

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

		// if (mImgRight.getVisibility() == View.VISIBLE) {
		// mLlRightOpers.setLayoutParams(mParamRightOpersNormal);
		// } else {
		// mLlRightOpers.setLayoutParams(mParamRightOpersNoRightImg);
		// }
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
	 * 设置右边操作
	 * @param strRightOper
	 */
	public void setRightOpers(String strRightOper) {

		mLstRightOperViews = new ArrayList<TextView>();
		mLlRightOpers.removeAllViews();

		if (strRightOper != null && strRightOper.length() > 0) {
			mLstRightOpers = Arrays.asList(strRightOper.split(SPLIT_OPER));
			for (String strOper : mLstRightOpers) {
				TextView tv = createOperTextView(strOper);
				mLstRightOperViews.add(tv);
				mLlRightOpers.addView(tv);
				final String strFinal = strOper;
				tv.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (mListenerOper != null) {
							mListenerOper.onCall(strFinal);
						}
					}
				});
			}
		}
	}

	private TextView createOperTextView(String strOper) {

		TextView tv = new TextView(getContext());
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setLayoutParams(mParamTv);
		tv.setPadding(DeviceUtil.dp2Px(getContext(), 2), 0, DeviceUtil.dp2Px(getContext(), 2), 0);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setTextColor(getResources().getColor(R.color.greenblue));
		tv.setBackgroundResource(R.drawable.bg_btn_title);
		tv.setText(strOper);
		return tv;
	}

	/**
	 * 设置右边操作
	 * @param listener
	 */
	public void setRightOperListener(IListener<String> listener) {
		this.mListenerOper = listener;
	}

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
		// if (visibility == View.VISIBLE) {
		// mLlRightOpers.setLayoutParams(mParamRightOpersNormal);
		// } else {
		// mLlRightOpers.setLayoutParams(mParamRightOpersNoRightImg);
		// }
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
	 * 设置标题栏标题的点击事件
	 * @param listener
	 */
	public void setTitleClick(View.OnClickListener listener) {
		this.mTitleClickListener = listener;
		this.mTvTitle.setOnClickListener(mTitleClickListener);
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
