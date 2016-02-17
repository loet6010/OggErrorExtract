package com.oggerror.extract.sqldispose.logic;

import org.apache.ibatis.session.SqlSession;

import com.oggerror.extract.sqldispose.dao.EnableRowMovementDao;
import com.oggerror.extract.sqldispose.dto.TableNameDto;
import com.oggerror.extract.sqldispose.util.SqlSessionBuild;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  开启行迁移逻辑类
 *
 */
public class EnableRowMovementLogic {

	/**
	 * 开启行迁移方法
	 * @param tableName
	 * @return true
	 */
	public boolean enableRowMovement(String tableName) {
		System.out.println("行迁移表名："+tableName);
		
		TableNameDto tableNameDto = new TableNameDto();
		tableNameDto.setTableName(tableName);
		
		// 获取SqlSession
		SqlSession sqlSession = new SqlSessionBuild().getSqlSession();
		try {
			// 映射EnableRowMovementDao，开启行迁移
			EnableRowMovementDao eRowMovementDao = sqlSession.getMapper(EnableRowMovementDao.class);
			eRowMovementDao.enableRowMovement(tableNameDto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 关闭SqlSession
			sqlSession.close();
		}
		return true;
	}
}
