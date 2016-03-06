package com.oggerror.extract.sqldispose.logic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import com.oggerror.extract.connssh.param.GetAvailableDiskSpace;
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
	private boolean pathUnable = true;
	private int noCount = 0;
	private int tenCount = 10;
	
	/**
	 * 根据表空间名来新建表空间
	 * @param tbsName
	 * @return true
	 */
	public boolean createTbsSpace(String tbsName) {
		
		// 取得新建表空间的文件路径名
		String tbsFilePath = getTbsFilePath(tbsName);
		
		// 给TbsSpaceDto赋值
		TbsSpaceDto createTbsSpaceDto = new TbsSpaceDto();
		createTbsSpaceDto.setTbsName(tbsName);
		createTbsSpaceDto.setTbsFilePath(tbsFilePath);
		
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
	
	// 获取表空间文件完整路径名
	private String getTbsFilePath(String tbsName) {
		
		// 获取表空间文件名列表
		TbsFilePathLogic tLogic = new TbsFilePathLogic();
		ArrayList<String> filePathList = tLogic.getTbsFilePathList();
		
		// 获取文件所在路径
		String filePath = getFilePath(filePathList);
		
		if (filePath != null) {
			// 判断磁盘空间
			if (GetAvailableDiskSpace.isAvailable(filePath)) {
				String newFilePathName = null;
				
				while (pathUnable) {
					newFilePathName = createTbsSpace(tbsName, filePath, filePathList);
				}

				System.out.println(filePath);
				System.out.println(newFilePathName);

				return "'"+newFilePathName+"'";
			}
		}		
		return null;
	}
	
	// 创建新的文件完整路径名
	private String createTbsSpace(String tbsName, String filePath, ArrayList<String> filePathList) {
		String fString = filePath + tbsName + noCount + "1.dbf";
		for(String filePathName : filePathList) {
			if (fString.equals(filePathName)) {
				pathUnable = true;
				noCount += tenCount;
				tenCount = tenCount * 10;
				return null;
			}
		}
		pathUnable = false;
		return fString;
	}
	
	// 获取现有的表空间文件路径名
	private String getFilePath(ArrayList<String> filePathList) {
		String filePath = null;
		// 用正则匹配找表空间文件路径名
		String regexPath = "[/][^ ]+[/]";
		Pattern patternPath = Pattern.compile(regexPath);
		Matcher matcherPath;
		
		for (String strFilePath : filePathList) {			
			matcherPath = patternPath.matcher(strFilePath);			
			// 找出路径名返回
			if(matcherPath.find()) {
				// 获取路径名
				filePath = matcherPath.group();
				System.out.println(strFilePath+":["+filePath+"]");
				break;
			}
		}		
		return filePath;
	}	
}
