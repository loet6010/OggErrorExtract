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
	// 扩充20M大小（单位KB）
	private final static long TBS_ADDSIZE_20M = 1024 * 20;
	// 正则匹配表空间名匹配规则
	private final static String MATCH_TBS_NAME = "(tablespace)[ ]+[^ ]+";
	
	private String tbsName = null;
	private String tbsFilePath = null;
	private long availableSize = 0;
	
	
	/**
	 * 增加表空间，成功返回true
	 * @param readLineTemp
	 * @return true
	 */
	public boolean addTbsSpace(String readLineTemp) {
		// 获取表空间名
		tbsName = getTbsName(readLineTemp);
		System.out.println(tbsName);
		
		// 获取表空间文件名
		TbsFilePathLogic tbsFilePathLogic = new TbsFilePathLogic();
		tbsFilePath = tbsFilePathLogic.getTbsFilePath(tbsName);
		
		System.out.println(tbsFilePath);
		System.out.println(TBS_ADDSIZE_20M);
		
		// 文件名存在情况下，获取磁盘可用空间
		if (tbsFilePath != null) {
			GetAvailableDiskSpace getAvailableDiskSpace = new GetAvailableDiskSpace();
			availableSize = getAvailableDiskSpace.getDiskSize(tbsFilePath);
			
			System.out.println(availableSize);
			
			// 剩余磁盘空间大于扩充空间是对表空间进行扩充
			if (availableSize > TBS_ADDSIZE_20M) {
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
