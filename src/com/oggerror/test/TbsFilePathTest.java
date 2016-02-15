package com.oggerror.test;

import com.oggerror.sqldispose.logic.TbsFilePathLogic;

public class TbsFilePathTest {

	public static void main(String[] args) {
		TbsFilePathLogic tbsFilePathLogic = new TbsFilePathLogic();
		String str = tbsFilePathLogic.getTbsFilePath("EXT_TEST");
		
		System.out.println(str);
	}
}
