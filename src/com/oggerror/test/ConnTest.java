package com.oggerror.test;

import com.oggerror.connssh.param.ParamManagerSsh;

public class ConnTest {

	public static void main(String[] args) {
		ParamManagerSsh pSsh = new ParamManagerSsh();
		long sizeLong = pSsh.getDiskSize();
		System.out.println(sizeLong);
	}

}
