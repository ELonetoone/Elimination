package el.onetoone.back;

import java.util.HashMap;

/**
 * 市场类
 * @author iznauy
 * 工具类
 */
public class Market {
	
	/**
	 * 存放商品列表（商品名-价格）
	 */
	private static HashMap<String, Integer> itemList = new HashMap<>();
	
	/**
	 * 初始化商品列表
	 */
	static {
		itemList.put(ItemList.BOOM, 10000);
		itemList.put(ItemList.HAMMER, 5000);
		itemList.put(ItemList.NEWMAP, 10000);
		itemList.put(ItemList.PLUSONESECOND, 999999);
		itemList.put(ItemList.PLUSFIVESECONDS, 15000);
		itemList.put(ItemList.PLUSTENSECONDS, 25000);
		itemList.put(ItemList.PLUSONESTEP, 10000);
		itemList.put(ItemList.PLUSTHREESTEPS, 30000);
		itemList.put(ItemList.PLUSFIVESTEPS, 50000);
	}
	
	/**
	 * 扣除金币
	 * @param user
	 * @param coin
	 * @return 返回TRUE表示金币足够，购买成功
	 */
	private static boolean deductCoin(User user, int coin) {
		if (user.getCoinCount() >= coin) {
			user.setCoinCount(user.getCoinCount() - coin);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 购买商品
	 * @param user 购买者
	 * @param item 商品名称
	 * @return 购买成功返回TRUE，扣除相应金币，否则返回FALSE
	 */
	public static boolean buyItem(User user, String item) {
		return deductCoin(user, itemList.get(item));
	}
	
	public static HashMap<String, Integer> getItemList() {
		return itemList;
	}
	
}
