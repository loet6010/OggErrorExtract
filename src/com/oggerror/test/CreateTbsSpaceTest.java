package com.oggerror.test;

import com.oggerror.extract.sqldispose.logic.CreateTbsSpaceLogic;

public class CreateTbsSpaceTest {

	public static void main(String[] args) {
		CreateTbsSpaceLogic cLogic = new CreateTbsSpaceLogic();
		String tbs_name = "ET_TEST";
		if (cLogic.createTbsSpace(tbs_name)) {
			System.out.println("新建表空间成功");
		} else {
			System.out.println("新建表空间失败");
		}

	}

}
