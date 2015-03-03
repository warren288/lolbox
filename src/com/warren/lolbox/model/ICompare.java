package com.warren.lolbox.model;

/**
 * 比较器接口，允许比较两个不同类型的对象的大小，比较规则由 {@link #compare(Object, Object)}定义
 * @author yangsheng
 * @date 2014年10月28日
 * @param <K>
 * @param <V>
 */
public interface ICompare<K, V> {
	/**
	 * 比较器
	 * @param k
	 * @param v
	 * @return
	 */
	public int compare(K k, V v);
}
