package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warren.lolbox.model.BaseContentFragment;
import com.warren.lolbox.model.SimpleTool;
import com.warren.lolbox.widget.MessageDialog;
import com.warren.lolbox.widget.TitleBar;

/**
 * 工具Fragment
 * @author warren
 * @date 2014年12月28日
 */
public class ToolFragment extends BaseContentFragment {

	private static String LOGTAG = "ToolFragment";

	private LinearLayout mLlRoot;
	private TitleBar mTb;
	private GridView mGridTools;
	private BaseGridAdapter mAdapter;

	private List<SimpleTool> mTools = new ArrayList<SimpleTool>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mLlRoot = (LinearLayout) inflater.inflate(R.layout.frag_tool, container, false);

		initTools();

		initCtrl();

		return mLlRoot;
	}

	private void initTools() {

		SimpleTool stBMYX = new SimpleTool(R.drawable.tool_menu_bmyx, R.string.toolmenu_bmyxtest);
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
		mTools.add(stBMYX);
		mTools.add(stVideo);
		mTools.add(stShake);
		mTools.add(stLottery);
		mTools.add(stLiveShow);
		mTools.add(stReflectTest);
	}

	private void initCtrl() {

		mTb = (TitleBar) mLlRoot.findViewById(R.id.titlebar);
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
