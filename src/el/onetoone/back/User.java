package el.onetoone.back;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * 用户类，可以用于注册账号，登录等。
 * 
 * @author iznauy
 *
 */
public class User {

	/**
	 * +5s
	 */
	private int plus5sec = 0;

	/**
	 * +1s！
	 */
	private int plus1sec = 0;

	/**
	 * +10s
	 */
	private int plus10sec = 0;

	/**
	 * +5step
	 */
	private int plus5step = 0;

	/**
	 * +3step
	 */
	private int plus3step = 0;

	/**
	 * +1step
	 */
	private int plus1step = 0;

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
	 * 用户无尽模式最高分
	 */
	private int endLessMaxMark = 0;

	/**
	 * 用户限时模式最高分
	 */
	private int timeLimitedMaxMark = 0;

	/**
	 * 用户步数有限模式最高分
	 */
	private int stepLimitedMaxMark = 0;

	/**
	 * 用户持有的道具
	 */
	private HashMap<String, Integer> myItems = new HashMap<>();

	/**
	 * 持有道具的Observable版
	 */
	private ObservableMap<String, Integer> observableMyItems = FXCollections.observableMap(myItems);

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
	 * 已经被注册
	 */
	public static final String HASBEENREGISTERED = "SDADASDCZ";

	/**
	 * 将User对象化成字符串，方便存储在本地。
	 */
	@Override
	public String toString() {
		return uid + " " + password + " " + userName + " " + coinCount + " " + endLessMaxMark + " " + timeLimitedMaxMark
				+ " " + stepLimitedMaxMark + " " + myItems.get(ItemList.BOOM) + " " + myItems.get(ItemList.HAMMER) + " "
				+ myItems.get(ItemList.NEWMAP) + " " + myItems.get(ItemList.PLUSONESECOND) + " "
				+ myItems.get(ItemList.PLUSTHREESECONDS) + " " + myItems.get(ItemList.PLUSFIVESECONDS) + " "
				+ myItems.get(ItemList.PLUSONESTEP) + " " + myItems.get(ItemList.PLUSTHREESTEPS) + " "
				+ myItems.get(ItemList.PLUSFIVESTEPS) + "\n";
	}

	/**
	 * 根据字符串解析出一个用户
	 * 
	 * @param userInfo
	 *            用户信息字符串
	 * @return 一个用户对象
	 */
	public User parseUserString(String userInfo) {
		String[] userInfos = userInfo.split(" ");
		return new User(userInfos[0], userInfos[1], userInfos[2], Integer.valueOf(userInfos[3]),
				Integer.valueOf(userInfos[4]), Integer.valueOf(userInfos[5]), Integer.valueOf(userInfos[6]),
				Integer.valueOf(userInfos[7]), Integer.valueOf(userInfos[8]), Integer.valueOf(userInfos[9]),
				Integer.valueOf(userInfos[10]), Integer.valueOf(userInfos[11]), Integer.valueOf(userInfos[12]),
				Integer.valueOf(userInfos[13]), Integer.valueOf(userInfos[14]), Integer.valueOf(userInfos[15]));
	}

	/**
	 * 用于将字符串转化为一个User对象
	 * 
	 * @param uid
	 * @param password
	 * @param userName
	 * @param coinCount
	 * @param maxMark
	 */
	public User(String uid, String password, String userName, int coinCount, int endLessMark, int timeLimitedMaxMark,
			int stepLimitedMaxMark, int boomCount, int hammerCount, int newmapCount, int plus1s, int plus5s,
			int plus10s, int plus1step, int plus3step, int plus5step) {
		this.uid = uid;
		this.userName = userName;
		this.endLessMaxMark = endLessMark;
		this.timeLimitedMaxMark = timeLimitedMaxMark;
		this.stepLimitedMaxMark = stepLimitedMaxMark;
		this.password = password;
		this.coinCount = coinCount;
		this.myItems.put(ItemList.BOOM, boomCount);
		this.myItems.put(ItemList.HAMMER, hammerCount);
		this.myItems.put(ItemList.NEWMAP, newmapCount);
		this.myItems.put(ItemList.PLUSONESECOND, plus1s);
		this.myItems.put(ItemList.PLUSTHREESECONDS, plus5s);
		this.myItems.put(ItemList.PLUSFIVESECONDS, plus10s);
		this.myItems.put(ItemList.PLUSONESTEP, plus1step);
		this.myItems.put(ItemList.PLUSTHREESTEPS, plus3step);
		this.myItems.put(ItemList.PLUSFIVESTEPS, plus5step);
	}

	/**
	 * 判定用户是否已经注册
	 * 
	 * @param uid
	 *            用户注册ID
	 * @return
	 */
	public static boolean hasRegister(String uid) throws Exception {
		return false;
	}

	/**
	 * 使用hasRegister判定后使用 注册用户 默认昵称是uid
	 * 
	 * @param uid
	 *            用户注册账号
	 * @param password
	 *            用户密码
	 * @return
	 * @throws Exception
	 *             如果遇到文件读写失败时候抛出
	 */
	public static User register(String uid, String password) throws Exception {
		User user = new User(uid, password, uid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		NetDataBase netDataBase = new NetDataBase();
		String returnString = netDataBase.register(user.toString());
		if (returnString == null || returnString.equals("fail")) {
			throw new Exception(FAILTOCONNECTDATABASE);
		} else if (returnString.equals(
				"has registered!                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ")) {
			throw new Exception(HASBEENREGISTERED);
		} else {
			return user;
		}
	}

	/**
	 * 用户登陆时候调用
	 * 
	 * @param uid
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @return 用户对象
	 * @throws Exception
	 *             登录失败和密码出错时候均抛出异常
	 */
	public static User login(String uid, String password) throws Exception {
		// DataBase dataBase = new DataBase();
		// ArrayList<String> allUsers = dataBase.read();
		// if (allUsers == null) {
		// throw new Exception(FAILTOCONNECTDATABASE);
		// }
		// for (String userInfo: allUsers) {
		// String[] userInfos = userInfo.split(" ");
		// if (userInfos[0].equals(uid) && userInfos[1].equals(password)) {
		// return new User(userInfos[0], userInfos[1], userInfos[2],
		// Integer.valueOf(userInfos[3]), Integer.valueOf(userInfos[4]),
		// Integer.valueOf(userInfos[5])
		// , Integer.valueOf(userInfos[6]), Integer.valueOf(userInfos[7]),
		// Integer.valueOf(userInfos[8]), Integer.valueOf(userInfos[9]));
		// } else if (userInfos[0].equals(uid) &&
		// !userInfos[1].equals(password)) {
		// throw new Exception(WRONGPASSWORD);
		// }
		// }
		// //假如能进入到这儿说明根本没注册
		// throw new Exception(HASNOTREGISTER);
		NetDataBase netDataBase = new NetDataBase();
		String userInfo = uid + " " + password;
		String returnInfo = netDataBase.login(userInfo);
		if (returnInfo == null) {
			throw new Exception(FAILTOCONNECTDATABASE);
		} else if (returnInfo.equals(
				"wrong password                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ")) {
			throw new Exception(WRONGPASSWORD);
		} else if (returnInfo.equals(
				"has not registered                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ")) {
			throw new Exception(HASNOTREGISTER);
		} else {
			// System.out.println(returnInfo);
			String[] userInfos = returnInfo.split(" ");
			// System.out.println(Arrays.deepToString(userInfos));

			return new User(userInfos[0], userInfos[1], userInfos[2], Integer.valueOf(userInfos[3]),
					Integer.valueOf(userInfos[4]), Integer.valueOf(userInfos[5]), Integer.valueOf(userInfos[6]),
					Integer.valueOf(userInfos[7]), Integer.valueOf(userInfos[8]), Integer.valueOf(userInfos[9]),
					Integer.valueOf(userInfos[10]), Integer.valueOf(userInfos[11]), Integer.valueOf(userInfos[12]),
					Integer.valueOf(userInfos[13]), Integer.valueOf(userInfos[14]),
					Integer.valueOf(userInfos[15].trim()));
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	public boolean updateUserInfo() {
		String userInfo = this.toString();
		NetDataBase netDataBase = new NetDataBase();
		String returnInfo = netDataBase.updateInfo(userInfo);
		if (returnInfo == null || returnInfo.equals("fail")) {
			return false;
		} else {
			return true;
		}
	}

	// /**
	// * 判断两个用户是否相同
	// */
	// @Override
	// public boolean equals(Object anotherUser) {
	// User another = (User) anotherUser;
	// if (another.coinCount == coinCount &&
	// another.endLessMaxMark == endLessMaxMark &&
	// another.password.equals(password) &&
	// another.uid.equals(uid) &&
	// another.userName.equals(userName)) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	/**
	 * 获取用户昵称
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户昵称
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取当前金币
	 * 
	 * @return
	 */
	public int getCoinCount() {
		return coinCount;
	}

	/**
	 * 设置当前金币
	 * 
	 * @param coinCount
	 */
	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}

	/**
	 * 获取最高分数
	 * 
	 * @return
	 */
	public int getendLessMaxMark() {
		return endLessMaxMark;
	}

	/**
	 * 设置最高分
	 * 
	 * @param endLessMaxMark
	 */
	public void setEndLessMaxMark(int endLessMaxMark) {
		this.endLessMaxMark = endLessMaxMark;
	}

	/**
	 * 获取各种道具用户拥有数量
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getMyItems() {
		return this.myItems;
	}

	/**
	 * 判断用户是否有足够的道具
	 * 
	 * @param item
	 *            道具名称
	 * @return
	 */
	private boolean hasEnoughItem(String item) {
		if (myItems.get(item) >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 使用某个道具
	 * 
	 * @param item
	 * @return 是否使用成功，使用成功返回TRUE，否则返回FALSE
	 */
	public boolean useItem(String item) {
		if (hasEnoughItem(item)) {
			myItems.replace(item, myItems.get(item) - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取时间有限模式最高分
	 * 
	 * @return
	 */
	public int getTimeLimitedMaxMark() {
		return timeLimitedMaxMark;
	}

	/**
	 * 设置时间优先模式最高分
	 * 
	 * @param timeLimitedMaxMark
	 */
	public void setTimeLimitedMaxMark(int timeLimitedMaxMark) {
		this.timeLimitedMaxMark = timeLimitedMaxMark;
	}

	/**
	 * 获取步数有限模式最高分
	 * 
	 * @return
	 */
	public int getStepLimitedMaxMark() {
		return stepLimitedMaxMark;
	}

	/**
	 * 设置步数有限模式最高分
	 * 
	 * @param stepLimitedMaxMark
	 */
	public void setStepLimitedMaxMark(int stepLimitedMaxMark) {
		this.stepLimitedMaxMark = stepLimitedMaxMark;
	}

	public ObservableMap<String, Integer> getObservableItemMap() {

		return observableMyItems;
	}

	public int getPlus5sec() {
		return plus5sec;
	}

	public void setPlus5sec(int plus5sec) {
		this.plus5sec = plus5sec;
	}

	public int getPlus1sec() {
		return plus1sec;
	}

	public void setPlus1sec(int plus1sec) {
		this.plus1sec = plus1sec;
	}

	public int getPlus10sec() {
		return plus10sec;
	}

	public void setPlus10sec(int plus10sec) {
		this.plus10sec = plus10sec;
	}

	public int getPlus5step() {
		return plus5step;
	}

	public void setPlus5step(int plus5step) {
		this.plus5step = plus5step;
	}

	public int getPlus3step() {
		return plus3step;
	}

	public void setPlus3step(int plus3step) {
		this.plus3step = plus3step;
	}

	public int getPlus1step() {
		return plus1step;
	}

	public void setPlus1step(int plus1step) {
		this.plus1step = plus1step;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ObservableMap<String, Integer> getObservableMyItems() {
		return observableMyItems;
	}

	public void setObservableMyItems(ObservableMap<String, Integer> observableMyItems) {
		this.observableMyItems = observableMyItems;
	}

	public void setMyItems(HashMap<String, Integer> myItems) {
		this.myItems = myItems;
	}

	// public int getPlus5sec() {
	// return plus5sec;
	// }
	//
	// public void setPlus5sec(int plus5sec) {
	// this.plus5sec = plus5sec;
	// }
	//
	// public int getPlus1sec() {
	// return plus1sec;
	// }
	//
	// public void setPlus1sec(int plus1sec) {
	// this.plus1sec = plus1sec;
	// }
	//
	// public int getPlus10sec() {
	// return plus10sec;
	// }
	//
	// public void setPlus10sec(int plus10sec) {
	// this.plus10sec = plus10sec;
	// }
	//
	// public int getPlus5step() {
	// return plus5step;
	// }
	//
	// public void setPlus5step(int plus5step) {
	// this.plus5step = plus5step;
	// }
	//
	// public int getPlus3step() {
	// return plus3step;
	// }
	//
	// public void setPlus3step(int plus3step) {
	// this.plus3step = plus3step;
	// }
}
