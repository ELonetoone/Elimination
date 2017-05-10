package el.onetoone.ui.shop;

import el.onetoone.back.Config;
import el.onetoone.back.ItemList;
import el.onetoone.back.UserBox;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class MarketPanel extends Group{

	//放置能够购买的道具
	MarketComponentPane shop;
	
	//背包
	MarketComponentPane bag;
	
	//购买状态
	MarketComponentPane status;
	
	//商店物品
	ItemPanel marketHammer;
	
	//背包物品
	ItemPanel bagHammer;

	private ObservableMap<String, Integer> userItemList;

	private Text itemText;
	
	public MarketPanel() {
		
//		setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		createNode();
		addNode();
	}

	private void createNode() {
		
		shop = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 15, Config.SCREEN_HEIGHT - 30);
		shop.setLayoutX(15);
		shop.setLayoutY(15);
		
		marketHammer = new ItemPanel(ItemList.HAMMER);
		marketHammer.setLayoutX(20);
		marketHammer.setLayoutY(20);
		
		bag = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 30, Config.SCREEN_HEIGHT * 3 / 5 - 30);
		bag.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		bag.setLayoutY(Config.SCREEN_HEIGHT * 2 / 5 + 15);
		
		status = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 30, Config.SCREEN_HEIGHT * 2 / 5 - 30);
		status.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		status.setLayoutY(15);
		
		itemText = new Text();
	}
	
	private void addNode() {
		
		getChildren().addAll(shop, bag, status);
		
		addUserItemToBag();
		shop.getChildren().add(marketHammer);
		marketHammer.init();
		
		status.getChildren().add(itemText);
		
	}
	
	private void addUserItemToBag() {
		
		userItemList = UserBox.getUser().getObservableItemMap();
		System.out.println(userItemList == UserBox.getUser().getObservableItemMap());
		userItemList.addListener(new MapChangeListener<String, Integer>() {

			@Override
			public void onChanged(MapChangeListener.Change arg0) {
				// TODO Auto-generated method stub
				if (userItemList.get(ItemList.HAMMER) == 1) {
					
					System.out.println("changed");
					bagHammer = new ItemPanel(ItemList.HAMMER);
					bagHammer.setLayoutX(15);
					bagHammer.setLayoutY(30);
					bag.getChildren().addAll(bagHammer);
				} else {
					
					bag.getChildren().add(bagHammer.createNumberPane());
				}
			}
			
		});
		
	}
	
	public ObservableMap<String, Integer> getUserItemList() {
		return userItemList;
	}

	public void setItemDescription(String description) {
		
		itemText.setText(description);
		itemText.setLayoutY(30);
		itemText.setLayoutX(15);
		itemText.setStyle(
				"-fx-font-size: 25;"
				+ "-fx-font-stroke: #00bfff;");
	}
}
