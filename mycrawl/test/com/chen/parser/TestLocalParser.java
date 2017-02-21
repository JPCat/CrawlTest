package com.chen.parser;

import org.junit.Test;

public class TestLocalParser
{
	@Test
	public void test1(){
		LocalParser lp = new LocalParser();
		String inputPath = "D:\\run\\20170220";
		String outputPath = "D:\\run\\parser";
		lp.parseSohuNews(inputPath, outputPath);
		
	}

}
