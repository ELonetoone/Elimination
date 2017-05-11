package el.onetoone.ui.shop;

import el.onetoone.back.ItemList;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class ShopPane extends Pane{

	private ItemPanel hammer, boom, newMap;
	private TilePane tilePane;
	private PurchasePane purchasePane;
	
	public ShopPane(PurchasePane purchasePane) {
		
		super();
		this.purchasePane = purchasePane;
		
		tilePane = new TilePane();
		tilePane.setHgap(20);
		tilePane.setVgap(20);
		tilePane.setPadding(new Insets(10));
		getChildren().add(tilePane);
		
		setStyle("-fx-background-color: #fff0f5" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		
		createItem();
		addItem();
	}

	private void addItem() {
		// TODO Auto-generated method stub
		tilePane.getChildren().addAll(hammer, boom, newMap);
	}

	private void createItem() {
		// TODO Auto-generated method stub
		boom = new ItemPanel(ItemList.BOOM, 0);
		hammer = new ItemPanel(ItemList.HAMMER, 1);
		newMap = new ItemPanel(ItemList.NEWMAP, 2);
		
		boom.setOnMousePressed(e -> {
			purchasePane.updateInfo("BOOM!", ItemList.BOOM);
		});
		
		hammer.setOnMousePressed(e -> {
			purchasePane.updateInfo("锤子", ItemList.HAMMER);
		});
		
		newMap.setOnMousePressed(e -> {
			purchasePane.updateInfo("生成新地图", ItemList.NEWMAP);
		});
	}
}
