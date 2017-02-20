package com.chen.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
	/**
	 * 获取网页中匹配正则表达式的内容
	 * 
	 * @param regexString
	 * @param sourceString
	 * @return
	 */
	public static String getContentUseRegex(String regexString,
			String sourceString)
	{
		String result = "";
		if (isEmpty(regexString) || isEmpty(sourceString)) {
			return result;
		}
		try {
			Pattern pattern = Pattern.compile(regexString);
			Matcher matcher = pattern.matcher(sourceString);
			while (matcher.find()) {
				result = matcher.group(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		boolean b = false;
		if (null == str || "".equals(str)) {
			b = true;
		}
		return b;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		boolean b = false;
		if (null != str && !"".equals(str)) {
			b = true;
		}
		return b;
	}

}
