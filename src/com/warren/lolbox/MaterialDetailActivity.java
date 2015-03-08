package com.warren.lolbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.bean.Material;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.DeviceUtil;
import com.warren.lolbox.util.StringUtils;
import com.warren.lolbox.widget.TitleBar;

/**
 * 物品详情
 * @author warren
 * @date 2014年12月31日
 */
public class MaterialDetailActivity extends Activity {

	public static final String EXTRA_MATERIALID = "EXTRA_MATERIALID";
	private TitleBar mTb;

	private LinearLayout mLlRoot;
	private ImageView mImgMaterial;
	private TextView mTvMaterialName;
	private TextView mTvMaterialPrice;
	private TextView mTvDescription;
	private LinearLayout mLlNeed;
	private LinearLayout mLlCompose;

	private ImageLoader mImgLoader;

	private String mStrMaterialId;

	private Material mMaterial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materialdetail);

		mImgLoader = AppContext.getApp().getImgLoader();

		mStrMaterialId = getIntent().getStringExtra(EXTRA_MATERIALID);

		initCtrl();
	}

	private void initCtrl() {

		mTb = (TitleBar) findViewById(R.id.titlebar);

		mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
		mLlNeed = (LinearLayout) findViewById(R.id.ll_need);
		mLlCompose = (LinearLayout) findViewById(R.id.ll_compose);

		mImgMaterial = (ImageView) findViewById(R.id.img_material);
		mTvMaterialName = (TextView) findViewById(R.id.tv_materialname);
		mTvMaterialPrice = (TextView) findViewById(R.id.tv_materialmoney);
		mTvDescription = (TextView) findViewById(R.id.tv_materialdescription);

		if (mStrMaterialId == null) {
			setNullHint();
			return;
		}

		// 请求服务器获取物品详情Json
		AppContext.getApp()
					.getNetManager()
					.get(URLUtil.getURL_ZBDetail(Integer.parseInt(mStrMaterialId)),
								new IListener<String>() {

									@Override
									public void onCall(String strJson) {

										strJson = checkMaterialJson(strJson);

										// 获取到Json后解析成 Material实体
										AppContext.getApp()
													.getJsonManager()
													.parse(strJson, Material.class,
																new IListener<Material>() {

																	@Override
																	public void onCall(
																				Material material) {

																		if (material == null) {
																			setNullHint();
																			return;
																		}
																		mMaterial = material;
																		setView();
																	}

																});

									}
								});

	}

	/**
	 * 纠正服务器传回的Json。
	 * @description
	 *              请求生命药水、法力药水等“没有需求物品也没有合成物品”的工具类物品的物品详情时，服务器返回的Json中，
	 *              extAtts的值是 "extAttrs":[] 格式，这与Material
	 *              类的extAttrs所需求的Map格式不符，需矫正，否则会解析失败。
	 * @param strJson
	 * @return
	 */
	private String checkMaterialJson(String strJson) {

		if (strJson.contains("\"extAttrs\":[]")) {
			strJson = strJson.replace("\"extAttrs\":[]", "\"extAttrs\":{}");
		}
		return strJson;
	}

	/**
	 * 依据物品详情设置界面
	 */
	private void setView() {

		mTvDescription.setText(mMaterial.getDescription());
		mTvMaterialName.setText(mMaterial.getName());
		mTvMaterialPrice.setText("价格:" + mMaterial.getPrice() + " 总价:" + mMaterial.getAllPrice()
					+ " 售价:" + mMaterial.getSellPrice());

		mImgLoader.displayImage(
					URLUtil.getURL_ZBImg(Integer.parseInt(mMaterial.getId()), EnumDPI.DPI64x64),
					mImgMaterial);

		String[] arrNeedId = mMaterial.getNeed() == null ? null : mMaterial.getNeed().split(",");
		String[] arrComposeId = mMaterial.getCompose() == null ? null : mMaterial.getCompose()
					.split(",");

		if (arrNeedId != null) {
			for (String strNeedId : arrNeedId) {
				if (StringUtils.isNullOrZero(strNeedId)) {
					continue;
				}
				mLlNeed.addView(createMaterialImg(strNeedId));
			}
		}
		if (arrComposeId != null) {
			for (String strComposeId : arrComposeId) {
				if (StringUtils.isNullOrZero(strComposeId)) {
					continue;
				}
				mLlCompose.addView(createMaterialImg(strComposeId));
			}
		}

	}

	/**
	 * 设置查找物品详情失败的提示
	 */
	private void setNullHint() {

		mLlRoot.removeAllViews();
		TextView tvHint = new TextView(this);
		tvHint.setText("查找物品详情失败");
		mLlRoot.addView(tvHint);
	}

	/**
	 * 根据物品ID构建对应的ImageView，所需图片从服务器请求，点击事件也已在这里设置。
	 * @param materialId
	 * @return
	 */
	private ImageView createMaterialImg(final String materialId) {

		ImageView img = new ImageView(this);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DeviceUtil.dp2Px(this, 40), DeviceUtil.dp2Px(this, 40));
		params.leftMargin = DeviceUtil.dp2Px(this, 2);
		params.rightMargin = DeviceUtil.dp2Px(this, 2);

		img.setLayoutParams(params);

		mImgLoader.displayImage(
					URLUtil.getURL_ZBImg(Integer.parseInt(materialId), EnumDPI.DPI64x64), img);

		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				openMaterialDetail(materialId);
			}
		});

		return img;
	}

	/**
	 * 打开物品详情界面
	 * @param materialId
	 */
	private void openMaterialDetail(String materialId) {

		Intent it = new Intent(this, MaterialDetailActivity.class);
		it.putExtra(MaterialDetailActivity.EXTRA_MATERIALID, materialId);
		startActivity(it);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

}
