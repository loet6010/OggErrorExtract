package com.oggerror.test;

import com.oggerror.extract.sqldispose.logic.EnableRowMovementLogic;

public class EnableRowMovementTest {

	public static void main(String[] args) {
		EnableRowMovementLogic eLogic = new EnableRowMovementLogic();
		if (eLogic.enableRowMovement("tde_test")) {
			System.out.println("开启行迁移成功！");
		} else {
			System.out.println("开启行迁移失败！");
		}
	}

}
