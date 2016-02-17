package com.oggerror.extract.dispose.createTbsSpace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.sqldispose.logic.CreateTbsSpaceLogic;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  创建表空间处理类，提取出表空间名，并调用创建表空间逻辑类
 *
 */
public class CreateTbsSpaceDispose {
	// 正则匹配表空间名匹配规则
	private final static String MATCH_TBS_NAME = "(tablespace)[ ]+[']?[^ ]+[']?";

	private final static String MATCH_QUOTATION = "[']";
	
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
			System.out.println("新建表空间，未截取到表空间名！");
			return false;
		}
		
	}

	// 获取表空间名
	private String getTbsName(String readLineTemp) {
		Pattern pattern = null;
		Matcher matcher = null;
		// 如果有引号，将引号替换为空格
		pattern = Pattern.compile(MATCH_QUOTATION);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find()) {
			readLineTemp = matcher.replaceAll(" ");
		}
		
		// 取得表空间名
		pattern = Pattern.compile(MATCH_TBS_NAME);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find()) {
			String mString = matcher.group(0);
			System.out.println(mString);
			String[] mStringArr = mString.split(" ");
			return mStringArr[mStringArr.length - 1];
		}

		return null;
	}	

}
