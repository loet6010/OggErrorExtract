package com.oggerror.test;

import com.oggerror.extract.reader.GgserrLogReader;
/**
 * 
 * @author liurh
 * @date   2016年2月5日
 * @intro  测试类
 *
 */
public class TestMain {
	
	public static void main(String[] args) {
		
		String filePath = "D:\\splitFileTest\\ggserr.log";
		// 调用处理方法
		GgserrLogReader ggserrReader = new GgserrLogReader();
		ggserrReader.readGgserrLog(filePath);

	}

}
