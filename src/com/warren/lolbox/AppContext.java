package com.warren.lolbox;

import java.io.File;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * 全局程序类
 * @author warren
 * @date 2014年12月30日
 */
public class AppContext extends Application {

	private static AppContext app;

	private ImageLoader imgLoader;
	private AppNetManager netManager;
	private AppJsonParserManager jsonManager;

	public static final String UIL_CACHEFOLDER = Environment.getExternalStorageDirectory()
				+ "/warrenlol/imageloader/cache/";
	public static final String PICTURE_FOLDER = Environment.getExternalStorageDirectory()
				+ "/warrenlol/picture/";

	public static AppContext getApp() {
		return app;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		init();
	}

	private void init() {
		initFolder();
		
		imgLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration imgLoaderConfig = new ImageLoaderConfiguration.Builder(app)
					.diskCache(new UnlimitedDiscCache(new File(UIL_CACHEFOLDER))).threadPoolSize(5)
					.memoryCacheSize(3 * 1024 * 1024)
					.imageDownloader(new BaseImageDownloader(this, 10 * 1000, 60 * 1000)).build();
		imgLoader.init(imgLoaderConfig);

		netManager = AppNetManager.getInstance();
		jsonManager = AppJsonParserManager.getInstance();
	}
	
	private void initFolder(){
		File fileImageLoader = new File(UIL_CACHEFOLDER);
		if( ! fileImageLoader.exists()){
			fileImageLoader.mkdirs();
		}
		File filePicture = new File(PICTURE_FOLDER);
		if(! filePicture.exists()){
			filePicture.mkdirs();
		}
	}

	public ImageLoader getImgLoader() {
		return this.imgLoader;
	}

	public AppNetManager getNetManager() {
		return netManager;
	}

	public AppJsonParserManager getJsonManager() {
		return jsonManager;
	}
}
