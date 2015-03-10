package com.warren.lolbox;

import java.util.List;

import android.os.Bundle;
import android.widget.Toast;

import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.SummonerAbility;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

/**
 * 召唤师技能Activity
 * @author yangsheng
 * @date 2015年3月10日
 */
public class SummonerAbilityActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onlytitlebar);

		initCtrl();
	}

	private void initCtrl() {

		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_SummonerSkill(), new IListener<String>() {

						@Override
						public void onCall(String strJson) {

							if (StringUtils.isNullOrZero(strJson)) {
								Toast.makeText(SummonerAbilityActivity.this, "请求数据失败",
											Toast.LENGTH_SHORT).show();
								return;
							}

							AppContext.getApp()
										.getJsonManager()
										.parseList(strJson, SummonerAbility.class,
													new IListener<List<SummonerAbility>>() {

														@Override
														public void onCall(List<SummonerAbility> lst) {
															if (lst == null || lst.size() == 0) {
																Toast.makeText(
																			SummonerAbilityActivity.this,
																			"请求数据失败",
																			Toast.LENGTH_SHORT)
																			.show();
																return;
															}
															BaseGridFragment frag = new BaseGridFragment();
															frag.setLstSumAbility(lst);

															getFragmentManager()
																		.beginTransaction()
																		.add(R.id.ll_root, frag,
																					frag.getName())
																		.commit();
														}

													});

						}
					});

	}

	@Override
	protected boolean goBack() {
		return false;
	}
}
