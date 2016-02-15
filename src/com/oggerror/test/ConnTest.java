package com.oggerror.test;

import com.oggerror.extract.connssh.param.GetAvailableDiskSpace;

public class ConnTest {

	public static void main(String[] args) {
		GetAvailableDiskSpace getAvailableDiskSpace = new GetAvailableDiskSpace();
		long sizeLong = getAvailableDiskSpace.getDiskSize();
		System.out.println(sizeLong);
	}

}
