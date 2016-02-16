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
	
	// 扩充20M大小（单位KB）
	private final static long TBS_ADDSIZE_20M = 1024 * 20;
	
	// SSH连接参数定义
	private static final String IP_ADDRESS = "192.168.17.128";// IP地址	
	private static final String PORT = "22";// 端口号
	private static final String USER = "oracle";// 用户名
	private static final String PASSWORD = "redhat";// 密码
	private static final String COMMAND_LINE_FIRST = "df ";// 命令行
	private String command_line = null;
	
	ConnectionManagerSsh cSsh;
	
	private String readLine = null;
	private String readTemp = null;
	
	public GetAvailableDiskSpace() {
		cSsh = ConnectionManagerSsh.getInstance();
	}
	
	/**
	 * 判断剩余磁盘空间是否可扩充
	 * @param tbsFilePath
	 * @return true
	 */
	public static boolean isAvailable(String tbsFilePath) {
		long availableSize = new GetAvailableDiskSpace().getDiskSize(tbsFilePath);
		if (availableSize > TBS_ADDSIZE_20M) {
			return true;
		} else {
			return false;
		}
	}
	
	// 获取磁盘剩余空间大小
	private long getDiskSize(String tbsFilePath) {
		String matchLine = null;
		command_line = COMMAND_LINE_FIRST + tbsFilePath;
		if (getedCommandLineResult()) {
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
			BufferedReader bufferedReader = cSsh.execCommand(IP_ADDRESS, PORT, USER, null, PASSWORD, command_line);
			
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
