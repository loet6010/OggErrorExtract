package com.oggerror.extract.dispose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.addTbsSpace.AddTbsSpaceDispose;
import com.oggerror.extract.dispose.compressTableError.CompressTableErrorDispose;
import com.oggerror.extract.dispose.createTbsSpace.CreateTbsSpaceDispose;
import com.oggerror.extract.dispose.enableRowMovement.EnableRowMovementDispose;

/**
 * 
 * @author liurh
 * @date 2016年2月4日
 * @intro 对ggserr.log中ERROR信息进行处理，并调用各错误的处理类
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
	// 匹配字符串(分区表未打开行迁移）
	private final static String MATCH_ORA_14402 = "(ORA-14402)";
	// 匹配字符串(分区表未打开行迁移）
	private final static String MATCH_OGG_01433 = "(OGG-01433)";

	// 匹配枚举类型
	private enum ErrorType {
		tbs_overflow,
		tbs_noexist,
		row_movement_unable,
		compress_table_error,
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
			
		case row_movement_unable: {// 分区表未打开行迁移处理
			EnableRowMovementDispose enableRowMovementDispose = new EnableRowMovementDispose();
			boolean rowMovementDispose = enableRowMovementDispose.enableRowMovement(readLineTemp);

			if (rowMovementDispose) {
				System.out.println("开启行迁移处理成功！");
			} else {
				System.out.println("开启行迁移处理失败！");
			}
		}
			break;
			
		case compress_table_error: {// 压缩表错误
			CompressTableErrorDispose compressTableErrorDispose = new CompressTableErrorDispose();
			boolean cteDispose = compressTableErrorDispose.compressTableError(readLineTemp);
			
			if (cteDispose) {
				System.out.println("压缩表错误处理成功！");
			} else {
				System.out.println("压缩表错误处理失败！");
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
		
		// 分区表未打开行迁移
		pattern = Pattern.compile(MATCH_ORA_14402);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find())
			return ErrorType.row_movement_unable;
		
		// 压缩表错误
		pattern = Pattern.compile(MATCH_OGG_01433);
		matcher = pattern.matcher(readLineTemp);
		if (matcher.find())
			return ErrorType.compress_table_error;

		return ErrorType.other_type;
	}

}
