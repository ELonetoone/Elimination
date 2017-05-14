package el.onetoone.ui.shop;

import el.onetoone.back.ItemList;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class BagPane extends Pane {

	private ItemPanel hammer, boom, newMap;
	private TilePane tilePane;

	public BagPane() {

		setStyle("-fx-background-color: #fff0f5" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");

		setPrefSize(600, 300);

		tilePane = new TilePane();
		tilePane.setHgap(20);
		tilePane.setVgap(20);
		tilePane.setPadding(new Insets(10));
		getChildren().add(tilePane);

		createNode();
		addNode();
	}

	private void addNode() {
		// TODO Auto-generated method stub
		tilePane.getChildren().addAll(boom, hammer, newMap);
	}

	private void createNode() {
		// TODO Auto-generated method stub
		boom = new ItemPanel(ItemList.BOOM, 0);
		hammer = new ItemPanel(ItemList.HAMMER, 1);
		newMap = new ItemPanel(ItemList.NEWMAP, 2);
	}
}
