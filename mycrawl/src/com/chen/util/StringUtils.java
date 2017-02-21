package com.chen.util;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chen.config.Constant;

public class StringUtils
{
	public List<String> allPathResult = new ArrayList<String>();

	public StringUtils(String inputPath)
	{
		getAllPath(inputPath);
	}

	/**
	 * 将字符串输入到指定文件中
	 * 
	 * @param str
	 * @param outputPath
	 * @return
	 */
	public static boolean string2File(String str, String outputPath)
	{
		boolean b = false;
		if (isEmpty(outputPath)) {
			return b;
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(outputPath);
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	/**
	 * 根据路径获取文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileNameFromPath(String path)
	{
		String result = "";
		if (isEmpty(path)) {
			return result;
		}
		if (path.contains("\\")) {
			result = path.substring(path.lastIndexOf("\\") + 1,
					path.lastIndexOf("."));
		} else if (path.contains("/")) {
			result = path.substring(path.lastIndexOf("/") + 1,
					path.lastIndexOf("."));
		} else {
			result = path;
		}
		return result;
	}

	/**
	 * 获取文件的内容
	 * 
	 * @param inputPath
	 * @return
	 */
	public static String getContent(String inputPath)
	{
		String result = "";
		if (isEmpty(inputPath)) {
			return result;
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(inputPath);
			String encoding = StringUtils.getFileEncoding(inputPath);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(isr);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				result += temp + Constant.CRLF;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取文件的编码格式
	 * 
	 * @param inputPath
	 * @return
	 */
	public static String getFileEncoding(String inputPath)
	{
		String result = "";
		try {
			CodepageDetectorProxy detector = CodepageDetectorProxy
					.getInstance();
			detector.add(JChardetFacade.getInstance());
			Charset charset = detector.detectCodepage(new File(inputPath)
					.toURI().toURL());
			result = charset.name();
			if (isEmpty(result)) {
				result = "utf-8";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得某个路径下所有文件的绝对路径
	 * 
	 * @param inputPath
	 */
	public void getAllPath(String inputPath)
	{
		try {
			File file = new File(inputPath);
			if (!file.exists()) {
				return;
			}
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					getAllPath(f.getAbsolutePath());
				} else {
					allPathResult.add(f.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
