package com.oggerror.extract.dispose.addTbsSpace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.connssh.param.GetAvailableDiskSpace;
import com.oggerror.extract.sqldispose.logic.AddTbsSpaceLogic;
import com.oggerror.extract.sqldispose.logic.TbsFilePathLogic;

/**
 * 
 * @author liurh
 * @date   2016年2月15日
 * @intro  增加表空间处理类，提取出表空间名，并调用表空间增加类
 *
 */
public class AddTbsSpaceDispose {
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
		
		// 获取表空间文件名
		TbsFilePathLogic tbsFilePathLogic = new TbsFilePathLogic();
		String tbsFilePath = tbsFilePathLogic.getTbsFilePath(tbsName);
		
		// 文件名存在情况下，且磁盘可用空间够用，对表空间进行扩充
		if (tbsFilePath != null) {
			if (GetAvailableDiskSpace.isAvailable(tbsFilePath)) {
				AddTbsSpaceLogic addTbsSpaceLogic = new AddTbsSpaceLogic();
				return addTbsSpaceLogic.addTbsSpace(tbsName, tbsFilePath);
			}
		}
		return false;
	}
	
	private String getTbsName(String readLineTemp) {
		System.out.println(readLineTemp);
		Pattern pattern = Pattern.compile(MATCH_TBS_NAME);
		Matcher matcher = pattern.matcher(readLineTemp);
		if (matcher.find()) {
			String mString = matcher.group(0);
			String[] mStringArr = mString.split(" ");
			return mStringArr[mStringArr.length - 1];
		}
		
		return null;
	}

}
