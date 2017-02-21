package com.chen.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.chen.config.Constant;

public class SplitWordsUtils
{
	public static String ikSplit(String str)
	{
		String result = "";
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		InputStream is = null;
		Reader reader = null;
		try {
			byte[] bt = str.getBytes();
			is = new ByteArrayInputStream(bt);
			reader = new InputStreamReader(is);
			IKSegmenter iks = new IKSegmenter(reader, true);
			Lexeme lexeme = null;
			while ((lexeme = iks.next()) != null) {
				result += lexeme.getLexemeText()+lexeme.getLexemeTypeString() + Constant.DOUBLE_BLANK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

}
