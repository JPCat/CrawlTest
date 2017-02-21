package com.chen.util;

import java.util.List;

import org.junit.Test;

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

	@Test
	public void test2()
	{
		String inputPath = "D:\\20170220";
		StringUtils su = new StringUtils(inputPath);
		List<String> allPathResult = su.allPathResult;
		System.out.println(allPathResult.size());
	}

	@Test
	public void test3()
	{
		String inputPath = "D:\\20170220\\n481140174.shtml";
		String result = StringUtils.getContent(inputPath);
		System.out.println(result);
	}

	@Test
	public void test4()
	{
		String inputPath = "D:\\20170220\\n481140174.shtml";
		String result = StringUtils.getFileEncoding(inputPath);
		System.out.println(result);
	}
	
	@Test
	public void test5(){
		String path = "D:/20170220/n481140174.shtml";
		String result = StringUtils.getFileNameFromPath(path);
		System.out.println(result);
	}
	
	@Test
	public void test6(){
		String str = "我爱你";
		String outputPath = "D:/run/parser/a.txt";
		boolean result = StringUtils.string2File(str, outputPath);
		System.out.println(result);
	}

}
