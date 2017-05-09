package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Group;

public class MarketPanel extends Group{

	//放置能够购买的道具
	MarketComponentPane shop;
	
	//背包
	MarketComponentPane bag;
	
	//购买状态
	MarketComponentPane status;
	
	public MarketPanel() {
		
//		setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		createNode();
		addNode();
	}

	private void createNode() {
		
		shop = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 15, Config.SCREEN_HEIGHT - 30);
		shop.setLayoutX(15);
		shop.setLayoutY(15);
		
		bag = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 30, Config.SCREEN_HEIGHT * 3 / 5 - 30);
		bag.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		bag.setLayoutY(Config.SCREEN_HEIGHT * 2 / 5 + 15);
		
		status = new MarketComponentPane(Config.SCREEN_WIDTH / 2 - 30, Config.SCREEN_HEIGHT * 2 / 5 - 30);
		status.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		status.setLayoutY(15);
	}
	
	private void addNode() {
		
		getChildren().addAll(shop, bag, status);
	}
}
