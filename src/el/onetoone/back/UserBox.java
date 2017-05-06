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
	
	public static User getUser(User user) {
		return UserBox.user;
	}

}
