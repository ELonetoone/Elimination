package el.onetoone.ui.shop;

import el.onetoone.back.ItemList;
import el.onetoone.back.UserBox;
import el.onetoone.ui.Theme;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ItemPanel extends StackPane{

	private Image itemImg;
	private ImageView itemImgView;
	private String item;
	private Label quantityLabel;
	private final ObservableMap<String, Integer> itemMap = UserBox.getUser().getObservableItemMap();
	/**
	 * 
	 * @param item 0为商店中物品，1为背包中物品
	 * @param type
	 */
	public ItemPanel(String item, int type) {
		// TODO Auto-generated constructor stub
		this.item = item;
		switch (item) {
		case ItemList.BOOM:
			itemImg = Theme.PROPS_BOOM;
			break;

		case ItemList.HAMMER:
			//加载锤子图
			itemImg = Theme.PROPS_HAMMER;
			break;
			
		case ItemList.NEWMAP:
			itemImg = Theme.PROPS_NEW_MAP;
			break;
		}
		
		setStyle("-fx-background-color: #fff8dc;"
				+ "-fx-pref-width: 80;"
				+ "-fx-pref-height: 80;"
				+ "	-fx-background-radius: 40;"
				+ "	-fx-border-radius: 25;"
				+ "	-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		
		itemImgView = new ImageView(itemImg);
		itemImgView.setLayoutX(20);
		itemImgView.setLayoutY(20);
		itemImgView.setFitHeight(40);
		itemImgView.setFitWidth(40);
		itemImgView.setOnMousePressed(e -> {
			
		});
		
		getChildren().add(itemImgView);
		
		if (type == 1) {
			quantityLabel = new Label();
			quantityLabel.setText(itemMap.get(item).toString());
			quantityLabel.setPrefSize(30, 30);
			getChildren().add(quantityLabel);
			StackPane.setAlignment(quantityLabel, Pos.BOTTOM_RIGHT);
		}
		
	}
	
//	/**
//	 * 添加到MarketPanel之后再调用
//	 */
//	public void init() {
//		
//		setOnMousePressed( e-> {
//			((MarketPanel)getParent().getParent()).setItemDescription(item);
//		});
//	}
	

	/**
	 * 购买后，更新背包的物品数量
	 */
	public void updateQuantity() {
		quantityLabel.setText(itemMap.get(item).toString());
	}
}
