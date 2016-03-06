package com.oggerror.extract.sqldispose.dao;

import com.oggerror.extract.sqldispose.dto.TbsSpaceDto;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  新建表空间的映射类
 *
 */
public interface CreateTbsSpaceDao {
	
	/**
	 * 根据表空间名和文件路劲，新建表空间
	 * @param addTbsSpaceDto
	 */
	public void createTbsSpace(TbsSpaceDto addTbsSpaceDto);

}
