package com.oggerror.extract.dispose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.addTbsSpace.AddTbsSpaceDispose;
import com.oggerror.extract.dispose.createTbsSpace.CreateTbsSpaceDispose;

/**
 * 
 * @author liurh
 * @date 2016年2月4日
 * @intro 对ggserr.log中ERROR信息进行处理
 *
 */
public class LogAnalyzeDispose {
	// 正则匹配
	private Pattern pattern;
	private Matcher matcher;
	// 匹配字符串(表空间满）
	private final static String MATCH_ORA_01654 = "(ORA-01654)";
	// 匹配字符串(表空间不存在）
	private final static String MATCH_ORA_00959 = "(ORA-00959)";

	// 匹配枚举类型
	private enum ErrorType {
		tbs_overflow,
		tbs_noexist, 
		other_type
	}

	/**
	 * 取得错误号
	 * 
	 * @param readLineTemp
	 */
	public void errorAnalyzeDispose(String readLineTemp) {

		ErrorType errorType = getErrorType(readLineTemp);

		switch (errorType) {
		case tbs_overflow: {// 表空间满处理
			AddTbsSpaceDispose atsDispose = new AddTbsSpaceDispose();
			boolean tbsAddDipose = atsDispose.addTbsSpace(readLineTemp);

			if (tbsAddDipose) {
				System.out.println("表空间满处理成功！");
			} else {
				System.out.println("表空间满处理失败！");
			}
		}
			break;
			
		case tbs_noexist: {// 表空间不存在处理
			CreateTbsSpaceDispose cTbsSpaceDispose = new CreateTbsSpaceDispose();
			boolean tbsCreatDipose = cTbsSpaceDispose.createTbsSpace(readLineTemp);

			if (tbsCreatDipose) {
				System.out.println("表空间不存在处理成功！");
			} else {
				System.out.println("表空间不存在处理失败！");
			}
		}
			break;

		default:
			break;
		}
	}

	private ErrorType getErrorType(String readLineTemp) {
		// 表空间满匹配
		pattern = Pattern.compile(MATCH_ORA_01654);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find())
			return ErrorType.tbs_overflow;
		
		// 表空间不存在匹配
		pattern = Pattern.compile(MATCH_ORA_00959);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find())
			return ErrorType.tbs_noexist;

		return ErrorType.other_type;
	}

}
