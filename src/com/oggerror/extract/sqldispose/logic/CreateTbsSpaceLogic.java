package com.oggerror.extract.sqldispose.logic;

import org.apache.ibatis.session.SqlSession;

import com.oggerror.extract.sqldispose.dao.CreateTbsSpaceDao;
import com.oggerror.extract.sqldispose.dto.TbsSpaceDto;
import com.oggerror.extract.sqldispose.util.SqlSessionBuild;

/**
 * 
 * @author liurh
 * @date   2016年2月16日
 * @intro  新建表空间逻辑类
 *
 */
public class CreateTbsSpaceLogic {
	
	/**
	 * 根据表空间名来新建表空间
	 * @param tbsName
	 * @return true
	 */
	public boolean createTbsSpace(String tbsName) {
		
		// 
		TbsSpaceDto createTbsSpaceDto = new TbsSpaceDto();
		
		// 获取SqlSession
		SqlSession sqlSession = new SqlSessionBuild().getSqlSession();		
		try {
			// 映射CreateTbsSpaceDao，新建表空间
			CreateTbsSpaceDao createTbsSpaceDao = sqlSession.getMapper(CreateTbsSpaceDao.class);
			createTbsSpaceDao.createTbsSpace(createTbsSpaceDto);
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
