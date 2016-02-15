package com.oggerror.test;

import com.oggerror.extract.connssh.param.GetAvailableDiskSpace;

public class ConnTest {

	public static void main(String[] args) {
		GetAvailableDiskSpace getAvailableDiskSpace = new GetAvailableDiskSpace();
		long sizeLong = getAvailableDiskSpace.getDiskSize("/home/oracle/app/oracle/tbs_tde_test");
		System.out.println(sizeLong);
	}

}
