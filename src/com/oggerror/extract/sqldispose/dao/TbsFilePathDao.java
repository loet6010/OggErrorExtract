package com.oggerror.extract.sqldispose.dao;

import java.util.ArrayList;

/**
 * 
 * @author liurh
 * @date   2016年2月14日
 * @intro  获取表空间文件路径的映射类
 *
 */

public interface TbsFilePathDao {
	/**
	 * 根据表空间名来获取表空间文件路径
	 * @param tbsName
	 * @return 表空间文件路径
	 */
	public String selectTbsFilePath(String tbsName);
	
	/**
	 * 获取表空间文件路径列表
	 * @param tbsName
	 * @return 表空间文件路径列表
	 */
	public ArrayList<String> selectTbsFilePathList();
}
