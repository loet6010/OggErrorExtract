package com.oggerror.extract.dispose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author liurh
 * @date   2016年2月4日
 * @intro  对ggserr.log中ERROR信息进行处理
 *
 */
public class LogAnalyzeDispose {
	// 正则匹配
	private Pattern pattern;
	private Matcher matcher;	
	// 匹配字符串
	private final static String MATCH_ERROR_NO = "(OGG-)[\\d]{5}";
	
	/**
	 * 取得错误号
	 * @param readLineTemp
	 */
	public void errorNumberAcpuire(String readLineTemp) {
		pattern = Pattern.compile(MATCH_ERROR_NO);
		matcher = pattern.matcher(readLineTemp);
		
		if (matcher.find()) {		
			System.out.print(matcher.group());
			System.out.println();
		}		
	}

}
