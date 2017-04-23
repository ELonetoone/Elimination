package el.onetoone.back;

import java.util.ArrayList;

/**
 * 用户类，可以用于注册账号，登录等。
 * @author iznauy
 *
 */
public class User {
	
	/**
	 * 用户登录账户
	 */
	private String uid = null;
	
	/**
	 * 用户登录密码
	 */
	private String password = null;
	
	/**
	 * 用户昵称
	 */
	private String userName = null;
	
	/**
	 * 用户金币数
	 */
	private int coinCount = 0;
	
	/**
	 * 用户最高分
	 */
	private int maxMark = 0;
	
	/**
	 * 数据库连接失败
	 */
	public static final String FAILTOCONNECTDATABASE = "131231398";
	
	/**
	 * 登录密码出错
	 */
	public static final String WRONGPASSWORD = "123209854"; 
	
	/**
	 * 尚未注册
	 */
	public static final String HASNOTREGISTER = "90980343";
	
	/**
	 * 将User对象化成字符串，方便存储在本地。
	 */
	@Override
	public String toString() {
		return uid + " " + password + " " + userName + " " + coinCount + " " + maxMark + "\n";
	}
	
	/**
	 * 根据字符串解析出一个用户
	 * @param userInfo 用户信息字符串
	 * @return 一个用户对象
	 */
	public User parseUserString(String userInfo) {
		String[] userInfos = userInfo.split(" ");
		return new User(userInfos[0], userInfos[1], userInfos[2], Integer.valueOf(userInfos[3]), Integer.valueOf(userInfos[4]));
	}
	
	/**
	 * 用于将字符串转化为一个User对象
	 * @param uid
	 * @param password
	 * @param userName
	 * @param coinCount
	 * @param maxMark
	 */
	public User(String uid, String password, String userName, int coinCount, int maxMark) {
		this.uid = uid;
		this.maxMark = maxMark;
		this.password = password;
		this.coinCount = coinCount;
	}
	
	/**
	 * 判定用户是否已经注册
	 * @param uid 用户注册ID
	 * @return 
	 */
	public static boolean hasRegister(String uid) throws Exception{
		DataBase dataBase = new DataBase();
		ArrayList<String> allUsers = dataBase.read();
		if (allUsers == null) {
			throw new Exception(FAILTOCONNECTDATABASE);
		}
		for (String user: allUsers) {
			String[] userInfos = user.split(" ");
			if (userInfos[0].equals(uid)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 使用hasRegister判定后使用
	 * 注册用户
	 * 默认昵称是uid
	 * @param uid 用户注册账号
	 * @param password 用户密码
	 * @return
	 * @throws Exception 如果遇到文件读写失败时候抛出
	 */
	public static User register(String uid, String password) throws Exception {
		User user = new User(uid, password, uid, 0, 0);
		DataBase dataBase = new DataBase();
		//假如写入数据不成功
		if (!dataBase.write(user.toString())) {
			throw new Exception(FAILTOCONNECTDATABASE);
		} else {
			return user;
		}
	}
	
	/**
	 * 用户登陆时候调用
	 * @param uid 用户账号
	 * @param password 用户密码
	 * @return 用户对象
	 * @throws Exception 登录失败和密码出错时候均抛出异常
	 */
	public static User login(String uid, String password) throws Exception {
		DataBase dataBase = new DataBase();
		ArrayList<String> allUsers = dataBase.read();
		if (allUsers == null) {
			throw new Exception(FAILTOCONNECTDATABASE);
		}
		for (String userInfo: allUsers) {
			String[] userInfos = userInfo.split(" ");
			if (userInfos[0].equals(uid) && userInfos[1].equals(password)) {
				return new User(userInfos[0], userInfos[1], userInfos[2], Integer.valueOf(userInfos[3]), Integer.valueOf(userInfos[4]));
			} else if (userInfos[0].equals(uid) && !userInfos[1].equals(password)) {
				throw new Exception(WRONGPASSWORD);
			}
		}
		//假如能进入到这儿说明根本没注册
		throw new Exception(HASNOTREGISTER);
		
	}
	
	/**
	 * 判断两个用户是否相同
	 */
	@Override
	public boolean equals(Object anotherUser) {
		User another = (User) anotherUser;
		if (another.coinCount == coinCount &&
				another.maxMark == maxMark &&
				another.password.equals(password) &&
				another.uid.equals(uid) &&
				another.userName.equals(userName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取用户昵称
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户昵称
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取当前金币
	 * @return
	 */
	public int getCoinCount() {
		return coinCount;
	}

	/**
	 * 设置当前金币
	 * @param coinCount
	 */
	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}

	/**
	 * 获取最高分数
	 * @return
	 */
	public int getMaxMark() {
		return maxMark;
	}

	/**
	 * 设置最高分
	 * @param maxMark
	 */
	public void setMaxMark(int maxMark) {
		this.maxMark = maxMark;
	}

}
