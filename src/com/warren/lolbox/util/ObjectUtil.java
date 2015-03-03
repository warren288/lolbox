package com.warren.lolbox.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.warren.lolbox.model.ICompare;


/**
 * 对象工具类
 * @author yangsheng
 * @date 2014-8-28
 */
public class ObjectUtil {
	
	/**
	 * {@link Map}的字符串表示
	 * @param map
	 * @return 为null则返回 ""
	 */
	public static <K, V> String printMap(Map<K, V> map){
		if(map == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Set<Entry<K, V>> entrySet = map.entrySet();
		for(Entry<K, V> entry : entrySet){
			sb.append(entry.getKey() + ":" + entry.getValue() + ";");
		}
		return sb.toString();
	}
	
	/**
	 * {@link List} 的字符串表示
	 * @param list
	 * @return  为null则返回 ""
	 */
	public static <T> String printList(List<T> list){
		if(list == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(T obj : list){
			sb.append(obj + ",  ");
		}
		return sb.toString();
	}
	
	/**
	 * 打印某一个对象<br>使用反射，打印该对象所有方法的返回值
	 * @param obj
	 * @return
	 */
	public static <T> String printObject(T obj){
		
		if(obj == null){
			return "null";
		}
		
		StringBuilder sb = new StringBuilder();
		
		Method[] methods = obj.getClass().getMethods();
		for(Method method : methods){
			try {
				sb.append( method.getName() + " : " + method.invoke(obj, (Object[]) null) + "; ");
			} catch (IllegalArgumentException e) {
				LogTool.exception(e);
			} catch (IllegalAccessException e) {
				LogTool.exception(e);
			} catch (InvocationTargetException e) {
				LogTool.exception(e);
			}
		}
		
		return sb.toString();
	}
	
	
	
	/**
	 * {@link List}是否为空
	 * @param list
	 * @return	list为 null 或 size() 为0，则返回true；否则返回false。
	 */
	public static <T> boolean isNullOrZero(List<T> list){
		
		return list == null || list.size() == 0;
	}
	
	/**
	 * {@link Map} 是否为空
	 * @param map
	 * @return	map为 null 或 键值对数目 为0，则返回true；否则返回false。
	 */
	public static <K, V> boolean isNullOrZero(Map<K, V> map){
		return map == null || map.isEmpty();
	}
	
	
	/**
	 * 判断两个对象的值是否相等<br>
	 * 判断规则：
	 * 1，如果两者都为null，返回true；
	 * 2，如果两者是同一类型且equals()返回true，则返回true；
	 * 3，其他情况均返回false。
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean isEqual(Object obj1, Object obj2){
		
		if(obj1 == null && obj2 == null){
			return true;
		}
		if(obj1 != null && obj2 != null){
			if( !obj1.getClass().equals(obj2.getClass())){
				return false;
			}
			return obj1.equals(obj2);
		} else {
			return false;
		}
	}
	
	/**
	 * 取对象在数组中的位置，使用专用的自定义比较器
	 * @param list
	 * @param obj
	 * @param comparator
	 * @return
	 */
	public static <K, V> int getIndex(K[] arr, V obj, ICompare<K, V> comparator){
		
		if(arr == null){
			return -1;
		}
		return getIndex(Arrays.asList(arr), obj, comparator);
	}
	
	/**
	 * 取对象在列表中的位置，使用专用的自定义比较器
	 * @param list
	 * @param obj
	 * @param comparator
	 * @return
	 */
	public static <K, V> int getIndex(List<K> list, V obj, ICompare<K, V> comparator){
		
		if(isNullOrZero(list) || obj == null || comparator == null){
			return -1;
		}
		int index = -1;
		int count = list.size();
		for(int i = 0; i < count; i++){
			if(comparator.compare(list.get(i), obj) == 0){
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * 取对象在数组中的位置
	 * @param arr
	 * @param obj
	 * @return 如果参数为 null 或 没找到，则返回 -1；否则返回位置
	 */
	public static <T> int getIndex(T[] arr, T obj){
		if(arr == null){
			return -1;
		}
		return getIndex(Arrays.asList(arr), obj);
	}
	
	/**
	 * 取对象在数组中的位置
	 * @param list
	 * @param obj
	 * @return 如果参数为 null 或 没找到，则返回 -1；否则返回位置
	 */
	public static <T> int getIndex(List<T> list, T obj){
		if(isNullOrZero(list) || obj == null){
			return -1;
		}
		int index = -1;
		int count = list.size();
		for (int i = 0; i < count; i++) {
			if (isEqual(list.get(i), obj)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * 获取指定路径下的DB的所有表名
	 * @param strDBPath
	 * @return
	 */
	public static List<String> getDBTableNames(String strDBPath) {

		List<String> tableNameList = new ArrayList<String>();
		SQLiteDatabase db = SQLiteDatabase.openDatabase(strDBPath, null, SQLiteDatabase.OPEN_READWRITE);
		String sql = "select name from sqlite_master where type = 'table' order by name";
		Cursor cursor = db.rawQuery(sql, null);
//		cursor.moveToFirst();
		while (cursor.moveToNext()) {

			String strTable = cursor.getString(0);
			// 元数据表要排除
			if (!strTable.equalsIgnoreCase("android_metadata")) {
				tableNameList.add(cursor.getString(0));
			}
		}
		db.close();
		return tableNameList;
	}
	
//	public static <T> T getTask(Object obj, T a){
//		if(obj == null){
//			return null;
//		}
//		try{
//			return (T)obj;
//		}catch (ClassCastException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
