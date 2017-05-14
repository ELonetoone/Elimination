package el.onetoone.ui.shop;

import el.onetoone.back.Config;
import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MarketPanel extends Pane {

	ShopPane shopPane;
	BagPane bagPane;
	PurchasePane purchasePane;

	private ObservableMap<String, Integer> userItemList;

	private Text itemText;

	public MarketPanel() {

		// setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

		// setStyle(""-fx-);
		createNode();
		addNode();
	}

	private void createNode() {

		purchasePane = new PurchasePane();
		purchasePane.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		purchasePane.setLayoutY(15);

		shopPane = new ShopPane(purchasePane);
		shopPane.setLayoutX(15);
		shopPane.setLayoutY(15);

		bagPane = new BagPane();
		bagPane.setLayoutX(Config.SCREEN_WIDTH / 2 + 15);
		bagPane.setLayoutY(Config.SCREEN_HEIGHT * 2 / 5 + 15);

		itemText = new Text();
	}

	private void addNode() {

		getChildren().addAll(shopPane, bagPane, purchasePane);

		purchasePane.getChildren().add(itemText);

	}

	public ObservableMap<String, Integer> getUserItemList() {
		return userItemList;
	}

	public void setItemDescription(String description) {

		itemText.setText(description);
		itemText.setLayoutY(30);
		itemText.setLayoutX(15);
		itemText.setStyle("-fx-font-size: 25;" + "-fx-font-stroke: #00bfff;");
	}
}
