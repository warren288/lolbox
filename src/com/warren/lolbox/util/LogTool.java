package com.warren.lolbox.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.os.Environment;
import android.util.Log;

/**
 * 保存日志到文件中
 */
public class LogTool {
	
	private static Lock lockLog = new ReentrantLock();
	private static ExecutorService exeService = Executors.newSingleThreadExecutor();
	
	private static final String SYSTEM_ROOT = Environment.getExternalStorageDirectory().getPath();
	private static String strLogRoot = SYSTEM_ROOT + "/LolBox_warren/Log/";
	
	/**
	 * 开始一个日志块
	 * @param strMessage
	 */
	public static void startModule(String strMessage){
		strMessage = strMessage == null ? "" : strMessage;
		saveLog("\n\r\n\r" + strMessage + "\n\r");
	}
	/**
	 * 结束一个日志块
	 * @param strMessage
	 */
	public static void endModule(String strMessage){
		strMessage = strMessage == null ? "" : strMessage;
		saveLog("\n\r" + strMessage + "\n\r\n\r");
	}
	
	
	/**
	 * 保存日志，同时在LogCat中打印一条 warn 日志
	 * @param strTag		日志标签
	 * @param strMessage	日志内容
	 */
	public static void w(String strTag, String strMessage){
		saveLog("级别：w " + "，标签：" + strTag + "， 信息：" + strMessage);
		Log.w(strTag, strMessage);
	}
	/**
	 * 保存日志，同时在LogCat中打印一条 error 日志
	 * @param strTag		日志标签
	 * @param strMessage	日志内容
	 */
	public static void e(String strTag, String strMessage){
		saveLog("级别：e " + "， 标签：" + strTag + "， 信息：" + strMessage);
		Log.e(strTag, strMessage);
	}
	/**
	 * 保存日志，同时在LogCat中打印一条 info 日志
	 * @param strTag		日志标签
	 * @param strMessage	日志内容
	 */
	public static void i(String strTag, String strMessage){
		saveLog("级别：i " + "， 标签：" + strTag + "， 信息：" + strMessage);
		Log.i(strTag, strMessage);
	}
	
	/**
	 * 保存日志，同时在LogCat中打印其调用栈
	 * @param e	异常信息
	 */
	public static void exception(Throwable e){
		saveLog("级别：exception " + "， 标签：" + "异常" +  ", 信息： " + StringUtils.getThrowable(e));
		e.printStackTrace();
	}
	
	/**
	 * 保存日志，使用默认路径，每天的日志分开
	 * @param strText
	 */
	public static void saveLog(String strText){
		
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		String strDate = format.format(calendar.getTime());
		
		String strFilePath = strLogRoot + "log" + strDate + ".txt";
		saveLogInner(strFilePath, strText);
	}
	
	/**
	 * 普通日志
	 * @param strFilePath 	日志路径
	 * @param strText		日志内容
	 */
	public static void saveLog(final String strFilePath, final String strText){
		
		saveLogInner(strFilePath, strText);
	}
	
	
	/**
	 * 2013.11.27 by yangsheng   保存日志
	 * @param strFilePath
	 * @param strText
	 */
	private static void saveLogInner(final String strFilePath, final String strText){

		exeService.execute(new Runnable() {
			
			@Override
			public void run() {
				lockLog.lock();
				
				try {
					File file = new File(strFilePath);
					File fileParent = file.getParentFile();
					if( ! fileParent.exists()){
						fileParent.mkdirs();
					}
					if(! file.exists()){
						file.createNewFile();
					}
					FileWriter filewrt = new FileWriter(file, true);
					String strTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA).format(new Date());
					
					String strAdd = strTime+ " : " + strText + "\r\n";

					filewrt.append(strAdd);
					filewrt.flush();
					
					filewrt.close();
				} catch (IOException e) {
					
				} finally{
					lockLog.unlock();
				}
			}
		});
	}
}
