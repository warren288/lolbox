package com.warren.lolbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.SimpleTool;
import com.warren.lolbox.model.bean.SummonerInfo;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.MessageDialog;
import com.warren.lolbox.widget.TitleBar;

/**
 * 工具Fragment
 * @author warren
 * @date 2014年12月28日
 */
public class ToolFragment extends BaseFragment {

	private static String LOGTAG = "ToolFragment";

	private LinearLayout mLlRoot;
	private TitleBar mTb;

	private RelativeLayout mRlSummonerAdd;
	private Button mBtnSummonerAdd;
	private RelativeLayout mRlSummonerRoot;
	private RelativeLayout mRlSummonerDetail;
	private ImageView mImgSummoner;
	private TextView mTvSummonerName;
	private TextView mTvSummonerServer;
	private TextView mTvSummonerLevel;
	private TextView mTvSummonerUp;
	private TextView mTvZdl;

	private GridView mGridTools;
	private BaseGridAdapter mAdapter;

	private SummonerInfo mSummonerInfo;
	private List<SimpleTool> mTools = new ArrayList<SimpleTool>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLlRoot = (LinearLayout) inflater.inflate(R.layout.frag_tool, container, false);

		initTools();

		initCtrl();

		return mLlRoot;
	}

	@Override
	public void onResume() {
		super.onResume();
		requestSummonerData();
	}

	private void initTools() {

		SimpleTool stWeather = new SimpleTool(R.drawable.tool_menu_bmyx, R.string.toolmenu_weather);
		SimpleTool stHero = new SimpleTool(R.drawable.tool_menu_hero, R.string.toolmenu_yx);
		SimpleTool stVideo = new SimpleTool(R.drawable.tool_menu_video, R.string.toolmenu_video);
		SimpleTool stShake = new SimpleTool(R.drawable.tool_menu_shake, R.string.toolmenu_shrink);
		SimpleTool stLottery = new SimpleTool(R.drawable.tool_menu_lottery,
					R.string.toolmenu_lottery);
		SimpleTool stNews = new SimpleTool(R.drawable.tool_menu_news, R.string.toolmenu_boxnews);
		SimpleTool stLiveShow = new SimpleTool(R.drawable.tool_menu_live,
					R.string.toolmenu_liveshow);
		SimpleTool stReflectTest = new SimpleTool(R.drawable.tool_menu_video,
					R.string.toolmenu_reflecttest);
		SimpleTool stBaike = new SimpleTool(R.drawable.tool_menu_baike, R.string.toolmenu_baike);

		mTools.add(stHero);
		mTools.add(stBaike);
		mTools.add(stNews);
		mTools.add(stWeather);
		mTools.add(stShake);
		mTools.add(stLiveShow);
		mTools.add(stVideo);
		mTools.add(stLottery);
		mTools.add(stReflectTest);
	}

	private void initCtrl() {

		mTb = (TitleBar) mLlRoot.findViewById(R.id.titlebar);

		mRlSummonerAdd = (RelativeLayout) mLlRoot.findViewById(R.id.rl_summoner_add);
		mBtnSummonerAdd = (Button) mLlRoot.findViewById(R.id.btn_summoner_add);

		mRlSummonerRoot = (RelativeLayout) mLlRoot.findViewById(R.id.rl_summoner_root);
		mRlSummonerDetail = (RelativeLayout) mLlRoot.findViewById(R.id.rl_summoner_detail);
		mImgSummoner = (ImageView) mLlRoot.findViewById(R.id.img_summoner_pic);
		mTvSummonerName = (TextView) mLlRoot.findViewById(R.id.tv_summoner_name);
		mTvSummonerServer = (TextView) mLlRoot.findViewById(R.id.tv_summoner_server);
		mTvSummonerLevel = (TextView) mLlRoot.findViewById(R.id.tv_summoner_level);
		mTvSummonerUp = (TextView) mLlRoot.findViewById(R.id.tv_summoner_up);
		mTvZdl = (TextView) mLlRoot.findViewById(R.id.tv_summoner_powervalue);

		mRlSummonerAdd.setVisibility(View.VISIBLE);
		mRlSummonerRoot.setVisibility(View.GONE);

		mGridTools = (GridView) mLlRoot.findViewById(R.id.grid_tools);

		mAdapter = new BaseGridAdapter(LayoutInflater.from(mLlRoot.getContext()));
		mAdapter.setLstTools(mTools);

		mGridTools.setAdapter(mAdapter);

		mTb.setLeftClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openSummonerSearch();
			}
		});

		mTb.setRightClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// AlertDialog.Builder builder = new
				// AlertDialog.Builder(getActivity(), R.style.Dialog_Alert);
				//
				// builder.setTitle("测试测试");
				// builder.setMessage("测试内容\n测试内容2");
				// builder.setPositiveButton("确定", null);
				// builder.setNegativeButton("取消", null);
				// builder.show();

				new MessageDialog(getActivity()).setTitle("测试测试").setMessage("测试内容\n测试内容2")
							.setPositiveButton("确定", null)
							.setNegativeButton("取消", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									Toast.makeText(getActivity(), "取消了", Toast.LENGTH_SHORT).show();
								}

							}).show();

			}
		});

		mGridTools.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				openGridActivity(position);
			}
		});

		mRlSummonerDetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mSummonerInfo != null) {
					BaseKitManager.openSummonerDetail(
								(BaseActivity) getActivity(),
								URLUtil.getURL_SummonerDetail(mSummonerInfo.getPn(),
											mSummonerInfo.getSn()));
				}

			}
		});

		mBtnSummonerAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(), SearchSummonerActivity.class);
				it.putExtra(SearchSummonerActivity.EXTRA_ISFORSETSUMMONER, true);
				startActivity(it);
			}
		});

	}

	private void requestSummonerData() {

		final String strSummonerName = SystemConfig.getIntance().getDefaultSummonerName();
		final String strSummonerServer = SystemConfig.getIntance().getDefaultSummonerServer();

		httpGet(URLUtil.getURL_SummonerInfo(strSummonerName, strSummonerServer), SystemConfig
					.getIntance().getCommHead(), new IListener<String>() {

			@Override
			public void onCall(String strJson) {
				if (StringUtils.isNullOrZero(strJson)) {
					Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
					return;
				}
				jsonParseMap(strJson, new IListener<Map<String, HashMap<String, Object>>>() {

					@Override
					public void onCall(Map<String, HashMap<String, Object>> map) {
						if (map == null || !map.containsKey(strSummonerName)) {
							Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
							return;
						}
						mSummonerInfo = new SummonerInfo(map.get(strSummonerName));
						setSummonerView();
					}
				});
			}
		});
	}

	private void setSummonerView() {

		if (mSummonerInfo == null) {
			mRlSummonerRoot.setVisibility(View.GONE);
			mRlSummonerAdd.setVisibility(View.VISIBLE);
			return;
		}
		mRlSummonerAdd.setVisibility(View.GONE);
		mRlSummonerRoot.setVisibility(View.VISIBLE);
		mTvSummonerName.setText(mSummonerInfo.getPn());
		mTvSummonerServer.setText(mSummonerInfo.getSn());
		mTvSummonerLevel.setText(mSummonerInfo.getTierDesc());
		// mTvSummonerUp.setText(mSummonerInfo.get);
		mTvZdl.setText("" + mSummonerInfo.getZdl());
		AppContext.getApp()
					.getImgLoader()
					.displayImage(URLUtil.getUrl_SummonerImage(mSummonerInfo.getIcon()),
								mImgSummoner);

	}

	@Override
	public String getName() {
		return "ToolFragment";
	}

	private void openGridActivity(int position) {

		if (position == 0) {
			Intent it = new Intent(getActivity(), HeroListActivity.class);
			startActivity(it);
			getActivity().overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
		} else if (position == 1) {

			Intent it = new Intent(getActivity(), BaikeActivity.class);
			startActivity(it);
			getActivity().overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
		} else if (position == 2) {

			Intent it = new Intent(getActivity(), HotNewsActivity.class);
			startActivity(it);
			getActivity().overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
		}
	}

	/**
	 * 打开召唤师搜索界面
	 */
	private void openSummonerSearch() {
		Intent it = new Intent(getActivity(), SearchSummonerActivity.class);
		startActivity(it);
		getActivity().overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
	}

	public class BaseGridAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private List<SimpleTool> lstTools;

		public BaseGridAdapter(LayoutInflater inflater) {
			this.inflater = inflater;
		}

		public void setLstTools(List<SimpleTool> lstTools) {
			this.lstTools = lstTools;
		}

		@Override
		public int getCount() {
			return lstTools != null ? lstTools.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.frag_tool_grid, parent, false);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.img.setImageResource(lstTools.get(position).getImgResId());
			holder.tv.setText(lstTools.get(position).getTxtResId());

			AbsListView.LayoutParams param = new AbsListView.LayoutParams(
						android.view.ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight()
									/ (lstTools.size() / 3));
			convertView.setLayoutParams(param);
			return convertView;
		}

		class ViewHolder {
			ImageView img;
			TextView tv;
		}

	}

	@Override
	public View getRootView() {
		return null;
	}

}
