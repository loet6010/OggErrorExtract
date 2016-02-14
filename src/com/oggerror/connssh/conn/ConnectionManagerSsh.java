package com.oggerror.connssh.conn;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class ConnectionManagerSsh {

	private static ConnectionManagerSsh instance;

	private ConnectionManagerSsh() {

	}

	public static synchronized ConnectionManagerSsh getInstance() {
		if (instance == null) {
			instance = new ConnectionManagerSsh();
		}
		return instance;
	}

	public BufferedReader execCommand(String IpAddress, String port,
			String user, String privatekey, String password, String commandLine)
			throws Exception {
		BufferedReader br;
		BufferedReader brerror;
		Connection conn = null;
		Session session = null;
		try {
			conn = new Connection(IpAddress, Integer.parseInt(port));
			conn.connect();

			// boolean isAuthenticated;
			// isAuthenticated =
			// 有秘钥文件的使用秘钥文件
			if (privatekey != null && !privatekey.isEmpty()) {
				File fkey = new File(privatekey);
				conn.authenticateWithPublicKey(user, fkey, password);
			} else {
				conn.authenticateWithPassword(user, password);
			}
			session = conn.openSession();
			session.execCommand(commandLine);
			InputStream stdout = new StreamGobbler(session.getStdout());
			InputStream stderr = new StreamGobbler(session.getStderr());
			br = new BufferedReader(new InputStreamReader(stdout));
			brerror = new BufferedReader(new InputStreamReader(stderr));
			session.waitForCondition(ChannelCondition.CLOSED
					| ChannelCondition.EOF, 1000 * 20);
			StringBuffer err = new StringBuffer();
			String line;
			while ((line = brerror.readLine()) != null) {
				err.append(line);
			}
			brerror.close();
			if (err.length() > 0) {
				throw new Exception(err.toString());
			}
			// session.waitForCondition(ChannelCondition.CLOSED
			// | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS,
			// 1000 * 20);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return br;

	}

	public BufferedReader execShellCommands(String IpAddress, int port,
			String user, String privatekey, String password, String commandLine)
			throws Exception {
		BufferedReader br;
		Connection conn = null;
		Session session = null;
		try {
			conn = new Connection(IpAddress, port);
			conn.connect();
			session = null;
			// boolean isAuthenticated;
			// isAuthenticated =

			// 有秘钥文件的使用秘钥文件
			if (privatekey != null && !privatekey.isEmpty()) {
				File fkey = new File(privatekey);
				conn.authenticateWithPublicKey(user, fkey, password);
			} else {
				conn.authenticateWithPassword(user, password);
			}
			session = conn.openSession();

			session.requestPTY("vt100", 80, 24, 640, 480, null);
			session.startShell();
			OutputStream stdin = session.getStdin();
			PrintStream pstdin = new PrintStream(stdin);
			InputStream stdout = new StreamGobbler(session.getStdout());
			br = new BufferedReader(new InputStreamReader(stdout));

			pstdin.print(commandLine);
			pstdin.flush();
			pstdin.close();
			session.waitForCondition(ChannelCondition.CLOSED
					| ChannelCondition.EOF, 1000 * 20);
			// session.waitForCondition(ChannelCondition.CLOSED
			// | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS,
			// 1000 * 20);

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return br;
	}

}
