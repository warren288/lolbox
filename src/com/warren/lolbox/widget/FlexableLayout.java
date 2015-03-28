package com.warren.lolbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.warren.lolbox.R;

/**
 * 可折叠的LinearLayout
 * @author yangsheng
 * @date 2015年1月2日
 */
public class FlexableLayout extends LinearLayout {

	private ViewGroup mVgContent;
	private ViewGroup mVgPanel;
	private boolean mIsExpanded = false;
	private int mContractHeight;

	public FlexableLayout(Context context) {
		super(context);
	}

	public FlexableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public FlexableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(AttributeSet attrs) {

		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlexableLayout);
		mContractHeight = a.getDimensionPixelSize(R.styleable.FlexableLayout_contract_height, 60);
		a.recycle();
		
		setOrientation(LinearLayout.VERTICAL);
		mVgContent = (ViewGroup) findViewById(R.id.flexablelayout_content);
		mVgPanel = (ViewGroup) findViewById(R.id.flexablelayout_panel);

		mVgPanel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsExpanded) {
					mVgContent.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT, mContractHeight));
				} else {
					mVgContent.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								LinearLayout.LayoutParams.WRAP_CONTENT));
				}
			}
		});
	}
}
