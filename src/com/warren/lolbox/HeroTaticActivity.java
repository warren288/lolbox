package com.warren.lolbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.HeroZbTatic;
import com.warren.lolbox.url.DuowanConfig.EnumAbility;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.widget.ImageGroup;
import com.warren.lolbox.widget.TitleBar;

/**
 * 英雄出装Activity
 * @author yangsheng
 * @date 2015年3月10日
 */
public class HeroTaticActivity extends BaseActivity {

	public static final String EXTRA_HERONAME = "EXTRA_HERONAME";
	public static final int DEFAULT_LIMIT = 7;

	private TitleBar mTb;

	private ImageView mImgHero;
	private TextView mTvHeroName;
	private TextView mTvAuthorName;

	private ImageGroup mImgGpQqJn;
	private ImageGroup mImgGpQqZb;
	private TextView mTvQqDescription;
	private ImageGroup mImgGpZqJn;
	private ImageGroup mImgGpZqZb;
	private TextView mTvZqDescription;
	private ImageGroup mImgGpHqJn;
	private ImageGroup mImgGpHqZb;
	private TextView mTvHqDescription;
	private ImageGroup mImgGpNfJn;
	private ImageGroup mImgGpNfZb;
	private TextView mTvNfDescription;

	private String mStrHeroName;
	private List<HeroZbTatic> mLstTatic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_herotatic);
		initCtrl();

		mStrHeroName = getIntent().getExtras().getString(EXTRA_HERONAME);

		requestData();
	}

	private void initCtrl() {
		mTb = (TitleBar) findViewById(R.id.titlebar);

		mImgHero = (ImageView) findViewById(R.id.img_hero);
		mTvHeroName = (TextView) findViewById(R.id.tv_heroname);
		mTvAuthorName = (TextView) findViewById(R.id.tv_authorName);

		mImgGpQqJn = (ImageGroup) findViewById(R.id.imggp_qq_jn);
		mImgGpQqZb = (ImageGroup) findViewById(R.id.imggp_qq_zb);
		mTvQqDescription = (TextView) findViewById(R.id.tv_description_qq);
		mImgGpZqJn = (ImageGroup) findViewById(R.id.imggp_zq_jn);
		mImgGpZqZb = (ImageGroup) findViewById(R.id.imggp_zq_zb);
		mTvZqDescription = (TextView) findViewById(R.id.tv_description_zq);
		mImgGpHqJn = (ImageGroup) findViewById(R.id.imggp_hq_jn);
		mImgGpHqZb = (ImageGroup) findViewById(R.id.imggp_hq_zb);
		mTvHqDescription = (TextView) findViewById(R.id.tv_description_hq);
		mImgGpNfJn = (ImageGroup) findViewById(R.id.imggp_nf_jn);
		mImgGpNfZb = (ImageGroup) findViewById(R.id.imggp_nf_zb);
		mTvNfDescription = (TextView) findViewById(R.id.tv_description_nf);

	}

	private void requestData() {

		AppContext.getApp()
					.getNetManager()
					.get(URLUtil.getURL_HeroCz(mStrHeroName, DEFAULT_LIMIT),
								new IListener<String>() {

									@Override
									public void onCall(String t) {

										if (t == null || t.length() == 0) {
											Toast.makeText(HeroTaticActivity.this, "没有请求到数据",
														Toast.LENGTH_SHORT).show();
											return;
										}

										AppContext.getApp()
													.getJsonManager()
													.parseList(t, HeroZbTatic.class,
																new IListener<List<HeroZbTatic>>() {

																	@Override
																	public void onCall(
																				List<HeroZbTatic> t) {
																		if (t == null
																					|| t.size() == 0) {
																			Toast.makeText(
																						HeroTaticActivity.this,
																						"没有请求到数据",
																						Toast.LENGTH_SHORT)
																						.show();
																			return;
																		}
																		mLstTatic = t;
																		setView(0);
																	}
																});

									}
								});

	}

	private void setView(int index) {

		HeroZbTatic hzt = mLstTatic.get(index);
		mTvHeroName.setText(hzt.getNi_name() + " " + hzt.getCh_name());
		mTvAuthorName.setText(hzt.getAuthor());
		mTvQqDescription.setText(hzt.getPre_explain());
		mTvZqDescription.setText(hzt.getMid_explain());
		mTvHqDescription.setText(hzt.getEnd_explain());
		mTvNfDescription.setText(hzt.getNf_explain());

		ImageLoader imgLoader = AppContext.getApp().getImgLoader();
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mStrHeroName, EnumDPI.DPI120x120), mImgHero);

		Map<String, String> mapSkillUrl = new HashMap<String, String>();

		mapSkillUrl.put("B",
					URLUtil.getURL_HeroAbilityImg(mStrHeroName, EnumAbility.B, EnumDPI.DPI64x64));
		mapSkillUrl.put("Q",
					URLUtil.getURL_HeroAbilityImg(mStrHeroName, EnumAbility.Q, EnumDPI.DPI64x64));
		mapSkillUrl.put("W",
					URLUtil.getURL_HeroAbilityImg(mStrHeroName, EnumAbility.W, EnumDPI.DPI64x64));
		mapSkillUrl.put("E",
					URLUtil.getURL_HeroAbilityImg(mStrHeroName, EnumAbility.E, EnumDPI.DPI64x64));
		mapSkillUrl.put("R",
					URLUtil.getURL_HeroAbilityImg(mStrHeroName, EnumAbility.R, EnumDPI.DPI64x64));

		String[] arrSkill = hzt.getSkill().split(",");
		String[] arrZbQq = hzt.getPre_cz().split(",");
		String[] arrZbZq = hzt.getMid_cz().split(",");
		String[] arrZbHq = hzt.getEnd_cz().split(",");
		String[] arrZbNf = hzt.getNf_cz().split(",");
		
		String[] arrZbUrlQq = new String[arrZbQq.length];
		for(int i = 0; i < arrZbQq.length; i ++){
			arrZbUrlQq[i] = URLUtil.getURL_ZBImg(Integer.parseInt(arrZbQq[i]), EnumDPI.DPI64x64);
		}
		String[] arrZbUrlZq = new String[arrZbZq.length];
		for(int i = 0; i < arrZbZq.length; i ++){
			arrZbUrlZq[i] = URLUtil.getURL_ZBImg(Integer.parseInt(arrZbZq[i]), EnumDPI.DPI64x64);
		}
		String[] arrZbUrlHq = new String[arrZbHq.length];
		for(int i = 0; i < arrZbHq.length; i ++){
			arrZbUrlHq[i] = URLUtil.getURL_ZBImg(Integer.parseInt(arrZbHq[i]), EnumDPI.DPI64x64);
		}
		String[] arrZbUrlNf = new String[arrZbNf.length];
		for(int i = 0; i < arrZbNf.length; i ++){
			arrZbUrlNf[i] = URLUtil.getURL_ZBImg(Integer.parseInt(arrZbNf[i]), EnumDPI.DPI64x64);
		}

		mImgGpQqJn.displayImage(
					imgLoader,
					new String[] { mapSkillUrl.get(arrSkill[0]), mapSkillUrl.get(arrSkill[1]),
							mapSkillUrl.get(arrSkill[2]), mapSkillUrl.get(arrSkill[3]),
							mapSkillUrl.get(arrSkill[4]), mapSkillUrl.get(arrSkill[5]) });
		mImgGpZqJn.displayImage(
					imgLoader,
					new String[] { mapSkillUrl.get(mapSkillUrl.get(arrSkill[6])),
							mapSkillUrl.get(arrSkill[7]), mapSkillUrl.get(arrSkill[8]),
							mapSkillUrl.get(arrSkill[9]), mapSkillUrl.get(arrSkill[10]),
							mapSkillUrl.get(arrSkill[11]) });
		mImgGpHqJn.displayImage(
					imgLoader,
					new String[] {mapSkillUrl.get(mapSkillUrl.get(arrSkill[12])),
							mapSkillUrl.get(arrSkill[13]), mapSkillUrl.get(arrSkill[14]),
							mapSkillUrl.get(arrSkill[15]), mapSkillUrl.get(arrSkill[16]),
							mapSkillUrl.get(arrSkill[17]) });

		mImgGpQqZb.displayImage(imgLoader, arrZbUrlQq);
		mImgGpZqZb.displayImage(imgLoader, arrZbUrlZq);
		mImgGpHqZb.displayImage(imgLoader, arrZbUrlHq);
		mImgGpNfZb.displayImage(imgLoader, arrZbUrlNf);
		
		mImgGpQqJn.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		mImgGpZqJn.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		mImgGpHqJn.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		
		mImgGpQqZb.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		mImgGpZqZb.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		mImgGpHqZb.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		mImgGpNfZb.setOnClickListener(new IListener<Integer>() {

			@Override
			public void onCall(Integer t) {
			}
		});
		
	}
	
	private void initListener(){
		
	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
