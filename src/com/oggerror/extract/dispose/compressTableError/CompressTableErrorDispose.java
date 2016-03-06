package com.oggerror.extract.dispose.compressTableError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.util.TableNameExtractTool;

/**
 * 
 * @author liurh
 * @date   2016年2月18日
 * @intro  压缩表错误处理类
 *
 */
public class CompressTableErrorDispose extends TableNameExtractTool {
	// 正则匹配表名匹配规则
	private final static String MATCH_TABLE_NAME = "(table)[ ]+[^ ]+";
	/**
	 * 压缩表错误处理方法
	 * @param readLineTemp
	 * @return
	 */
	public boolean compressTableError(String readLineTemp) {
		// 获取表名
		String tableName = getTableName(readLineTemp);
		System.out.println(tableName);
		
		if (tableName != null) {		
			return true;
		} else {
			System.out.println("压缩表错误，未获取到表名！");
			return false;
		}
	}
	
	// 获取表名方法
	private String getTableName(String readLineTemp) {
		Pattern pattern = Pattern.compile(MATCH_TABLE_NAME);
		Matcher matcher = pattern.matcher(readLineTemp);
		
		if (matcher.find()) {
			return getTableOrSpaceName(matcher.group());
		}
		
		return null;
	}

}
