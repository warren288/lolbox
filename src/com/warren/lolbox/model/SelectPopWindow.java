package com.warren.lolbox.model;

import java.util.List;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.warren.lolbox.R;

/**
 * 选择器弹框
 * @author yangsheng
 * @date 2015年3月13日
 */
public class SelectPopWindow {

	private PopupWindow pop;
	private View anchor;
	private List<SimpleUserTool> userTools;
	private LayoutInflater inflater;
	private ListView lvMenus;
	private int xOff = 0;
	private int yOff = 0;
	private int width = 0;

	/**
	 * 创建一个{@code SelectPopWindow}，创建完成后调用{@code show()}显示之。
	 * 这个方法相当于{@code create(anchor, editTools, 0, 0)}。<br>
	 * 如果{@code editTools}大小为0，抛出异常。
	 * @param anchor 锚点
	 * @param editTools 可供选择的操作
	 * @exception IllegalArgumentException
	 * @return
	 */
	public static SelectPopWindow create(View anchor, List<SimpleUserTool> editTools) {

		if (anchor == null || editTools == null || editTools.size() == 0) {
			System.out.println("SelectPopWindow.create, 传入参数非法");
			throw new IllegalArgumentException("SelectPopWindow.create, 传入参数非法");
		}
		return new SelectPopWindow(anchor, editTools);
	}

	/**
	 * 创建一个{@code SelectPopWindow}，创建完成后调用{@code show()}显示之。<br>
	 * 如果{@code editTools}大小为0，抛出异常。
	 * @param anchor 锚点
	 * @param editTools 可供选择的操作
	 * @param xOff PopupWindow的X偏移
	 * @param yOff PopupWindow的Y偏移
	 * @exception IllegalArgumentException
	 * @return
	 */
	public static SelectPopWindow create(View anchor, List<SimpleUserTool> editTools, int xOff,
				int yOff) {

		if (anchor == null || editTools == null || editTools.size() == 0) {
			System.out.println("SelectPopWindow.create, 传入参数非法");
			throw new IllegalArgumentException("SelectPopWindow.create, 传入参数非法");
		}
		return new SelectPopWindow(anchor, editTools, xOff, yOff);
	}

	public static SelectPopWindow create(View anchor, List<SimpleUserTool> editTools, int xOff,
				int yOff, int width) {

		if (anchor == null || editTools == null || editTools.size() == 0) {
			System.out.println("SelectPopWindow.create, 传入参数非法");
			throw new IllegalArgumentException("SelectPopWindow.create, 传入参数非法");
		}
		return new SelectPopWindow(anchor, editTools, xOff, yOff, width);
	}

	private SelectPopWindow(View anchor, List<SimpleUserTool> editTools, int xOff, int yOff,
				int width) {

		this.anchor = anchor;
		this.userTools = editTools;
		this.xOff = xOff;
		this.yOff = yOff;
		this.width = width;
		init();
	}

	private SelectPopWindow(View anchor, List<SimpleUserTool> editTools, int xOff, int yOff) {

		this.anchor = anchor;
		this.userTools = editTools;
		this.xOff = xOff;
		this.yOff = yOff;
		init();
	}

	private SelectPopWindow(View anchor, List<SimpleUserTool> editTools) {

		this.anchor = anchor;
		this.userTools = editTools;
		init();
	}

	private void init() {

		inflater = LayoutInflater.from(anchor.getContext());
		View contentView = inflater.inflate(R.layout.pop_selector, null);

		lvMenus = (ListView) contentView.findViewById(R.id.lv_menupop);
		if (width > 0) {
			lvMenus.setLayoutParams(new LinearLayout.LayoutParams(width,
						LinearLayout.LayoutParams.WRAP_CONTENT));
		}
		AdapterSelectPop adapter = new AdapterSelectPop();
		lvMenus.setAdapter(adapter);

		// lvMenus.setLayoutParams(new LayoutParams(100,
		// LayoutParams.WRAP_CONTENT));

		pop = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					true);
		pop.setBackgroundDrawable(new BitmapDrawable(anchor.getContext().getResources()));

		lvMenus.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				userTools.get(position).getCallBack().onCall(userTools.get(position).getName());
				pop.dismiss();
			}
		});
	}

	/**
	 * 设置列表大小
	 * @param param
	 * @return
	 */
	public SelectPopWindow apply(LayoutParams param) {

		if (lvMenus != null) {
			lvMenus.setLayoutParams(param);
		}

		return this;
	}

	/**
	 * 显示PopupWindow，在 {@link #anchor} 的下方
	 */
	public void show() {
		int[] location = new int[2];
		anchor.getLocationOnScreen(location);
		pop.showAsDropDown(anchor, xOff, yOff);
	}

	/**
	 * 选择器列表的Adapter
	 * @author yangsheng
	 * @date 2014年12月20日
	 */
	private class AdapterSelectPop extends BaseAdapter {

		@Override
		public int getCount() {
			return userTools.size();
		}

		@Override
		public Object getItem(int position) {
			return userTools.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView tv;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.pop_selector_item, parent, false);
				tv = (TextView) convertView.findViewById(R.id.tv_menu);
				convertView.setTag(tv);
			} else {
				tv = (TextView) convertView.getTag();
			}
			tv.setText(userTools.get(position).getName());
			return convertView;
		}

	}

}
