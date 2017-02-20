package com.chen.test;

import org.junit.Test;

import com.chen.util.HtmlUtils;
import com.chen.util.StringUtils;

public class TestStringUtils
{
	@Test
	public void test1()
	{
		String regexString = "<h1 itemprop=\"headline\">(.*)</h1>";
		String addressUrl = "http://news.sohu.com/20170219/n481118854.shtml";
		String sourceString = HtmlUtils.getHtmlContent(addressUrl);
		String result = StringUtils.getContentUseRegex(regexString,
				sourceString);
		System.out.println(result);
	}

}
