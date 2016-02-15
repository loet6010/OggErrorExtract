package com.oggerror.extract.connssh.param;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oggerror.extract.connssh.conn.ConnectionManagerSsh;
/**
 * 
 * @author liurh
 * @date   2016年2月14日
 * @intro  设定SSH连接参数，并提供返回磁盘空间的方法
 *
 */
public class GetAvailableDiskSpace {
	/**
	 * SSH连接参数定义
	 */
	private static final String IP_ADDRESS = "192.168.17.128";// IP地址	
	private static final String PORT = "22";// 端口号
	private static final String USER = "oracle";// 用户名
	private static final String PASSWORD = "redhat";// 密码
	private static final String COMMAND_LINE = "df /home";// 命令行
	
	ConnectionManagerSsh cSsh;
	
	private String readLine = null;
	private String readTemp = null;
	
	public GetAvailableDiskSpace() {
		cSsh = ConnectionManagerSsh.getInstance();
	}
	
	/**
	 * 获取可用磁盘空间
	 * @return long
	 */
	public long getDiskSize() {
		String matchLine = null;
		if (getedCommandLineResult()) {
			System.out.println(readTemp);
			Pattern pattern = Pattern.compile("[ ][\\d]+[ ]");
			Matcher matcher = pattern.matcher(readTemp);
			
			while (matcher.find()) {
				matchLine = matcher.group().trim();
			}
			return Long.parseLong(matchLine);
		}				
		return 0;
	}
	
	private boolean getedCommandLineResult() {
		try {
			BufferedReader bufferedReader = cSsh.execCommand(IP_ADDRESS, PORT, USER, null, PASSWORD, COMMAND_LINE);
			
			readLine = bufferedReader.readLine();
			while (readLine != null) {
				readTemp = readLine;
				readLine = bufferedReader.readLine();
			}			
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}		
		return true;
	}

}
