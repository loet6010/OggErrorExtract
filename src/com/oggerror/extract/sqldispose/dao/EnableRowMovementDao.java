package com.oggerror.extract.sqldispose.dao;

import com.oggerror.extract.sqldispose.dto.TableNameDto;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  开启行迁移映射类
 *
 */
public interface EnableRowMovementDao {

	/**
	 * 根据表名，开启行迁移
	 * @param tableName
	 */
	public void enableRowMovement(TableNameDto tableNameDto);
}
