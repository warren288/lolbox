package com.warren.lolbox.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;


/**
 * 设备相关的工具
 * @author yangsheng
 * @date 2014年9月30日
 */
public class DeviceUtil {

	/**
	 * 取屏幕大小
	 * @param activity
	 * @return PointD ，其 {@link PointD#x} 为屏幕宽度， {@link PointD#y} 为屏幕高度
	 */
	public static Point getWindowSize(Activity activity) {

		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return new Point(dm.widthPixels, dm.heightPixels);

	}
	
	/**
	 * 屏幕像素点转换，dp 转换为 px
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2Px(Context context, float dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/**
	 * 取设备 IMEI 号
	 * @param context
	 * @return 如果获取失败，则返回 <code>null</code>
	 */
	public static String getTelePhonyDeviceId(Context context) {

		TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Activity.TELEPHONY_SERVICE);
		String strSysId = tm.getDeviceId();

		return strSysId;
	}

	/**
	 * 判断设备是手机还是平板 手机 则return false; 平板 则 return true。判断依据是屏幕分辨率 720x960 <br>
	 * @param context
	 * @return
	 */
	public static boolean isTabletDevice(Context context) {

		if (android.os.Build.VERSION.SDK_INT >= 11) {
			
			Configuration con = context.getResources().getConfiguration();
			
			try {
				boolean b = con.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE);
				return b;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
