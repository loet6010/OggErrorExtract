package com.oggerror.extract.sqldispose.dto;
/**
 * 
 * @author liurh
 * @date   2016年2月15日
 * @intro  增加表空间的dto类
 *
 */
public class TbsSpaceDto {
	// 表空间名
	private String tbsName;
	// 表空间文件路径
	private String tbsFilePath;
	
	/**
	 * 获取表空间名
	 * @return
	 */
	public String getTbsName() {
		return tbsName;
	}
	
	/**
	 * 设置表空间名
	 * @param tbsName
	 */
	public void setTbsName(String tbsName) {
		this.tbsName = tbsName;
	}
	
	/**
	 * 获取表空间路径
	 * @return
	 */
	public String getTbsFilePath() {
		return tbsFilePath;
	}
	
	/**
	 * 设置表空间路径
	 * @param tbsFilePath
	 */
	public void setTbsFilePath(String tbsFilePath) {
		this.tbsFilePath = tbsFilePath;
	}
	
}
