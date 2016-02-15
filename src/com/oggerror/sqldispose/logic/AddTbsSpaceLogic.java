package com.oggerror.sqldispose.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import com.oggerror.sqldispose.dao.AddTbsSpaceDao;
import com.oggerror.sqldispose.dto.AddTbsSpaceDto;
import com.oggerror.sqldispose.util.SqlSessionBuild;

/**
 * 
 * @author liurh
 * @date   2016年2月15日
 * @intro  增加表空间的逻辑类
 *
 */
public class AddTbsSpaceLogic {

	private String tbsFilePath = null;
	private String oldFilePath = null;
	private AddTbsSpaceDto addTbsSpaceDto;
	private StringBuffer sBufferFileNo;
	
	/**
	 * 根据表空间名来对表空间容量进行扩充
	 * @param tbsName
	 * @return true
	 */
	public boolean addTbsSpace(String tbsName) {
		// 获取表空间文件路径名
		TbsFilePathLogic tbsFilePathLogic = new TbsFilePathLogic();
		oldFilePath = tbsFilePathLogic.getTbsFilePath(tbsName);
		
		// 根据源文件路径名产生新的文件路径名
		tbsFilePath = getNewFilePath(oldFilePath);
		
		// 对AddTbsSpaceDto进行赋值
		addTbsSpaceDto = new AddTbsSpaceDto();
		addTbsSpaceDto.setTbsName(tbsName);
		addTbsSpaceDto.setTbsFilePath(tbsFilePath);
		
		System.out.println(oldFilePath);
		System.out.println(tbsFilePath);
		
		// 获取SqlSession
		SqlSession sqlSession = new SqlSessionBuild().getSqlSession();
		try {
			// 映射AddTbsSpaceDao，对表空间容量进行扩充
			AddTbsSpaceDao addTbsSpaceDao = sqlSession.getMapper(AddTbsSpaceDao.class);
			addTbsSpaceDao.addTbsSpace(addTbsSpaceDto);		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			// 关闭SqlSession
			sqlSession.close();		 
		}
		
		return true;
	}
	
	private String getNewFilePath(String filePath) {
		if (filePath == null) {
			return null;
		}
		
		// 用正则匹配找出路径中的文件名
		String regexFileName = "[a-zA-z]+[\\d]+(.dbf)";
		Pattern patternFileName = Pattern.compile(regexFileName);
		Matcher matcherFileName = patternFileName.matcher(filePath);
		
		// 找出后对文件名进行更改
		if(matcherFileName.find()) {
			// 获取文件名
			String fileName = matcherFileName.group();
			
			// 匹配找出文件名中的数字
			String regexNo = "[\\d]+";
			Pattern patternNo = Pattern.compile(regexNo);
			Matcher matcherNo = patternNo.matcher(fileName);
			
			// 对数字进行加1，更新文件名
			if (matcherNo.find()) {
				String fileNo = matcherNo.group();
				int intFileNo = Integer.parseInt(fileNo);
				intFileNo += 1;
				String sFileNo = String.valueOf(intFileNo);				
				sBufferFileNo = new StringBuffer();
				
				// 对数字进行补“0”
				for (int i = 0; i < (fileNo.length() - sFileNo.length()); i++) {
					sBufferFileNo.append("0");
				}				
				fileNo = sBufferFileNo + sFileNo;
				// 加1后的数字串替换原数字串
				fileName = matcherNo.replaceFirst(fileNo);
			}
			// 更改后文件名替换原文件名
			filePath = matcherFileName.replaceFirst(fileName);
		}
		return "'"+filePath+"'";
	}
}
