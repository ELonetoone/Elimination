package el.onetoone.back;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 网络连接版本的数据库
 * 
 * @author iznauy
 *
 */
public class NetDataBase {

	private static final String add = "118.89.114.25";

	private static final int port = 21500;

	/**
	 * 最长延时
	 */
	private static final int TIMEOUT = 2000;

	/**
	 * UDPSocet
	 */
	private DatagramSocket socket;

	/**
	 * 发送数据的Pocket
	 */
	private DatagramPacket sendPocket;

	/**
	 * 接收数据的Pocket
	 */
	private DatagramPacket receivePocket;

	/**
	 * 是否已经初始化
	 */
	private boolean hasInit = false;

	/**
	 * 接收数据的存储位置
	 */
	private byte[] dataReceive = new byte[1024];

	/**
	 * 初始化链接
	 * 
	 * @return FALSE 表示初始化失败，也就是网络连接失败，否则返回 TRUE
	 */
	public boolean init() {
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(TIMEOUT);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		hasInit = true;
		return true;
	}

	/**
	 * 获取 hasInit
	 * 
	 * @return hasInit
	 */
	public boolean getHasInit() {
		return this.hasInit;
	}

	/**
	 * 注册账号
	 * 
	 * @param info
	 *            User对象的字符串形式
	 * @return null或者 "fail" 表示网络连接失败 "has registered!" 表示已经注册过了 "success"
	 *         表示注册成功
	 */
	public String register(String info) {
		if (!hasInit) {
			if (!init()) {
				return null;
			}
		}
		String receivedInfo = null;
		try {
			InetAddress address = InetAddress.getByName(add);
			sendPocket = new DatagramPacket(("register " + info).getBytes(), ("register " + info).length(), address,
					port);
			receivePocket = new DatagramPacket(dataReceive, 1024);
			socket.send(sendPocket);
			socket.receive(receivePocket);
			if (!receivePocket.getAddress().equals(address)) {
				return null;
			}
			receivedInfo = new String(dataReceive);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		System.out.println(receivedInfo);
		return receivedInfo;
	}

	/**
	 * 更新账户信息
	 * 
	 * @param newInfo
	 *            User对象的字符串形式
	 * @return 同上
	 */
	public String updateInfo(String newInfo) {
		if (!hasInit) {
			if (!init()) {
				return null;
			}
		}
		String receivedInfo = null;
		try {
			InetAddress address = InetAddress.getByName(add);
			sendPocket = new DatagramPacket(("update " + newInfo).getBytes(), ("update " + newInfo).length(), address,
					port);
			receivePocket = new DatagramPacket(dataReceive, 1024);
			socket.send(sendPocket);
			socket.receive(receivePocket);
			if (!receivePocket.getAddress().equals(address)) {
				return null;
			}
			receivedInfo = new String(dataReceive);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		System.out.println(receivedInfo);
		return receivedInfo;
	}

	public String login(String userInfo) {
		if (!hasInit) {
			if (!init()) {
				return null;
			}
		}
		String receivedInfo = null;
		try {
			InetAddress address = InetAddress.getByName(add);
			sendPocket = new DatagramPacket(("login " + userInfo).getBytes(), ("login " + userInfo).length(), address,
					port);
			receivePocket = new DatagramPacket(dataReceive, 1024);
			socket.send(sendPocket);
			socket.receive(receivePocket);
			if (!receivePocket.getAddress().equals(address)) {
				return null;
			}
			receivedInfo = new String(dataReceive);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		System.out.println(receivedInfo);
		return receivedInfo;
	}

}
