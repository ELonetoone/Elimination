package el.onetoone.back;

/**
 * 一个装着User的小盒子！
 * @author iznauy
 *
 */
public class UserBox {
	
	private static User user;
	
	public static void setUser(User user) {
		UserBox.user = user;
	}
	
	public static User getUser() {
		return UserBox.user;
	}
	
	/**
	 * 判断用户是否登录
	 * @return
	 */
	public static boolean hasNotLogin() { 
		return UserBox.user == null;
	}

}
