package el.onetoone.ui.shop;

import el.onetoone.back.ItemList;
import el.onetoone.back.UserBox;
import el.onetoone.ui.Theme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ItemPanel extends Pane{

	private Image itemImg;
	private ImageView itemImgView;
	private String description;
	
	public ItemPanel(String item) {
		// TODO Auto-generated constructor stub
		description = item;
		switch (item) {
		case ItemList.BOOM:
			//加载爆炸图
			break;

		case ItemList.HAMMER:
			//加载锤子图
			itemImg = Theme.IMAGE_MONEY;
			break;
			
		case ItemList.NEWMAP:
			
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
		
		UserBox.getUser().getObservableItemMap().put(ItemList.HAMMER, 0);
	}
	
	/**
	 * 添加到MarketPanel之后再调用
	 */
	public void init() {
		
		setOnMousePressed( e-> {
			((MarketPanel)getParent().getParent()).setItemDescription(description);
			UserBox.getUser().getObservableItemMap().put(description, UserBox.getUser().getObservableItemMap().get(description) + 1);
		});
	}
	
	/**
	 * 放在背包界面的物品调用
	 */
	public Pane createNumberPane() {
		
		Pane numberPane = new Pane();
		numberPane.setLayoutX(getLayoutX() + 10);
		numberPane.setLayoutY(getLayoutY() + 100);
		numberPane.setStyle("-fx-background-color: #ffffff;"
				+ "-fx-pref-width: 60;"
				+ "-fx-pref-height: 40;"
				+ "	-fx-background-radius: 20;"
//				+ "	-fx-border-radius: 100;"
				+ "	-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");
		
		return numberPane;
	}
}
