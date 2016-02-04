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
public class GgserrReader {

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

	/**
	 * 主函数-程序入口
	 * @param args
	 */
	public static void main(String[] args) {

		GgserrReader ggserrReader = new GgserrReader();
		try {
			ggserrReader.readGgserrLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读“ggserr.log”文件
	 * @throws IOException
	 */
	public void readGgserrLog() throws IOException {
		try {
			FileReader fileReader = new FileReader("D:\\splitFileTest\\ggserr.log");
			bReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String readLineTemp = bReader.readLine();
			int i = 1;
			while (readLineTemp != null) {
				matchType = getMatchType(readLineTemp);
				
				// 对读取行进行判断
				switch (matchType) {
				case error_type:{
					errorLogDispose.errorNumberAcpuire(readLineTemp);;
				}
					break;
					
				case warning_type:{
				}
					break;

				default:
					break;
				}
				
				// 读取下一行
				readLineTemp = bReader.readLine();
				i++;
			}
			System.out.println("log行数：" + i);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bReader.close();
		}
	}

	/**
	 * 判断字符串中含有的关键字
	 * @param tempLine
	 * @return MatchType
	 */
	private MatchType getMatchType(String tempLine) {
		// 创建 Pattern 对象(匹配ERROR信息）
		Pattern patternError = Pattern.compile(MATCH_ERROR);
		// 现在创建 matcher 对象
		Matcher matcherError = patternError.matcher(tempLine);

		if (matcherError.find())
			return MatchType.error_type;

		// 创建 Pattern 对象(匹配WARNING信息）
		Pattern patternWarning = Pattern.compile(MATCH_WARNING);
		// 现在创建 matcher 对象
		Matcher matcherWarning = patternWarning.matcher(tempLine);

		if (matcherWarning.find())
			return MatchType.warning_type;

		return MatchType.other_type;
	}

}
