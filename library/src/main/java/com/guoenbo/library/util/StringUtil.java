package com.guoenbo.library.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringUtil {

	/**
	 * 判断Object是否为null
	 *
	 * @param obj
	 * @return null true
	 */
	public static boolean isEmpty(Object obj) {
		if (null != obj) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否为null
	 *
	 * @param str
	 * @return null true
	 */
	public static boolean isEmpty(String str) {
		if (null != str && !str.trim().equals("") && !str.equals("null")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断list是否为null
	 *
	 * @param list
	 * @return null true
	 */
	public static boolean isEmpty(List<?> list) {
		if (null != list && list.size() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断list是否为null
	 *
	 * @param map
	 * @return null true
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		if (null != map && !map.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 返回空值
	 * */
	public static String isEmptyToString(String str) {
		String s = (isEmpty(str) ? "" : str.trim());
		return s;
	}

	/**
	 * 返回空值
	 * */
	public static String isEmptyToString(Object obj) {
		String s = (isEmpty(obj) ? "" : String.valueOf(obj));
		return s;
	}

	public static String isEmptyToString(String str, String defaultValue) {
		String s = (isEmpty(str) ? defaultValue : str.trim());
		return s;
	}

	public static String isNoEmptyToString(String str, String defaultValue) {
		String s = (isEmpty(str) ? "" : defaultValue);
		return s;
	}

	/**
	 * 把String按分隔符转换为List<String>
	 *
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static List<String> StringToListString(String str, String split) {
		List<String> list = new ArrayList<String>();
		if (!StringUtil.isEmpty(str)) {
			String[] strs = str.split(split);
			for (String string : strs) {
				list.add(string);
			}
		}

		return list;
	}

	public static String[] split(String str, String split){
		String[] string = null;
		if(!StringUtil.isEmpty(str)){
			string = str.split(split);
		}
		return string;
	}
	
}
