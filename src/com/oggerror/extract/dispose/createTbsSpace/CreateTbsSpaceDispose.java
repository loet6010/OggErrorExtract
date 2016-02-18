package com.oggerror.extract.dispose.createTbsSpace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.util.TableNameExtractTool;
import com.oggerror.extract.sqldispose.logic.CreateTbsSpaceLogic;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  创建表空间处理类，提取出表空间名，并调用创建表空间逻辑类
 *
 */
public class CreateTbsSpaceDispose extends TableNameExtractTool {
	// 正则匹配表空间名匹配规则
	private final static String MATCH_TBS_NAME = "(tablespace)[ ]+[^ ]+";
	
	/**
	 * 新建表空间，成功返回true
	 * 
	 * @param readLineTemp
	 * @return true
	 */
	public boolean createTbsSpace(String readLineTemp) {
		// 获取表空间名
		String tbsName = getTbsName(readLineTemp);
		System.out.println(tbsName);

		if (tbsName != null) {
			// 创建表空间
			CreateTbsSpaceLogic createTbsSpaceLogic = new CreateTbsSpaceLogic();
			return createTbsSpaceLogic.createTbsSpace(tbsName);
		} else {
			System.out.println("新建表空间，未获取到表空间名！");
			return false;
		}
		
	}

	// 获取表空间名
	private String getTbsName(String readLineTemp) {
		Pattern pattern = Pattern.compile(MATCH_TBS_NAME);
		Matcher matcher = pattern.matcher(readLineTemp);
		
		if (matcher.find()) {
			return getTableOrSpaceName(matcher.group(0));
		}

		return null;
	}	

}
