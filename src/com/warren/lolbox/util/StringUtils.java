package com.warren.lolbox.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 字符串工具类
 * @author yangsheng
 */
public final class StringUtils {

	/**
	 * 字符串是否为空或者长度为0
	 * @param str
	 * @return
	 */
	public static boolean isNullOrZero(final String str) {

		return str == null || str.length() == 0;
	}

	/**
	 * 字符串是否是空白
	 * @param str
	 * @return 如果 字符串为 null 或长度为 0， 或全部由空白(包括)组成，则返回true；否则返回false。
	 */
	public static boolean isBlank(final String str) {
		if (isNullOrZero(str)) {
			return true;
		}
		String strRegular = "\\s{1,}";
		return str.matches(strRegular);
	}

	/**
	 * 确保String不为null，如果传入的参数不为null，则返回原字符串，否则返回""字符串
	 * @param str
	 * @return
	 */
	public static String getNoNullString(final String str) {
		return str == null ? "" : str;
	}

	/**
	 * 取 子串在母串中的所有index
	 * @param strSource
	 * @param strSearch
	 * @return 取 子串在母串中的所有index，如果母串中子串一次都没有出现，则返回size()为 0 的列表
	 */
	public static List<Integer> getIndexs(final String strSource, final String strSearch) {

		List<Integer> indexList = new ArrayList<Integer>();
		int index = strSource.indexOf(strSearch);
		if (index < 0) {
			return indexList;
		}
		indexList.add(index);
		while (index >= 0) {

			int index2 = strSource.substring(index + 1).indexOf(strSearch);
			if (index2 >= 0) {
				indexList.add(index + index2 + 1);
				index = index + index2 + 1;
			} else {
				index = index2;
			}
		}
		return indexList;
	}

	/**
	 * 异常信息转化为字符串
	 * @param ex
	 * @return
	 */
	public static String getThrowable(final Throwable ex) {

		if (ex == null) {
			return null;
		}
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		ex.printStackTrace(pw);
		Throwable cause = ex.getCause();

		while (cause != null) {
			cause.printStackTrace(pw);
			cause = cause.getCause();
		}
		pw.close();
		String result = writer.toString();
		return result;
	}

	/**
	 * 比较两个字符串是否相同
	 * @param str1
	 * @param str2
	 * @param bCaseSensity 是否大小写敏感
	 * @return 特殊情况处理：如果 str1 和 str2 均为空，返回true
	 */
	public static boolean equal(final String str1, final String str2, final boolean bCaseSensity) {

		if (str1 == null && str2 == null) {
			return true;
		} else if (str1 == null || str2 == null) {
			return false;
		}
		if (bCaseSensity) {
			return str1.equals(str2);
		} else {
			return str1.equalsIgnoreCase(str2);
		}
	}

	/**
	 * 由文件路径取文件名
	 * @param strFilePath 文件路径
	 * @param isWithSuffix 返回值中是否附带文件后缀
	 * @return
	 */
	public static String getFileNameFromPath(final String strFilePath, final boolean isWithSuffix) {

		if (isNullOrZero(strFilePath)) {
			return null;
		}
		int indexStart = strFilePath.lastIndexOf(File.separator) + 1;
		if (isWithSuffix) {
			return strFilePath.substring(indexStart);
		} else {
			int indexEnd = strFilePath.lastIndexOf(".");
			if (indexEnd < 0) {
				indexEnd = strFilePath.length();
			}
			return strFilePath.substring(indexStart, indexEnd);
		}
	}

	/**
	 * 由文件夹路径取文件夹名称
	 * @param strFilePath
	 * @param isWithSuffix
	 * @return
	 */
	public static String getDirectoryNameFromPath(final String strFilePath) {

		if (isNullOrZero(strFilePath)) {
			return null;
		}

		int indexStart = -1;
		int indexEnd = -1;

		if (strFilePath.endsWith("/")) {

			indexEnd = strFilePath.length() - 1;
			String strFilePathSubed = strFilePath.substring(0, strFilePath.length() - 1);
			indexStart = strFilePathSubed.lastIndexOf("/");
		} else {
			indexEnd = strFilePath.length();
			indexStart = strFilePath.lastIndexOf("/");
		}
		return strFilePath.substring(indexStart + 1, indexEnd);
	}

	/**
	 * 取同时存在于 strLstFrom 和 strLstForFind 中的字符串
	 * @param strLstFrom
	 * @param strLstForFind
	 * @return 如果参数为 null或没有找到符合条件的字符串 ，则返回 null；如果有多条符合条件的字符串，返回第一项。
	 */
	public static String getMix(final List<String> strLstFrom, final List<String> strLstForFind) {

		if (strLstFrom == null || strLstForFind == null) {
			return null;
		}
		for (String strFrom : strLstFrom) {
			for (String strFind : strLstForFind) {
				if (equal(strFrom, strFind, false)) {
					return strFind;
				}
			}
		}
		return null;
	}

	/**
	 * 在strArray中查找strFind的位置，只返回第一次出现的位置，不区分大小写
	 * @param strArray
	 * @param strFind
	 * @return 如果输入值不合法或没有找到，返回-1；
	 */
	public static int getIndex(final String[] strArray, final String strFind) {

		return getIndex(Arrays.asList(strArray), strFind);
	}

	/**
	 * 在strArray中查找strFind的位置，只返回第一次出现的位置，不区分大小写
	 * @param strList
	 * @param strFind
	 * @return 如果输入值不合法或没有找到，返回-1；
	 */
	public static int getIndex(final List<String> strList, final String strFind) {

		if (strList == null || strList.size() == 0 || isNullOrZero(strFind)) {
			return -1;
		}
		int index = -1;
		int count = strList.size();
		for (int i = 0; i < count; i++) {
			if (equal(strList.get(i), strFind, true)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 将字符串转换为字符串列表
	 * @param strSource 源字符串
	 * @param strSplit 字符串的分隔符
	 * @return
	 */
	public static List<String> getStrListFromString(final String strSource, final String strSplit) {

		if (isNullOrZero(strSource) || isNullOrZero(strSplit)) {
			return new ArrayList<String>();
		}

		List<String> strLst = new ArrayList<String>();

		String[] strArray = strSource.split(strSplit);
		strLst.addAll(Arrays.asList(strArray));

		return strLst;
	}

	/**
	 * 将字符串转换为字符串列表
	 * @param strSource 源字符串
	 * @param strSplit 字符串的分隔符
	 * @param bDelEmpty 是否删除空项
	 * @return
	 */
	public static List<String> getStrListFromString(final String strSource, final String strSplit,
				final boolean bDelEmpty) {

		if (isNullOrZero(strSource) || isNullOrZero(strSplit)) {
			return new ArrayList<String>();
		}

		List<String> strLst = new ArrayList<String>();

		String[] strArray = strSource.split(strSplit);
		for (String str : strArray) {
			if (str.length() != 0) {
				strLst.add(str);
			}
		}

		return strLst;
	}

	/**
	 * 将字符串数组转换为字符串
	 * @param strArrSource 源字符串数组
	 * @param strSplit 字符串的分隔符
	 * @return
	 */
	public static String createStringFromStrArr(final String[] strArrSource, final String strSplit) {

		List<String> strList = Arrays.asList(strArrSource);
		return createStringFromStrList(strList, strSplit);
	}

	/**
	 * 将字符串列表转换为字符串
	 * @param strListSource 源字符串列表
	 * @param strSplit 字符串的分隔符
	 * @return 如果参数非法，则返回 ""
	 */
	public static String createStringFromStrList(final List<String> strListSource,
				final String strSplit) {

		if (strListSource == null || strListSource.size() == 0 || isNullOrZero(strSplit)) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (String str : strListSource) {
			sb.append(str);
			sb.append(strSplit);
		}

		String strOut = sb.toString();

		return strOut.substring(0, strOut.length() - 1);

	}

	/**
	 * 将输入字符串按照每行字数进行重组
	 * 该方法会去除头尾的空格
	 * @param str 输入字符串
	 * @param colums 每行字数
	 * @return str为null或长度为0，则返回 ""
	 */
	public static String strHuanhang(final String str, final int colums) {

		if (isNullOrZero(str)) {
			return "";
		}
		String input = str;
		String output = new String("");

		int length = input.length();
		while (length > 10) {
			output = output + input.substring(0, 10) + "\r\n";
			input = input.substring(10);
			length = length - 10;
		}
		output = output + input;

		return output.trim();
	}

	/**
	 * 保留小数点后n位数<br>
	 * @param d 双精度浮点数
	 * @param n 需要保留的小数点位数
	 * @return 返回一个保留小数点后n位的数字的字符串
	 */
	public static String keepDecimal(final Double d, final int n) {

		String strOut = String.format("%." + n + "f", d);
		return strOut;
	}

	/**
	 * {@link Map}的字符串表示
	 * @param map
	 * @return 为null则返回 ""
	 */
	public static <K, V> String printMap(Map<K, V> map) {
		return ObjectUtil.printMap(map);
	}

	/**
	 * {@link List} 的字符串表示
	 * @param list
	 * @return 为null则返回 ""
	 */
	public static <T> String printList(List<T> list) {
		return ObjectUtil.printList(list);
	}

}
