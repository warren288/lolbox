package com.warren.lolbox;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warren.lolbox.model.IBaseFragment;
import com.warren.lolbox.model.IListener;

/**
 * 主界面底部的操作栏
 * @author warren
 * @date 2014年12月28日
 */
public class FootFragment extends Fragment implements IBaseFragment {

	private static String LOGTAG = "MainFootFragment";

	private LinearLayout mLlRoot;
	private LinearLayout mLlTool;
	private LinearLayout mLlNews;
	private LinearLayout mLlChat;
	private LinearLayout mLlAction;
	private LinearLayout mLlFind;

	private ImageView mImgTool;
	private ImageView mImgNews;
	private ImageView mImgChat;
	private ImageView mImgAction;
	private ImageView mImgFind;

	private TextView mTvTool;
	private TextView mTvNews;
	private TextView mTvChat;
	private TextView mTvAction;
	private TextView mTvFind;

	private IListener<Integer> mListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mLlRoot = (LinearLayout) inflater.inflate(R.layout.frag_mainfoot, container, false);
		initCtrl();
		return mLlRoot;
	}

	public void setListeners(IListener<Integer> listener) {
		this.mListener = listener;
	}

	private void initCtrl() {

		mLlTool = (LinearLayout) mLlRoot.findViewById(R.id.ll_tool);
		mLlNews = (LinearLayout) mLlRoot.findViewById(R.id.ll_news);
		mLlChat = (LinearLayout) mLlRoot.findViewById(R.id.ll_chat);
		mLlAction = (LinearLayout) mLlRoot.findViewById(R.id.ll_action);
		mLlFind = (LinearLayout) mLlRoot.findViewById(R.id.ll_find);

		mImgTool = (ImageView) mLlRoot.findViewById(R.id.img_tool);
		mImgNews = (ImageView) mLlRoot.findViewById(R.id.img_news);
		mImgChat = (ImageView) mLlRoot.findViewById(R.id.img_chat);
		mImgAction = (ImageView) mLlRoot.findViewById(R.id.img_action);
		mImgFind = (ImageView) mLlRoot.findViewById(R.id.img_find);

		mTvTool = (TextView) mLlRoot.findViewById(R.id.tv_tool);
		mTvNews = (TextView) mLlRoot.findViewById(R.id.tv_news);
		mTvChat = (TextView) mLlRoot.findViewById(R.id.tv_chat);
		mTvAction = (TextView) mLlRoot.findViewById(R.id.tv_action);
		mTvFind = (TextView) mLlRoot.findViewById(R.id.tv_find);

		mLlTool.setOnClickListener(new OnOperClick());
		mLlNews.setOnClickListener(new OnOperClick());
		mLlChat.setOnClickListener(new OnOperClick());
		mLlAction.setOnClickListener(new OnOperClick());
		mLlFind.setOnClickListener(new OnOperClick());
		// 屏蔽未实现功能
		mLlChat.setVisibility(View.GONE);
		mLlAction.setVisibility(View.GONE);
	}

	/**
	 * 选项按钮点击事件
	 * @author warren
	 * @date 2014年12月28日
	 */
	class OnOperClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			if (mListener == null) {
				Log.w(LOGTAG, "监听事件不正确，无法处理");
				return;
			}

			// 将选中的工具设为选中状态，其他的工具均设为未选中状态。
			// 触发设置的回调监听
			switch (v.getId()) {
			case R.id.ll_tool:
				mListener.onCall(0);

				mImgTool.setImageResource(R.drawable.tool_btn_bg_d);
				mImgNews.setImageResource(R.drawable.news_btn_bg_s);
				mImgChat.setImageResource(R.drawable.chat_btn_bg_s);
				mImgAction.setImageResource(R.drawable.moment_btn_bg_s);
				mImgFind.setImageResource(R.drawable.moment_unseleted);
				mTvTool.setTextColor(getResources().getColor(R.color.greenblue));
				mTvNews.setTextColor(getResources().getColor(R.color.main_blue));
				mTvChat.setTextColor(getResources().getColor(R.color.main_blue));
				mTvAction.setTextColor(getResources().getColor(R.color.main_blue));
				mTvFind.setTextColor(getResources().getColor(R.color.main_blue));
				break;
			case R.id.ll_news:
				mListener.onCall(1);

				mImgTool.setImageResource(R.drawable.tool_btn_bg_s);
				mImgNews.setImageResource(R.drawable.news_btn_bg_d);
				mImgChat.setImageResource(R.drawable.chat_btn_bg_s);
				mImgAction.setImageResource(R.drawable.moment_btn_bg_s);
				mImgFind.setImageResource(R.drawable.moment_unseleted);
				mTvTool.setTextColor(getResources().getColor(R.color.main_blue));
				mTvNews.setTextColor(getResources().getColor(R.color.greenblue));
				mTvChat.setTextColor(getResources().getColor(R.color.main_blue));
				mTvAction.setTextColor(getResources().getColor(R.color.main_blue));
				mTvFind.setTextColor(getResources().getColor(R.color.main_blue));

				break;
			case R.id.ll_chat:
				mListener.onCall(2);

				mImgTool.setImageResource(R.drawable.tool_btn_bg_s);
				mImgNews.setImageResource(R.drawable.news_btn_bg_s);
				mImgChat.setImageResource(R.drawable.chat_btn_bg_d);
				mImgAction.setImageResource(R.drawable.moment_btn_bg_s);
				mImgFind.setImageResource(R.drawable.moment_unseleted);
				mTvTool.setTextColor(getResources().getColor(R.color.main_blue));
				mTvNews.setTextColor(getResources().getColor(R.color.main_blue));
				mTvChat.setTextColor(getResources().getColor(R.color.greenblue));
				mTvAction.setTextColor(getResources().getColor(R.color.main_blue));
				mTvFind.setTextColor(getResources().getColor(R.color.main_blue));

				break;
			case R.id.ll_action:
				mListener.onCall(3);

				mImgTool.setImageResource(R.drawable.tool_btn_bg_s);
				mImgNews.setImageResource(R.drawable.news_btn_bg_s);
				mImgChat.setImageResource(R.drawable.chat_btn_bg_s);
				mImgAction.setImageResource(R.drawable.moment_btn_bg_d);
				mImgFind.setImageResource(R.drawable.moment_unseleted);
				mTvTool.setTextColor(getResources().getColor(R.color.main_blue));
				mTvNews.setTextColor(getResources().getColor(R.color.main_blue));
				mTvChat.setTextColor(getResources().getColor(R.color.main_blue));
				mTvAction.setTextColor(getResources().getColor(R.color.greenblue));
				mTvFind.setTextColor(getResources().getColor(R.color.main_blue));

				break;
			case R.id.ll_find:
				mListener.onCall(4);

				mImgTool.setImageResource(R.drawable.tool_btn_bg_s);
				mImgNews.setImageResource(R.drawable.news_btn_bg_s);
				mImgChat.setImageResource(R.drawable.chat_btn_bg_s);
				mImgAction.setImageResource(R.drawable.moment_btn_bg_s);
				mImgFind.setImageResource(R.drawable.moment_seleted);
				mTvTool.setTextColor(getResources().getColor(R.color.main_blue));
				mTvNews.setTextColor(getResources().getColor(R.color.main_blue));
				mTvChat.setTextColor(getResources().getColor(R.color.main_blue));
				mTvAction.setTextColor(getResources().getColor(R.color.main_blue));
				mTvFind.setTextColor(getResources().getColor(R.color.greenblue));

				break;
			default:
				break;
			}
		}
	}

	@Override
	public String getName() {
		return "MainFootFragment";
	}

	@Override
	public View getRootView() {
		return null;
	}

}
