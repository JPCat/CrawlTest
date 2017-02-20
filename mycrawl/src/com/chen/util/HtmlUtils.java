package com.chen.util;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.chen.config.Constant;

public class HtmlUtils
{
	/**
	 * 使用Parser获取网页中指定的内容
	 * 
	 * @param addressUrl
	 * @param encoding
	 * @return
	 */
	public static List<String> getHtmlContentUseParser(String addressUrl,
			String encoding)
	{
		List<String> result = null;
		if (StringUtils.isEmpty(addressUrl)) {
			return result;
		}
		if (StringUtils.isEmpty(encoding)) {
			encoding = "utf-8";
		}
		try {
			result = new ArrayList<String>(); 
			Parser parser = new Parser(addressUrl);
			parser.setEncoding(encoding);

			NodeFilter otherFilter = new AndFilter(new TagNameFilter("h1"),
					new HasAttributeFilter("style", "color: #636363;font-weight: normal;"));
			NodeList otherList = parser.parse(otherFilter);
			SimpleNodeIterator iterator = otherList.elements();
			while(iterator.hasMoreNodes()){
				Node node = iterator.nextNode();
				result.add(node.toPlainTextString().replace("&nbsp;", ""));
			}
			parser.reset();
			
			NodeFilter introFilter = new AndFilter(new TagNameFilter("a"),
					new HasAttributeFilter("class", "l_con"));
			NodeList introList = parser.parse(introFilter);
			iterator = introList.elements();
			while(iterator.hasMoreNodes()){
				Node node = iterator.nextNode();
				result.add(node.toPlainTextString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取网页的编码格式
	 * 
	 * @param url
	 *            网页地址
	 * @return
	 */
	public static String getEncoding(URL url)
	{
		String result = "";
		try {
			CodepageDetectorProxy detector = CodepageDetectorProxy
					.getInstance();
			detector.add(JChardetFacade.getInstance());
			Charset charset = detector.detectCodepage(url);
			result = charset.name();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获取网页内容
	 * 
	 * @param addressUrl
	 *            网页地址
	 * @return
	 */
	public static String getHtmlContent(String addressUrl)
	{
		String result = "";
		if (StringUtils.isEmpty(addressUrl)) {
			return result;
		}
		try {
			URL url = new URL(addressUrl);
			String encoding = getEncoding(url);
			if (StringUtils.isEmpty(encoding)) {
				encoding = "utf-8";
			}
			result = getHtmlContent(addressUrl, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获取网页内容
	 * 
	 * @param addressUrl
	 *            网页地址
	 * @param encoding
	 *            网页的编码格式
	 * @return
	 */
	public static String getHtmlContent(String addressUrl, String encoding)
	{
		String result = "";
		if (StringUtils.isEmpty(addressUrl)) {
			return result;
		}
		if (StringUtils.isEmpty(encoding)) {
			encoding = "utf-8";
		}
		try {
			URL url = new URL(addressUrl);
			InputStreamReader isr = new InputStreamReader(url.openStream(),
					encoding);
			BufferedReader br = new BufferedReader(isr);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				result += temp + Constant.CRLF;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
