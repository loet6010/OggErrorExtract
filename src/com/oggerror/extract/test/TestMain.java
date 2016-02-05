package com.oggerror.extract.test;

import java.io.IOException;

import com.oggerror.extract.reader.GgserrDispose;
/**
 * 
 * @author liurh
 * @date   2016年2月5日
 * @intro  测试类
 *
 */
public class TestMain {
	
	public static void main(String[] args) {
		// 调用处理方法
		GgserrDispose ggserrReader = new GgserrDispose();
		try {
			ggserrReader.readGgserrLog("D:\\splitFileTest\\ggserr.log");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
