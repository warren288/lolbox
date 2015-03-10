package com.warren.lolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.Hero;
import com.warren.lolbox.model.bean.HeroAbility;
import com.warren.lolbox.url.DuowanConfig.EnumAbility;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.DeviceUtil;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.TitleBar;

/**
 * 英雄详情
 * @author warren
 * @date 2015年1月2日
 */
public class HeroDetailActivity extends BaseActivity {

	public static final String EXTRA_HEROENNAME = "EXTRA_HEROENNAME";

	private TitleBar mTb;
	private ImageView mImgHero;
	private TextView mTvHeroName;
	private TextView mTvTag;
	private TextView mTvPrice;
	private TextView mTvAtt;
	private TextView mTvAbilityDescription;

	private FrameLayout[] mArrFlAbility = new FrameLayout[5];
	private ImageView[] mArrImgAbility = new ImageView[5];
	private View[] mArrVAbility = new View[5];

	private LinearLayout mLlPartner;
	private ImageView mImgPartner1;
	private TextView mTvPartner1;
	private ImageView mImgPartner2;
	private TextView mTvPartner2;
	private RelativeLayout mRlPartnerViewMore;
	private TextView mTvExpandPartner;
	private boolean mIsExpandPartner = false;

	private LinearLayout mLlEnemy;
	private ImageView mImgEnemy1;
	private TextView mTvEnemy1;
	private ImageView mImgEnemy2;
	private TextView mTvEnemy2;
	private RelativeLayout mRlEnemyViewMore;
	private TextView mTvExpandEnemy;
	private boolean mIsExpandEnemy = false;

	private TextView mTvUse;
	private TextView mTvAnswerfor;

	private LinearLayout mLlData;
	private RelativeLayout mRlDataViewMore;
	private TextView mTvExpandData;
	private boolean mIsExpandDataView = false;

	private TextView mTvRank;
	private TextView mTvAttackDistance;
	private TextView mTvMovement;
	private TextView mTvBaseAttack;
	private TextView mTvBaseDefense;
	private TextView mTvBaseMona;
	private TextView mTvBaseHealth;
	private TextView mTvBaseBaoji;
	private TextView mTvMonaRegen;
	private TextView mTvHealthRegen;
	private TextView mTvMagicDefense;
	private SeekBar mSeekRank;

	private TextView mTvStory;
	private RelativeLayout mRlStoryViewMore;
	private TextView mTvExpandStory;
	private boolean mIsExpandStory = false;

	private String mStrHeroEnName;

	private Hero mHero;

	private String[] mArrAbilitysDes = new String[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_herodetail);

		mStrHeroEnName = getIntent().getStringExtra(EXTRA_HEROENNAME);

		initCtrl();

		requestData();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);

		mImgHero = (ImageView) findViewById(R.id.img_hero);
		mTvHeroName = (TextView) findViewById(R.id.tv_heroname);
		mTvTag = (TextView) findViewById(R.id.tv_tag);
		mTvPrice = (TextView) findViewById(R.id.tv_price);
		mTvAtt = (TextView) findViewById(R.id.tv_att);

		mArrFlAbility[0] = (FrameLayout) findViewById(R.id.fl_ability_1);
		mArrImgAbility[0] = (ImageView) findViewById(R.id.img_ability_1);
		mArrVAbility[0] = findViewById(R.id.v_ability_1);
		mArrFlAbility[1] = (FrameLayout) findViewById(R.id.fl_ability_2);
		mArrImgAbility[1] = (ImageView) findViewById(R.id.img_ability_2);
		mArrVAbility[1] = findViewById(R.id.v_ability_2);
		mArrFlAbility[2] = (FrameLayout) findViewById(R.id.fl_ability_3);
		mArrImgAbility[2] = (ImageView) findViewById(R.id.img_ability_3);
		mArrVAbility[2] = findViewById(R.id.v_ability_3);
		mArrFlAbility[3] = (FrameLayout) findViewById(R.id.fl_ability_4);
		mArrImgAbility[3] = (ImageView) findViewById(R.id.img_ability_4);
		mArrVAbility[3] = findViewById(R.id.v_ability_4);
		mArrFlAbility[4] = (FrameLayout) findViewById(R.id.fl_ability_5);
		mArrImgAbility[4] = (ImageView) findViewById(R.id.img_ability_5);
		mArrVAbility[4] = findViewById(R.id.v_ability_5);

		mTvAbilityDescription = (TextView) findViewById(R.id.tv_ability_description);

		mLlPartner = (LinearLayout) findViewById(R.id.ll_partner_content);
		mImgPartner1 = (ImageView) findViewById(R.id.img_partner_1);
		mTvPartner1 = (TextView) findViewById(R.id.tv_partner_1);
		mImgPartner2 = (ImageView) findViewById(R.id.img_partner_2);
		mTvPartner2 = (TextView) findViewById(R.id.tv_partner_2);
		mRlPartnerViewMore = (RelativeLayout) findViewById(R.id.rl_viewmorepartner);
		mTvExpandPartner = (TextView) findViewById(R.id.tv_viewmorepartner);

		mLlEnemy = (LinearLayout) findViewById(R.id.ll_enemy_content);
		mImgEnemy1 = (ImageView) findViewById(R.id.img_enemy_1);
		mTvEnemy1 = (TextView) findViewById(R.id.tv_enemy_1);
		mImgEnemy2 = (ImageView) findViewById(R.id.img_enemy_2);
		mTvEnemy2 = (TextView) findViewById(R.id.tv_enemy_2);
		mRlEnemyViewMore = (RelativeLayout) findViewById(R.id.rl_viewmoreenemy);
		mTvExpandEnemy = (TextView) findViewById(R.id.tv_viewmoreenemy);

		mTvUse = (TextView) findViewById(R.id.tv_use);
		mTvAnswerfor = (TextView) findViewById(R.id.tv_answerfor);

		mLlData = (LinearLayout) findViewById(R.id.ll_data);
		// mTvData = findViewById(R.id.tv_)
		mRlDataViewMore = (RelativeLayout) findViewById(R.id.rl_viewmoredata);
		mTvExpandData = (TextView) findViewById(R.id.tv_viewmoredata);

		mTvRank = (TextView) findViewById(R.id.tv_rank);
		mTvAttackDistance = (TextView) findViewById(R.id.tv_attackdistance);
		mTvMovement = (TextView) findViewById(R.id.tv_movement);
		mTvBaseAttack = (TextView) findViewById(R.id.tv_baseattack);
		mTvBaseDefense = (TextView) findViewById(R.id.tv_basedefense);
		mTvBaseMona = (TextView) findViewById(R.id.tv_basemona);
		mTvBaseHealth = (TextView) findViewById(R.id.tv_basehealth);
		mTvBaseBaoji = (TextView) findViewById(R.id.tv_baojirate);
		mTvMonaRegen = (TextView) findViewById(R.id.tv_monaregen);
		mTvHealthRegen = (TextView) findViewById(R.id.tv_healthregen);
		mTvMagicDefense = (TextView) findViewById(R.id.tv_magicdefense);
		mSeekRank = (SeekBar) findViewById(R.id.seekRank);

		mTvStory = (TextView) findViewById(R.id.tv_story);
		mRlStoryViewMore = (RelativeLayout) findViewById(R.id.rl_viewmorestory);
		mTvExpandStory = (TextView) findViewById(R.id.tv_viewmorestory);

		mTb.setLeftClick(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	/**
	 * 选择技能
	 * @param index
	 */
	private void selectAbitlity(int index) {

		mTvAbilityDescription.setText(mArrAbilitysDes[index] == null ? "" : mArrAbilitysDes[index]);
		for (int i = 0; i < 5; i++) {

			if (i == index) {
				mArrVAbility[i].setVisibility(View.GONE);
			} else {
				mArrVAbility[i].setVisibility(View.VISIBLE);
			}

		}
	}

	/**
	 * 请求数据
	 */
	private void requestData() {

		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_HeroDetail(mStrHeroEnName), new IListener<String>() {

						@Override
						public void onCall(String t) {

							if (t == null || t.length() == 0) {
								Toast.makeText(HeroDetailActivity.this, "没有请求到数据",
											Toast.LENGTH_SHORT).show();
								return;
							}

							t = checkData(t);

							AppContext.getApp().getJsonManager()
										.parse(t, Hero.class, new IListener<Hero>() {

											@Override
											public void onCall(Hero t) {
												mHero = t;
												setView();
											}
										});

						}
					});
	}

	/**
	 * 请求到的英雄详情Json中技能的字段名格式是 Taric_Q ,不便于转换为固定格式的bean，这里处理矫正一下
	 * @param strJsonHero
	 * @return
	 */
	private String checkData(String strJsonHero) {

		strJsonHero = strJsonHero.replace(mStrHeroEnName + "_B", "b");
		strJsonHero = strJsonHero.replace(mStrHeroEnName + "_Q", "q");
		strJsonHero = strJsonHero.replace(mStrHeroEnName + "_W", "w");
		strJsonHero = strJsonHero.replace(mStrHeroEnName + "_E", "e");
		strJsonHero = strJsonHero.replace(mStrHeroEnName + "_R", "r");
		return strJsonHero;
	}

	/**
	 * 根据请求得到的数据设置视图
	 */
	private void setView() {

		ImageLoader imgLoader = AppContext.getApp().getImgLoader();

		imgLoader.displayImage(URLUtil.getURL_HeroImg(mStrHeroEnName, EnumDPI.DPI120x120), mImgHero);

		imgLoader.displayImage(
					URLUtil.getURL_HeroAbilityImg(mStrHeroEnName, EnumAbility.B, EnumDPI.DPI64x64),
					mArrImgAbility[0]);
		imgLoader.displayImage(
					URLUtil.getURL_HeroAbilityImg(mStrHeroEnName, EnumAbility.Q, EnumDPI.DPI64x64),
					mArrImgAbility[1]);
		imgLoader.displayImage(
					URLUtil.getURL_HeroAbilityImg(mStrHeroEnName, EnumAbility.W, EnumDPI.DPI64x64),
					mArrImgAbility[2]);
		imgLoader.displayImage(
					URLUtil.getURL_HeroAbilityImg(mStrHeroEnName, EnumAbility.E, EnumDPI.DPI64x64),
					mArrImgAbility[3]);
		imgLoader.displayImage(
					URLUtil.getURL_HeroAbilityImg(mStrHeroEnName, EnumAbility.R, EnumDPI.DPI64x64),
					mArrImgAbility[4]);

		// 新英雄可能价格不明确，服务器返回的Json中price字段内容为 ","，需要特殊对待
		String strPrice = mHero.getPrice() != null && mHero.getPrice().contains(",")
					&& mHero.getPrice().length() >= 3 ? ("金" + mHero.getPrice().split(",")[0] + "券" + mHero
					.getPrice().split(",")[1]) : "";
		String strAbility = "攻" + mHero.getRatingAttack() + " 法" + mHero.getRatingMagic() + " 防"
					+ mHero.getRatingDefense() + " 难" + mHero.getRatingDifficulty();

		mTvHeroName.setText(mHero.getDisplayName());
		mTvTag.setText(mHero.getTags());
		mTvPrice.setText(strPrice);
		mTvAtt.setText(strAbility);

		// 新英雄的最佳搭档和最佳克制可能为空，需要特殊对待。
		// 2015.1.3 今天的去请求结果里全都没有最佳搭档和最佳克制的节点了。
		if (mHero.getLike() != null && mHero.getLike().size() == 2) {
			imgLoader.displayImage(URLUtil.getURL_HeroImg(mHero.getLike().get(0).get("partner"),
						EnumDPI.DPI120x120), mImgPartner1);
			imgLoader.displayImage(URLUtil.getURL_HeroImg(mHero.getLike().get(1).get("partner"),
						EnumDPI.DPI120x120), mImgPartner2);
			mTvPartner1.setText(mHero.getLike().get(0).get("des"));
			mTvPartner2.setText(mHero.getLike().get(1).get("des"));
			mImgPartner1.setOnClickListener(new OnHeroClickListener());
			mImgPartner2.setOnClickListener(new OnHeroClickListener());
		} else {
			findViewById(R.id.tv_hint_partner).setVisibility(View.GONE);
			findViewById(R.id.ll_partner).setVisibility(View.GONE);
		}

		if (mHero.getHate() != null && mHero.getHate().size() == 2) {

			imgLoader.displayImage(URLUtil.getURL_HeroImg(mHero.getHate().get(0).get("partner"),
						EnumDPI.DPI120x120), mImgEnemy1);
			imgLoader.displayImage(URLUtil.getURL_HeroImg(mHero.getHate().get(1).get("partner"),
						EnumDPI.DPI120x120), mImgEnemy2);

			mTvEnemy1.setText(mHero.getHate().get(0).get("des"));
			mTvEnemy2.setText(mHero.getHate().get(1).get("des"));

			mImgEnemy1.setOnClickListener(new OnHeroClickListener());
			mImgEnemy2.setOnClickListener(new OnHeroClickListener());
		} else {
			findViewById(R.id.tv_hint_enemy).setVisibility(View.GONE);
			findViewById(R.id.ll_enemy).setVisibility(View.GONE);
		}

		mTvUse.setText(mHero.getTips());
		mTvAnswerfor.setText(mHero.getOpponentTips());

		mTvStory.setText(mHero.getDescription());

		mArrAbilitysDes[0] = generateAbilityDescription(mHero.getB());
		mArrAbilitysDes[1] = generateAbilityDescription(mHero.getQ());
		mArrAbilitysDes[2] = generateAbilityDescription(mHero.getW());
		mArrAbilitysDes[3] = generateAbilityDescription(mHero.getE());
		mArrAbilitysDes[4] = generateAbilityDescription(mHero.getR());

		selectAbitlity(0);

		for (int i = 0; i < 5; i++) {
			final int iIn = i;
			mArrFlAbility[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					selectAbitlity(iIn);
				}
			});
		}

		mRlPartnerViewMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsExpandPartner) {
					mIsExpandPartner = false;
					mLlPartner.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT, DeviceUtil.dp2Px(
											HeroDetailActivity.this, 60)));
					mTvExpandPartner.setText("点击查看更多");
				} else {
					mIsExpandPartner = true;
					mLlPartner.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								LinearLayout.LayoutParams.WRAP_CONTENT));
					mTvExpandPartner.setText("点击隐藏");
				}
			}
		});

		mRlEnemyViewMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsExpandEnemy) {
					mIsExpandEnemy = false;
					mLlEnemy.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT, DeviceUtil.dp2Px(
											HeroDetailActivity.this, 60)));
					mTvExpandEnemy.setText("点击查看更多");
				} else {
					mIsExpandEnemy = true;
					mLlEnemy.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								LinearLayout.LayoutParams.WRAP_CONTENT));
					mTvExpandEnemy.setText("点击隐藏");
				}
			}
		});

		mRlStoryViewMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsExpandStory) {
					mIsExpandStory = false;
					mTvStory.setVisibility(View.GONE);
					mTvExpandStory.setText("点击查看更多");
				} else {
					mIsExpandStory = true;
					mTvStory.setVisibility(View.VISIBLE);
					mTvExpandStory.setText("点击隐藏");
				}
			}
		});

		mRlDataViewMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsExpandDataView) {
					mIsExpandDataView = false;
					mLlData.setVisibility(View.GONE);
					mTvExpandData.setText("点击查看更多");
				} else {
					mIsExpandDataView = true;
					mLlData.setVisibility(View.VISIBLE);
					mTvExpandData.setText("点击隐藏");
				}
			}
		});

		mTvRank.setText("1");
		mTvAttackDistance.setText(mHero.getRange());
		mTvBaseAttack.setText(mHero.getAttackBase());
		mTvMovement.setText(mHero.getMoveSpeed());
		mTvBaseDefense.setText(mHero.getArmorBase());
		mTvBaseMona.setText(mHero.getManaBase());
		mTvBaseHealth.setText(mHero.getHealthBase());
		mTvBaseBaoji.setText(mHero.getCriticalChanceBase());
		mTvMonaRegen.setText(mHero.getManaRegenBase());
		mTvHealthRegen.setText(mHero.getHealthRegenBase());
		mTvMagicDefense.setText(mHero.getMagicResistBase());

		mSeekRank.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

				// SeekBar的取值区间是 0-17，显示时调整为 1-18
				progress = progress + 1;
				mTvRank.setText("" + progress);
				mTvAttackDistance.setText(mHero.getRange());
				mTvBaseAttack.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getAttackBase())
										+ Double.parseDouble(mHero.getAttackLevel()) * progress, 1));
				mTvMovement.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getMoveSpeed()), 1));
				mTvBaseDefense.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getArmorBase())
										+ Double.parseDouble(mHero.getArmorLevel()) * progress, 1));
				mTvBaseMona.setText(StringUtils.keepDecimal(Double.parseDouble(mHero.getManaBase())
							+ Double.parseDouble(mHero.getManaLevel()) * progress, 1));
				mTvBaseHealth.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getHealthBase())
										+ Double.parseDouble(mHero.getHealthLevel()) * progress, 1));
				mTvBaseBaoji.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getCriticalChanceBase())
										+ Double.parseDouble(mHero.getCriticalChanceLevel())
										* progress, 1));
				mTvMonaRegen.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getManaRegenBase())
										+ Double.parseDouble(mHero.getManaRegenLevel()) * progress,
							1));
				mTvHealthRegen.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getHealthRegenBase())
										+ Double.parseDouble(mHero.getHealthRegenLevel())
										* progress, 1));
				mTvMagicDefense.setText(StringUtils.keepDecimal(
							Double.parseDouble(mHero.getMagicResistBase())
										+ Double.parseDouble(mHero.getMagicResistLevel())
										* progress, 1));
			}
		});

		mSeekRank.setProgress(0);
	}

	/**
	 * 构造英雄技能的描述
	 * @param ability
	 * @return
	 */
	private String generateAbilityDescription(HeroAbility ability) {

		StringBuilder sbDescription = new StringBuilder();
		sbDescription.append(ability.getName() + "\n");
		sbDescription.append("消耗：" + ability.getCost() + "\n");
		sbDescription.append("冷却：" + ability.getCooldown() + "\n");
		sbDescription.append("范围：" + ability.getRange() + "\n");
		sbDescription.append("效果：" + ability.getDescription());

		return sbDescription.toString();
	}

	/**
	 * 搭档或克制 英雄头像点击事件
	 * @author warren
	 * @date 2015年1月2日
	 */
	class OnHeroClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			String strHeroName = "";

			switch (v.getId()) {
			case R.id.img_partner_1:
				strHeroName = mHero.getLike().get(0).get("partner");
				break;
			case R.id.img_partner_2:
				strHeroName = mHero.getLike().get(1).get("partner");
				break;
			case R.id.img_enemy_1:
				strHeroName = mHero.getHate().get(0).get("partner");
				break;
			case R.id.img_enemy_2:
				strHeroName = mHero.getHate().get(1).get("partner");
				break;
			default:
				break;
			}

			if (StringUtils.isNullOrZero(strHeroName)) {
				Toast.makeText(HeroDetailActivity.this, "所请求的英雄不存在", Toast.LENGTH_SHORT).show();
				return;
			}

			Intent it = new Intent(HeroDetailActivity.this, HeroDetailActivity.class);
			it.putExtra(HeroDetailActivity.EXTRA_HEROENNAME, strHeroName);
			HeroDetailActivity.this.startActivity(it);
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

		}

	}

	@Override
	protected boolean goBack() {
		return false;
	}

}
