package com.oggerror.sqldispose.dao;
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
}
