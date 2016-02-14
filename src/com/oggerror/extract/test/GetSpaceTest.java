package com.oggerror.extract.test;

import java.io.File;
/**
 * 
 * @author liurh
 * @date   2016年2月14日
 * @intro  获取磁盘空间大小
 *
 */
public class GetSpaceTest {

	public static void main(String[] args) {
		File f = null;
		long v;
		boolean bool = false;

		try {
			// create new file
			f = new File("D:\\test.txt");

			// get number of unallocated bytes
			v = f.getFreeSpace();

			// true if the file path exists
			bool = f.exists();

			// if file exists
			if (bool) {
				// prints
				System.out.print("number of unallocated bytes: " + v);
			}
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}

	}

}
