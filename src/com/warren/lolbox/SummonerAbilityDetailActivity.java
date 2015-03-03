package com.warren.lolbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.warren.lolbox.model.bean.SummonerAbility;
import com.warren.lolbox.url.URLUtil;

/**
 * 召唤师技能详情
 * @author warren
 * @date 2015年1月4日
 */
public class SummonerAbilityDetailActivity extends Activity {

	public static final String EXTRA_SUMMONERABILITY = "EXTRA_SUMMONERABILITY";
	
	private SummonerAbility mSa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summonerskilldetail);
		
		mSa = (SummonerAbility) getIntent().getSerializableExtra(EXTRA_SUMMONERABILITY);
		if(mSa == null){
			Toast.makeText(this, "没有召唤师技能", Toast.LENGTH_SHORT).show();
			return;
		}
		
		initCtrl();
	}

	private void initCtrl() {

		ImageView imgSkill = (ImageView) findViewById(R.id.img_skill);
		TextView tvName = (TextView) findViewById(R.id.tv_skillname);
		TextView tvLevel = (TextView) findViewById(R.id.tv_level);
		TextView tvCoolDown = (TextView) findViewById(R.id.tv_cooldown);
		TextView tvDes = (TextView) findViewById(R.id.tv_des);
		TextView tvHint = (TextView) findViewById(R.id.tv_hint);

		AppContext.getApp().getImgLoader().displayImage(URLUtil.getURL_SummonerSkillImg(mSa.getId()), imgSkill);
		tvName.setText(mSa.getName());
		tvLevel.setText("需要等级 " + mSa.getLevel());
		tvCoolDown.setText("冷却时间 " + mSa.getCooldown());
		tvDes.setText(mSa.getDes());
		tvHint.setText(mSa.getTips());
		
	}

}
