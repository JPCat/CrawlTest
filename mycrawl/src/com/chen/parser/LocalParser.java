package com.chen.parser;

import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.chen.config.Constant;
import com.chen.util.StringUtils;

public class LocalParser
{
	/**
	 * 解析某个路径下所有HTML文件，将其转为TXT文件输出到指定路径中
	 * 
	 * @param inputPath
	 * @param outputPath
	 */
	public void parseSohuNews(String inputPath, String outputPath)
	{
		StringUtils su = new StringUtils(inputPath);
		List<String> allPath = su.allPathResult;
		for (String path : allPath) {
			String htmlContent = StringUtils.getContent(path);
			String fileName = StringUtils.getFileNameFromPath(path);
			singleParseSohuNews(htmlContent, outputPath + "/" + fileName
					+ ".txt");
		}
	}

	/**
	 * 从sohu的HTML文件中抽取信息输出到指定文件中
	 * 
	 * @param htmlContent
	 * @param outputPath
	 */
	public void singleParseSohuNews(String htmlContent, String outputPath)
	{
		try {
			Parser parser = Parser.createParser(htmlContent, "gb2312");

			NodeFilter titleFilter = new AndFilter(new TagNameFilter("h1"),
					new HasAttributeFilter("itemprop", "headline"));
			NodeList titleList = parser.parse(titleFilter);
			Node titleNode = titleList.elementAt(0);
			String title = "";
			if (titleNode != null) {
				title = titleNode.toPlainTextString();
			}
			// System.out.println(title);
			parser.reset();

			NodeFilter dateFilter = new AndFilter(new TagNameFilter("div"),
					new HasAttributeFilter("class", "time"));
			NodeList dateList = parser.parse(dateFilter);
			Node dateNode = dateList.elementAt(0);
			String date = "";
			if (dateNode != null) {
				date = dateNode.toPlainTextString();
			}
			// System.out.println(date);
			parser.reset();

			NodeFilter sourceFilter = new AndFilter(new TagNameFilter("span"),
					new HasAttributeFilter("itemprop", "name"));
			NodeList sourceList = parser.parse(sourceFilter);
			Node sourceNode = sourceList.elementAt(0);
			String source = "";
			if (sourceNode != null) {
				source = sourceNode.toPlainTextString();
			}
			// System.out.println(source);
			parser.reset();

			NodeFilter contentFilter = new AndFilter(new TagNameFilter("div"),
					new HasAttributeFilter("itemprop", "articleBody"));
			NodeList contentList = parser.parse(contentFilter);
			Node contentNode = contentList.elementAt(0);
			String content = "";
			if (contentNode != null) {
				content = contentNode.toPlainTextString();
			}
			// System.out.println(content);
			parser.reset();

			String result = title + Constant.CRLF + date + Constant.CRLF
					+ source + Constant.CRLF + content;
			StringUtils.string2File(result, outputPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
