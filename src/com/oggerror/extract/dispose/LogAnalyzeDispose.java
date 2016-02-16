package com.oggerror.extract.dispose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.dispose.addTbsSpace.AddTbsSpaceDispose;

/**
 * 
 * @author liurh
 * @date   2016年2月4日
 * @intro  对ggserr.log中ERROR信息进行处理
 *
 */
public class LogAnalyzeDispose {
	// 正则匹配
	private Pattern pattern;
	private Matcher matcher;	
	// 匹配字符串(表空间不足）
	private final static String MATCH_ORA_01654 = "(ORA-01654)";
	
	/**
	 * 取得错误号
	 * @param readLineTemp
	 */
	public void errorNumberAcpuire(String readLineTemp) {
		// 表空间不足匹配
		pattern = Pattern.compile(MATCH_ORA_01654);
		matcher = pattern.matcher(readLineTemp);		
		if (matcher.find()) {		
			AddTbsSpaceDispose atsDispose = new AddTbsSpaceDispose();
			boolean tbsAddDipose = atsDispose.addTbsSpace(readLineTemp);
			
			if (tbsAddDipose) {
				System.out.println("表空间不足处理成功！");
			} else {
				System.out.println("表空间不足处理失败！");
			}
			
		}
	}

}
