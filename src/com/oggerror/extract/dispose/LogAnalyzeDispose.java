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
	// 匹配字符串(表空间不足）
	private final static String ORA_01654 = "(ORA-01654)";
	
	/**
	 * 取得错误号
	 * @param readLineTemp
	 */
	public void errorNumberAcpuire(String readLineTemp) {
		// 表空间不足匹配
		pattern = Pattern.compile(ORA_01654);
		matcher = pattern.matcher(readLineTemp);		
		if (matcher.find()) {		
			System.out.println(matcher.group());
			System.out.println(readLineTemp);
		}
	}

}
