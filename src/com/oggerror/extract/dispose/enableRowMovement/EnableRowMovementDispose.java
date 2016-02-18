package com.oggerror.extract.dispose.enableRowMovement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.util.TableNameExtractTool;
import com.oggerror.extract.sqldispose.logic.EnableRowMovementLogic;

/**
 * 
 * @author liurh
 * @date 2016年2月17日
 * @intro 开启行迁移处理类，提取表名，调用开启行迁移逻辑类
 *
 */
public class EnableRowMovementDispose extends TableNameExtractTool {
	// 正则匹配表名匹配规则
	private final static String MATCH_TBS_NAME = "(UPDATE)[ ]+[^ ]+";

	/**
	 * 开启行迁移处理方法
	 * 
	 * @param readLineTemp
	 * @return true
	 */
	public boolean enableRowMovement(String readLineTemp) {
		// 获取表名
		String tableName = getTbsName(readLineTemp);		
		System.out.println(tableName);
		
		// 表名存在的情况下，调用开启行迁移类
		if (tableName != null) {
			EnableRowMovementLogic enableRowMovementLogic = new EnableRowMovementLogic();
			return enableRowMovementLogic.enableRowMovement(tableName);
		} else {
			System.out.println("开启行迁移，未获取到表名！");
			return false;
		}
	}

	// 获取表名
	private String getTbsName(String readLineTemp) {
		Pattern pattern = Pattern.compile(MATCH_TBS_NAME);
		Matcher matcher = pattern.matcher(readLineTemp);
		
		if (matcher.find()) {
			return getTableOrSpaceName(matcher.group(0));
		}

		return null;
	}
}
