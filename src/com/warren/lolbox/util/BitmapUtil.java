package com.warren.lolbox.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.ExifInterface;

/**
 * 压缩工具类，暂时只支持图片压缩，由于比较耗时，压缩操作应放在非UI线程中执行。
 */
public class BitmapUtil {

	/**
	 * 默认压缩的目标输出图片大小为100KB
	 */
	public static final int PIC_OUT_SIZE = 102400;

	/**
	 * 读取图片属性：旋转的角度
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 旋转图片
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();;
		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/**
	 * 压缩图片，返回压缩后图片的字节数组，如果输入路径与输出路径相同，则输出文件会覆盖输入文件。
	 * @param strInPath 输入路径
	 * @param strOutPath 输出路径
	 * @return
	 */
	public static byte[] compressBitmap(String strInPath, String strOutPath) {

		if (strInPath == null || strInPath.length() == 0 || strOutPath == null
					|| strOutPath.length() == 0) {
			return null;
		}

		Bitmap bitmapBound = compressPicBound(strInPath);
		byte[] bytes = compressPicQualityToBytes(bitmapBound);

		File fileOut = new File(strOutPath);
		if (fileOut.exists()) {
			fileOut.delete();
		}

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileOut);
			fos.write(bytes);
			fos.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static Bitmap compressBitmap(String strInPath) {

		if (strInPath == null || strInPath.length() == 0) {
			return null;
		}
		Bitmap bitmapBound = compressPicBound(strInPath);
		Bitmap bitmapQuality = compressPicQuality(bitmapBound);
		return bitmapQuality;
	}

	/**
	 * 图片大小压缩
	 * @param strInPath
	 * @return
	 */
	public static Bitmap compressPicBound(String strInPath) {

		Options optBound = new Options();
		optBound.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(strInPath, optBound);

		int widthSrc = optBound.outWidth;
		int heightSrc = optBound.outHeight;

		int widthOut = 800;
		int heightOut = 480;

		int boundRatio = calculateInSampleSize(widthSrc, heightSrc, widthOut, heightOut);

		Options optAct = new Options();
		optAct.inSampleSize = boundRatio;
		optAct.inJustDecodeBounds = false;
		optAct.inPreferredConfig = Bitmap.Config.RGB_565;

		Bitmap bitmap = BitmapFactory.decodeFile(strInPath, optAct);

		return bitmap;
	}

	/**
	 * 图片大小压缩
	 * @param strInPath
	 * @return
	 */
	public static Bitmap decodeResource(Resources res, int drawable, int widthOut, int heightOut) {

		Options optBound = new Options();
		optBound.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, drawable, optBound);

		int widthSrc = optBound.outWidth;
		int heightSrc = optBound.outHeight;

		int boundRatio = calculateInSampleSize(widthSrc, heightSrc, widthOut, heightOut);

		Options optAct = new Options();
		optAct.inSampleSize = boundRatio;
		optAct.inJustDecodeBounds = false;
		optAct.inPreferredConfig = Bitmap.Config.RGB_565;

		Bitmap bitmap = BitmapFactory.decodeResource(res, drawable, optAct);
		return bitmap;
	}

	/**
	 * 图片质量压缩
	 * @param bitmap
	 * @return
	 */
	public static Bitmap compressPicQuality(Bitmap bitmap) {

		int byteCount = bitmap.getRowBytes() * bitmap.getHeight();
		int ratio = byteCount / PIC_OUT_SIZE;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (ratio < 1) {
			ratio = 100;

			bitmap.compress(CompressFormat.JPEG, ratio, baos);
		} else {
			ratio = 90;
			bitmap.compress(CompressFormat.JPEG, ratio, baos);
			while (baos.toByteArray().length / 1024 > 100) {
				// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				bitmap.compress(Bitmap.CompressFormat.JPEG, ratio, baos);
				ratio -= 10;// 每次都减少10
			}
		}

		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());

		Bitmap bitmapOut = BitmapFactory.decodeStream(isBm);
		return bitmapOut;
	}

	/**
	 * 图片质量压缩
	 * @param bitmap
	 * @return
	 */
	public static byte[] compressPicQualityToBytes(Bitmap bitmap) {

		if (bitmap == null) {
			return null;
		}

		int byteCount = bitmap.getRowBytes() * bitmap.getHeight();

		int ratio = byteCount / PIC_OUT_SIZE;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if (ratio < 1) {

			ratio = 100;
			bitmap.compress(CompressFormat.JPEG, ratio, baos);
		} else {

			ratio = 90;
			bitmap.compress(CompressFormat.JPEG, ratio, baos);
			while (baos.toByteArray().length / 1024 > 100) {
				// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				bitmap.compress(Bitmap.CompressFormat.JPEG, ratio, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				ratio -= 10;// 每次都减少10
			}
		}
		return baos.toByteArray();
	}

	// private Bitmap compressImage(Bitmap image) {
	//
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	// int options = 100;
	// while ( baos.toByteArray().length / 1024>100) {
	// //循环判断如果压缩后图片是否大于100kb,大于继续压缩
	// baos.reset();//重置baos即清空baos
	// image.compress(Bitmap.CompressFormat.JPEG, options,
	// baos);//这里压缩options%，把压缩后的数据存放到baos中
	// options -= 10;//每次都减少10
	// }
	// ByteArrayInputStream isBm = new
	// ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
	// Bitmap bitmap = BitmapFactory.decodeStream(isBm, null,
	// null);//把ByteArrayInputStream数据生成图片
	// return bitmap;
	// }

	private static int calculateInSampleSize(int widthSrc, int heightSrc, int widthOut,
				int heightOut) {

		int inSampleSize = 1;

		if (heightSrc > heightOut || widthSrc > widthOut) {
			int heightRatio = Math.round((float) heightSrc / (float) heightOut);
			int widthRatio = Math.round((float) widthSrc / (float) widthOut);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 在图片上增加文字示例
	 * @param bitmapSrc
	 * @param str
	 * @return
	 */
	public static Bitmap drawNewBitmap(Bitmap bitmapSrc, String str) {

		int width = bitmapSrc.getWidth();
		int hight = bitmapSrc.getHeight();
		System.out.println("宽" + width + "高" + hight);
		Bitmap icon = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888); // 建立一个空的BItMap
		Canvas canvas = new Canvas(icon);// 初始化画布绘制的图像到icon上

		Paint photoPaint = new Paint(); // 建立画笔
		photoPaint.setDither(true); // 获取跟清晰的图像采样
		photoPaint.setFilterBitmap(true);// 过滤一些

		Rect src = new Rect(0, 0, bitmapSrc.getWidth(), bitmapSrc.getHeight());// 创建一个指定的新矩形的坐标
		Rect dst = new Rect(0, 0, width, hight);// 创建一个指定的新矩形的坐标
		canvas.drawBitmap(bitmapSrc, src, dst, photoPaint);// 将photo 缩放或则扩大到
		// dst使用的填充区photoPaint

		Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
		textPaint.setTextSize(20.0f);// 字体大小
		textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
		textPaint.setColor(Color.RED);// 采用的颜色
		canvas.drawText(str, 20, 26, textPaint);// 绘制上去字，开始未知x,y采用那只笔绘制
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return icon;
	}

	/**
	 * 图片保存到指定路径
	 * @param bitmap
	 * @param strPath
	 * @return
	 */
	public static boolean saveToFile(Bitmap bitmap, String strPath) {

		if (bitmap == null || StringUtils.isNullOrZero(strPath)) {
			return false;
		}
		File file = new File(strPath);
		if (file.exists()) {
			file.delete();
		}
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(strPath));
			// 2014.1.5 改用JPEG压缩格式，保存照片变快
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
			os.flush();
			os.close();
			return true;
		} catch (Exception e) {
			LogTool.exception(e);
		}
		return false;

	}

}
