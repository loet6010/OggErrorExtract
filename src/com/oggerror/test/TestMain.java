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
		
		
		// 获取系统名
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println(osName);
		
		int sysNo = osName.indexOf("win");
		System.out.println(sysNo);
		if (sysNo >= 0) {
			System.out.println("Windows");
		} else {
			System.out.println("Linux");
		}
		
	}

}
