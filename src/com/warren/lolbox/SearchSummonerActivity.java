package com.warren.lolbox;

import java.nio.charset.Charset;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.warren.lolbox.widget.TitleBar;

/**
 * 搜索召唤师详细信息Activity
 * @author warren
 * @date 2014年12月28日
 */
public class SearchSummonerActivity extends Activity {

	String strUrl = "http://zdl.mbox.duowan.com/phone/playerDetailNew.php?"
				+ "sn=%E7%94%B5%E4%BF%A1%E5%8D%81%E5%9B%9B&pn=%E8%BF%98%E5%9C%A8%E7%AD%89%E5%BE%85";

	private static String[] arrServerNames = { "艾欧尼亚 电信一", "祖安 电信二", "诺克萨斯 电信三", "班德尔城 电信四",
			"皮尔特沃夫 电信五", "战争学院 电信六", "巨神峰 电信七", "雷瑟守备 电信八", "裁决之地 电信九", "黑色玫瑰 电信十", "暗影岛 电信十一",
			"钢铁烈阳 电信十二", "均衡教派 电信十三", "水晶之痕 电信十四", "影流 电信十五", "守望之海 电信十六", "征服之海 电信十七",
			"卡拉曼达 电信十八", "皮城警备 电信十九", "比尔吉沃特 网通一", "德玛西亚 网通二", "弗雷尔卓德 网通三", "无畏先锋 网通四", "恕瑞玛 网通五",
			"扭曲丛林 网通六", "巨龙之巢 网通七", "教育网专区 教育一" };

	private static String[] arrServers = { "电信一", "电信二", "电信三", "电信四", "电信五", "电信六", "电信七", "电信八",
			"电信九", "电信十", "电信十一", "电信十二", "电信十三", "电信十四", "电信十五", "电信十六", "电信十七", "电信十八", "电信十九",
			"网通一", "网通二", "网通三", "网通四", "网通五", "网通六", "网通七", "教育一" };
	
	private TitleBar mTb;
	private EditText mEtSummonerName;
	private Spinner mSpServer;
	private Button mBtnSearch;
	private ListView mLvHistory;

	private int mServerIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchsummoner);
		initCtrl();
	}

	private void initCtrl() {
		
		mTb = (TitleBar) findViewById(R.id.titlebar);

		mEtSummonerName = (EditText) findViewById(R.id.et_summonername);
		mSpServer = (Spinner) findViewById(R.id.sp_servername);
		mBtnSearch = (Button) findViewById(R.id.btn_search);
		mLvHistory = (ListView) findViewById(R.id.lv_search);

		ArrayAdapter<String> adapterSp = new ArrayAdapter<String>(this, R.layout.textview_spinner,
					arrServerNames);
		mSpServer.setAdapter(adapterSp);


		mSpServer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				mServerIndex = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		mBtnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEtSummonerName.getText().toString().trim().length() == 0) {
					Toast.makeText(SearchSummonerActivity.this, "请填写召唤师名称", Toast.LENGTH_SHORT)
								.show();
					return;
				}
				startSearch(mEtSummonerName.getText().toString().trim(), arrServers[mServerIndex]);
			}
		});

		mLvHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
	}

	/**
	 * 开始搜索召唤师详细信息，成功则打开召唤师详细信息界面，否则提示搜索失败
	 * @param strSummonerName
	 * @param strServer
	 */
	private void startSearch(String strSummonerName, String strServer) {

		RequestParams params = new RequestParams();
		params.put("sn", strServer);
		params.put("pn", strSummonerName);
		AsyncHttpClient http = new AsyncHttpClient();

		http.get(strUrl, params, new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				super.onStart();
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				super.onSuccess(arg0, arg1, arg2);
				String strContent = new String(arg2, Charset.forName("utf-8"));
				openSumonerDetail(strContent);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				super.onFailure(arg0, arg1, arg2, arg3);
				Toast.makeText(SearchSummonerActivity.this, "查找失败，请检查召唤师名称和服务器是否正确",
							Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 打开召唤师详情界面
	 * @param strContent 召唤师详情html字符串
	 */
	private void openSumonerDetail(String strContent) {

		Intent it = new Intent(this, SumonerDetailActivity.class);
		it.putExtra(SumonerDetailActivity.EXTRA_CONTENTDATA, strContent);
		startActivity(it);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

}
