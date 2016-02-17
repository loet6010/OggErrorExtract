package com.oggerror.extract.dispose.enableRowMovement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.sqldispose.logic.EnableRowMovementLogic;

/**
 * 
 * @author liurh
 * @date 2016年2月17日
 * @intro 开启行迁移处理类，提取表名，调用开启行迁移类
 *
 */
public class EnableRowMovementDispose {
	// 正则匹配表名匹配规则
	private final static String MATCH_TBS_NAME = "(UPDATE)[ ]+[']?[^ ]+[']?";

	private final static String MATCH_QUOTATION = "[\"]";

	/**
	 * 开启行迁移处理方法
	 * 
	 * @param readLineTemp
	 * @return true
	 */
	public boolean enableRowMovement(String readLineTemp) {
		System.out.println(readLineTemp);
		
		String tableName = getTbsName(readLineTemp);		
		System.out.println(tableName);
		
		// 表名存在的情况下，调用开启行迁移类
		if (tableName != null) {
			EnableRowMovementLogic enableRowMovementLogic = new EnableRowMovementLogic();
			return enableRowMovementLogic.enableRowMovement(tableName);
		} else {
			System.out.println("开启行迁移，未截取到表名！");
			return false;
		}
	}

	// 获取表空间名
	private String getTbsName(String readLineTemp) {
		Pattern pattern = null;
		Matcher matcher = null;
		String tableName = null;

		// 取得表名
		pattern = Pattern.compile(MATCH_TBS_NAME);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find()) {
			String mString = matcher.group(0);
			System.out.println(mString);
			String[] mStringArr = mString.split(" ");
			tableName = mStringArr[mStringArr.length - 1];
		}
		
		System.out.println(tableName);
		
		// 如果有引号，将引号去除
		pattern = Pattern.compile(MATCH_QUOTATION);
		matcher = pattern.matcher(tableName);
		if (matcher.find()) {
			tableName = matcher.replaceAll("");
		}

		return tableName;
	}
}
