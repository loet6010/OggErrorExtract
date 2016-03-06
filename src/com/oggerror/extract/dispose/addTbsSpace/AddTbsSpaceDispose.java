package com.oggerror.extract.dispose.addTbsSpace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.util.TableNameExtractTool;
import com.oggerror.extract.sqldispose.logic.AddTbsSpaceLogic;

/**
 * 
 * @author liurh
 * @date   2016年2月15日
 * @intro  增加表空间处理类，提取出表空间名，并调用增加表空间逻辑类
 *
 */
public class AddTbsSpaceDispose extends TableNameExtractTool {
	// 正则匹配表空间名匹配规则
	private final static String MATCH_TBS_NAME = "(tablespace)[ ]+[^ ]+";

	/**
	 * 增加表空间，成功返回true
	 * @param readLineTemp
	 * @return true
	 */
	public boolean addTbsSpace(String readLineTemp) {
		// 获取表空间名
		String tbsName = getTbsName(readLineTemp);
		System.out.println(tbsName);		
		
		if (tbsName != null) {
			// 对表空间进行扩充
			AddTbsSpaceLogic addTbsSpaceLogic = new AddTbsSpaceLogic();
			return addTbsSpaceLogic.addTbsSpace(tbsName);
		} else {
			System.out.println("增加表空间，未获取到表空间名！");
			return false;
		}
	}
	
	private String getTbsName(String readLineTemp) {
		Pattern pattern = Pattern.compile(MATCH_TBS_NAME);
		Matcher matcher = pattern.matcher(readLineTemp);
		
		if (matcher.find()) {
			return getTableOrSpaceName(matcher.group(0));
		}		
		
		return null;
	}

}
