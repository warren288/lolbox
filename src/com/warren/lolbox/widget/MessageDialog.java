package com.warren.lolbox.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warren.lolbox.R;

/**
 * 消息提示框
 * @author warren
 * @date 2015年1月5日
 */
public class MessageDialog {
	
	private DialogInterface.OnClickListener mListenerPostive;
	private DialogInterface.OnClickListener mListenerNegative;
	
	private AlertDialog mAlert;
	private AlertDialog.Builder mBuilder;
	
	private Context mContext;
	
	private String mStrTitle;
	private String mStrMessage;
	private String mStrOk;
	private String mStrCancel;
	
	private ViewGroup mVRoot;
	
	private LinearLayout mLlTitlePanel;
	private LinearLayout mLlContentPanel;
	private LinearLayout mLlButtonPanel;
	
	private TextView mTvTitle;
	private TextView mTvMessage;
	private Button mBtnPositive;
	private Button mBtnNegative;
	
	public MessageDialog(Context context){
		
		this.mContext = context;
		
		mVRoot = (ViewGroup) LayoutInflater.from(this.mContext).inflate(R.layout.dlg_message, null);
		mLlTitlePanel = (LinearLayout) this.mVRoot.findViewById(R.id.topPanel);
		mLlContentPanel = (LinearLayout) this.mVRoot.findViewById(R.id.contentPanel);
		mLlButtonPanel = (LinearLayout) this.mVRoot.findViewById(R.id.buttonPanel);
		
		mTvTitle = (TextView) this.mVRoot.findViewById(R.id.alertTitle);
		mTvMessage = (TextView) this.mVRoot.findViewById(R.id.message);
		mBtnPositive = (Button) this.mVRoot.findViewById(R.id.button1);
		mBtnNegative = (Button) this.mVRoot.findViewById(R.id.button2);
		
		mBuilder = new AlertDialog.Builder(context);
	}
	
	/**
	 * 显示Dialog
	 * @return
	 */
	public MessageDialog show(){
		
		// 没有设置标题就不显示标题区
		if(this.mStrTitle == null){
			mLlTitlePanel.setVisibility(View.GONE);
		} else {
			mTvTitle.setText(this.mStrTitle);
		}
		
		// 内容区无论如何都要显示
		mTvMessage.setText(mStrMessage == null ? "" : mStrMessage);
		
		if(this.mStrOk == null && this.mStrCancel == null){
			mLlButtonPanel.setVisibility(View.GONE);
		} else {
			if(mStrOk != null){
				mBtnPositive.setText(mStrOk);
				mBtnPositive.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(mListenerPostive != null){
							mListenerPostive.onClick(mAlert, DialogInterface.BUTTON1);
						}
						mAlert.dismiss();
					}
				});
			} else {
				mBtnPositive.setVisibility(View.GONE);
			}
			if(mStrCancel != null){
				mBtnNegative.setText(mStrCancel);
				mBtnNegative.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(mListenerNegative != null){
							mListenerNegative.onClick(mAlert, DialogInterface.BUTTON2);
						}
						mAlert.dismiss();
					}
				});
			} else {
				mBtnNegative.setVisibility(View.GONE);
			}
		}
		
		mAlert = mBuilder.show();
		
		// 显示Dialog后才能使用 setContentView 设置内容视图，否则会报异常，因为Dialog只有在显示渲染之后才能获得其窗口。
		mAlert.getWindow().setContentView(mVRoot);
		return this;
	}
	
	/**
	 * 隐藏Dialog
	 */
	public void dismiss(){
		if(mAlert != null && mAlert.isShowing()){
			mAlert.dismiss();
		}
	}
	
	/**
	 * 设置Dialog标题
	 * @param text
	 * @return
	 */
	public MessageDialog setTitle(String text){
		this.mStrTitle = text;
		return this;
	}
	
	/**
	 * 设置消息内容
	 * @param text
	 * @return
	 */
	public MessageDialog setMessage(String text){
		this.mStrMessage = text;
		return this;
	}
	
	/**
	 * 设置确定按钮
	 * @param text
	 * @param listener
	 * @return
	 */
	public MessageDialog setPositiveButton(String text, DialogInterface.OnClickListener listener){
		mStrOk = text;
		this.mListenerPostive = listener;
		return this;
	}
	
	/**
	 * 设置取消按钮
	 * @param text
	 * @param listener
	 * @return
	 */
	public MessageDialog setNegativeButton(String text, DialogInterface.OnClickListener listener){
		mStrCancel = text;
		this.mListenerNegative = listener;
		return this;
	}
}
