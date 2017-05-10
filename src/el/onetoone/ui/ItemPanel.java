package el.onetoone.ui;

import el.onetoone.back.ItemList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ItemPanel extends Pane{

	private Image itemImg;
	private ImageView itemImgView;
	
	public ItemPanel(String item) {
		// TODO Auto-generated constructor stub
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
		getChildren().add(itemImgView);
	}
}
