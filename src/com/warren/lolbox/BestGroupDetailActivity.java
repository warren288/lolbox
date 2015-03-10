package com.warren.lolbox;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.bean.BestGroup;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.widget.TitleBar;

/**
 * 最佳阵容详细信息
 * @author warren
 * @date 2015年1月3日
 */
public class BestGroupDetailActivity extends BaseActivity {

	public static final String EXTRA_BESTGROUP = "EXTRA_BESTGROUP";

	private TitleBar mTb;
	private BestGroup mBg;

	private TextView mTvDesTitle;
	private TextView mTvDes;
	private ImageView[] mArrImg = new ImageView[5];

	private ImageView[] mArrImgDes = new ImageView[5];

	private TextView[] mArrTvDes = new TextView[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bestgroupdetail);

		mBg = (BestGroup) getIntent().getSerializableExtra(EXTRA_BESTGROUP);
		
		if(mBg == null){
			Toast.makeText(this, "没有阵容信息", Toast.LENGTH_SHORT).show();
			return;
		}
		
		initCtrl();
	}

	private void initCtrl() {
		
		mTb = (TitleBar) findViewById(R.id.titlebar);
		
		mTvDesTitle = (TextView) findViewById(R.id.tv_destitle);
		mTvDes = (TextView) findViewById(R.id.tv_des);
		mArrTvDes[0] = (TextView) findViewById(R.id.tv_des_1);
		mArrTvDes[1] = (TextView) findViewById(R.id.tv_des_2);
		mArrTvDes[2] = (TextView) findViewById(R.id.tv_des_3);
		mArrTvDes[3] = (TextView) findViewById(R.id.tv_des_4);
		mArrTvDes[4] = (TextView) findViewById(R.id.tv_des_5);
		
		mArrImg[0] = (ImageView) findViewById(R.id.img_1);
		mArrImg[1] = (ImageView) findViewById(R.id.img_2);
		mArrImg[2] = (ImageView) findViewById(R.id.img_3);
		mArrImg[3] = (ImageView) findViewById(R.id.img_4);
		mArrImg[4] = (ImageView) findViewById(R.id.img_5);
		
		mArrImgDes[0] = (ImageView) findViewById(R.id.img_des_1);
		mArrImgDes[1] = (ImageView) findViewById(R.id.img_des_2);
		mArrImgDes[2] = (ImageView) findViewById(R.id.img_des_3);
		mArrImgDes[3] = (ImageView) findViewById(R.id.img_des_4);
		mArrImgDes[4] = (ImageView) findViewById(R.id.img_des_5);
		
		ImageLoader imgLoader = AppContext.getApp().getImgLoader();
		
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero1(), EnumDPI.DPI120x120), mArrImg[0]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero2(), EnumDPI.DPI120x120), mArrImg[1]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero3(), EnumDPI.DPI120x120), mArrImg[2]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero4(), EnumDPI.DPI120x120), mArrImg[3]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero5(), EnumDPI.DPI120x120), mArrImg[4]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero1(), EnumDPI.DPI120x120), mArrImgDes[0]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero2(), EnumDPI.DPI120x120), mArrImgDes[1]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero3(), EnumDPI.DPI120x120), mArrImgDes[2]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero4(), EnumDPI.DPI120x120), mArrImgDes[3]);
		imgLoader.displayImage(URLUtil.getURL_HeroImg(mBg.getHero5(), EnumDPI.DPI120x120), mArrImgDes[4]);
		
		mTvDesTitle.setText(mBg.getTitle());
		mTvDes.setText(mBg.getDes());
		mArrTvDes[0].setText(mBg.getDes1());
		mArrTvDes[1].setText(mBg.getDes2());
		mArrTvDes[2].setText(mBg.getDes3());
		mArrTvDes[3].setText(mBg.getDes4());
		mArrTvDes[4].setText(mBg.getDes5());
		
		OnImageClickListener clickListener = new OnImageClickListener();
		
		for(int i = 0; i < 5; i++){
			
			mArrImg[i].setOnClickListener(clickListener);
			mArrImgDes[i].setOnClickListener(clickListener);
		}
		
	}
	
	class OnImageClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.img_1:
			case R.id.img_des_1:
				AppBaseCenter.getInstance().openHeroDetail(BestGroupDetailActivity.this, mBg.getHero1());
				break;
			case R.id.img_2:
			case R.id.img_des_2:
				AppBaseCenter.getInstance().openHeroDetail(BestGroupDetailActivity.this, mBg.getHero2());
				break;
			case R.id.img_3:
			case R.id.img_des_3:
				AppBaseCenter.getInstance().openHeroDetail(BestGroupDetailActivity.this, mBg.getHero3());
				break;
			case R.id.img_4:
			case R.id.img_des_4:
				AppBaseCenter.getInstance().openHeroDetail(BestGroupDetailActivity.this, mBg.getHero4());
				break;
			case R.id.img_5:
			case R.id.img_des_5:
				AppBaseCenter.getInstance().openHeroDetail(BestGroupDetailActivity.this, mBg.getHero5());
				break;

			default:
				break;
			}
		}
	}

	@Override
	protected boolean goBack() {
		return false;
	}
}
