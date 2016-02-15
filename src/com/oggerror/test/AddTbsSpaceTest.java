package com.oggerror.test;

import com.oggerror.extract.sqldispose.logic.AddTbsSpaceLogic;

public class AddTbsSpaceTest {

	public static void main(String[] args) {
		AddTbsSpaceLogic addTbsSpaceLogic = new AddTbsSpaceLogic();
		boolean result = addTbsSpaceLogic.addTbsSpace("EXT_TEST");
		if (result) {
			System.out.println("扩容成功");
		} else {
			System.out.println("扩容失败");
		}
		
	}
}
