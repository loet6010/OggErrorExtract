package com.oggerror.extract.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author liurh
 * @date   2016年2月4日
 * @intro  读取ggserr.log并分离出ERROR和WARNING信息
 *
 */

import com.oggerror.extract.dispose.ErrorLogDispose;
public class GgserrDispose {

	// ERROR匹配串
	private final static String MATCH_ERROR = "[\\d][ ]+(ERROR)[ ]+";
	// WARNING匹配串
	private final static String MATCH_WARNING = "[\\d][ ]+(WARNING)[ ]+";
	// 匹配枚举类型
	private enum MatchType{
		error_type,
		warning_type,
		other_type
	}
	private MatchType matchType;
	// 读写相关Buffered变量
	private static BufferedReader bReader;
	// 类声明
	private ErrorLogDispose errorLogDispose = new ErrorLogDispose();
	// 正则匹配
	private Pattern pattern;
	private Matcher matcher;

	/**
	 * 读“ggserr.log”文件
	 * @throws IOException
	 */
	public void readGgserrLog(String filePath) {
		try {
			FileReader fileReader = new FileReader(filePath);
			bReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String readLineTemp = bReader.readLine();
			while (readLineTemp != null) {
				matchType = getMatchType(readLineTemp);
				
				// 对读取行进行判断
				switch (matchType) {
				case error_type:{
					errorLogDispose.errorNumberAcpuire(readLineTemp);;
				}
					break;
					
				case warning_type:{
					System.out.println("warning_type");
				}
					break;

				default:
					break;
				}				
				// 读取下一行
				readLineTemp = bReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断字符串中含有的关键字
	 * @param tempLine
	 * @return MatchType
	 */
	private MatchType getMatchType(String tempLine) {
		// 创建 Pattern 对象(匹配ERROR信息）
		pattern = Pattern.compile(MATCH_ERROR);
		// 现在创建 matcher 对象
		matcher = pattern.matcher(tempLine);

		if (matcher.find())
			return MatchType.error_type;

		// 创建 Pattern 对象(匹配WARNING信息）
		pattern = Pattern.compile(MATCH_WARNING);
		// 现在创建 matcher 对象
		matcher = pattern.matcher(tempLine);

		if (matcher.find())
			return MatchType.warning_type;

		return MatchType.other_type;
	}

}
