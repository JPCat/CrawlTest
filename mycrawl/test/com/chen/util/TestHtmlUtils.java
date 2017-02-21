package com.chen.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.chen.util.HtmlUtils;

public class TestHtmlUtils
{
	@Test
	public void test1()
	{
		String addressUrl = "http://www.mzhu8.com/mulu/6/1.html";
		String encoding = "gbk";
		String result = HtmlUtils.getHtmlContent(addressUrl, encoding);
		System.out.println(result);
	}

	@Test
	public void test2() throws MalformedURLException
	{
		String addressUrl = "http://www.mzhu8.com/mulu/6/1.html";
		URL url = new URL(addressUrl);
		String result = HtmlUtils.getEncoding(url);
		System.out.println(result);
	}

	@Test
	public void test3()
	{
		String addressUrl = "http://news.sohu.com/20170220/n481190326.shtml";
		String encoding = "gbk";
		String result = HtmlUtils.getHtmlContentUseParser(addressUrl, encoding);
		System.out.println(result);
	}

}
