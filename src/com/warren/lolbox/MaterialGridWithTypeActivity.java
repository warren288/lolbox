package com.warren.lolbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.warren.lolbox.model.GridParams;
import com.warren.lolbox.model.IListener;
import com.warren.lolbox.model.SimpleNetTool;
import com.warren.lolbox.model.bean.MaterialSimple;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.DuowanConfig.EnumZBType;
import com.warren.lolbox.url.URLUtil;
import com.warren.lolbox.util.StringUtils;

/**
 * 特定分类下的物品列表
 * @author warren
 * @date 2014年12月31日
 */
public class MaterialGridWithTypeActivity extends Activity {

	public static final String EXTRA_ZBTYPE = "EXTRA_ZBTYPE";

	private ImageView mImgLeft;
	private ImageView mImgRight;
	private TextView mTvTitle;

	private GridView mGv;

	private String mStrTitle;
	private EnumZBType mEnumType;

	private BaseGridAdapter mAdapter;
	private List<MaterialSimple> mLstMs;
	private List<SimpleNetTool> mLstSnt;

	private DisplayImageOptions mDisPlayOption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		String strType = getIntent().getStringExtra(EXTRA_ZBTYPE);
		if (StringUtils.isNullOrZero(strType)) {
			mStrTitle = "全部装备";
			mEnumType = EnumZBType.all;
		} else {
			mStrTitle = strType;
			mEnumType = EnumZBType.getZbType(StringUtils.getIndex(EnumZBType.getStringTypeArray(),
						strType));
		}
		initImgOption();
		initCtrl();
	}

	private void initImgOption() {

		// 这里无需设置这么多，一般使用ImageLoader的默认DisplayImageOptions就可以满足需求
		Options opt = new Options();
		opt.inInputShareable = true;
		opt.inPurgeable = true;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inSampleSize = 1;

		mDisPlayOption = new DisplayImageOptions.Builder()
					.considerExifParams(true)
					.bitmapConfig(Config.RGB_565)
					.decodingOptions(opt)
					.displayer(new BitmapDisplayer() {

						@Override
						public void display(Bitmap arg0, ImageAware arg1, LoadedFrom arg2) {
							
							// 由于请求的图片较小，在分辨率较大的设备上显示不美观。这里在显示前先放大到两倍，再显示。
							// 放大到两倍后，经检查大部分手机分辨率都能较好地展示，而放大后的图片对某一分辨率来说太大时，
							// 可通过设置ImageView的ScaleType来将自动将图片缩小。
							
							Matrix matrix = new Matrix();
							matrix.postScale(2, 2); 
							Bitmap bitResize = Bitmap.createBitmap(arg0, 0, 0, arg0.getWidth(),
										arg0.getHeight(), matrix, true);
							arg1.setImageBitmap(bitResize);
						}
					}).cacheInMemory(true).showImageOnLoading(R.drawable.dl_loading_img)
					.imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
	}

	private void initCtrl() {

		mImgLeft = (ImageView) findViewById(R.id.img_title_left);
		mImgRight = (ImageView) findViewById(R.id.img_title_right);
		mTvTitle = (TextView) findViewById(R.id.tv_title);

		mImgLeft.setImageResource(R.drawable.lolbox_titleview_return_default);
		mImgRight.setVisibility(View.GONE);
		mTvTitle.setText(mStrTitle);

		mImgLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mGv = (GridView) findViewById(R.id.grid);
		mGv.setNumColumns(4);

		mAdapter = new BaseGridAdapter(LayoutInflater.from(this));

		GridParams params = GridParams.createNormal();
		params.imgFillRootWidth = true;
		mAdapter.setNumColumn(4);
		mGv.setAdapter(mAdapter);

		// 请求服务器，查询某一类型的物品列表
		AppContext.getApp().getNetManager()
					.get(URLUtil.getURL_ZBLst(mEnumType), new IListener<String>() {

						@Override
						public void onCall(String t) {
							
							if (t == null) {
								Toast.makeText(MaterialGridWithTypeActivity.this, "没有符合条件的物品",
											Toast.LENGTH_SHORT).show();
								return;
							}
							
							// 获得物品列表Json后，转换为所需要的对象列表
							AppContext.getApp().getJsonManager().parseList(t, MaterialSimple.class,
										new IListener<List<MaterialSimple>>() {

											@Override
											public void onCall(List<MaterialSimple> lstMs) {

												if(lstMs == null || lstMs.size() == 0){
													Toast.makeText(MaterialGridWithTypeActivity.this, "没有符合条件的物品",
																Toast.LENGTH_SHORT).show();
													return;
												}
												mLstMs = lstMs;
												// 依据物品id，构造物品的图片地址，在Adapter中将根据该地址获取对应的图片
												mLstSnt = new ArrayList<SimpleNetTool>();
												for (MaterialSimple ms : lstMs) {
													SimpleNetTool snt = new SimpleNetTool(URLUtil
																.getURL_ZBImg(ms.getId(),
																			EnumDPI.DPI64x64), ms
																.getText());
													mLstSnt.add(snt);
												}
												mAdapter.setLstNetTools(mLstSnt, AppContext
															.getApp().getImgLoader(), mDisPlayOption);
												mAdapter.notifyDataSetChanged();
											}

										});
						}
					});
		
		
		mGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				openMaterialDetail("" + mLstMs.get(position).getId());
			}
		});

		
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
