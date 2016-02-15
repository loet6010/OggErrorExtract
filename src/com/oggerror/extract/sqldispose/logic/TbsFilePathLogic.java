package com.oggerror.extract.sqldispose.logic;

import org.apache.ibatis.session.SqlSession;

import com.oggerror.extract.sqldispose.dao.TbsFilePathDao;
import com.oggerror.extract.sqldispose.util.SqlSessionBuild;

/**
 * 
 * @author liurh
 * @date 2016年2月14日
 * @intro 获取表空间文件路径的逻辑类
 *
 */

public class TbsFilePathLogic {
	private String tbsFilePath = null;

	/**
	 * 根据表空间名来获取表空间文件路径
	 * 
	 * @param tbsName
	 * @return 表空间文件路径
	 */
	public String getTbsFilePath(String tbsName) {
		// 获取SqlSession
		SqlSession sqlSession = new SqlSessionBuild().getSqlSession();
		try {
			// 映射tbsFilePathDao的selectTbsFilePath方法，获取表空间文件路径
			TbsFilePathDao tbsFilePathDao = sqlSession.getMapper(TbsFilePathDao.class);
			tbsFilePath = tbsFilePathDao.selectTbsFilePath(tbsName);
		} finally {
			// 关闭SqlSession
			sqlSession.close();
		}
		
		return tbsFilePath;
	}
}
