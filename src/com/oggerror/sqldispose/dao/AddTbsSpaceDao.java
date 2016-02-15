package com.oggerror.sqldispose.dao;

import com.oggerror.sqldispose.dto.AddTbsSpaceDto;

/**
 * 
 * @author liurh
 * @date   2016年2月15日
 * @intro  增加表空间的映射类
 *
 */
public interface AddTbsSpaceDao {

	/**
	 * 根据表空间名和文件路劲，对表空间容量进行扩充
	 * @param addTbsSpaceDto
	 */
	public void addTbsSpace(AddTbsSpaceDto addTbsSpaceDto);

}
