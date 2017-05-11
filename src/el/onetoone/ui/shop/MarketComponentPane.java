package el.onetoone.ui.shop;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * 商城界面的小版块，如背包板块，商店板块
 * @author liao
 *
 */
public class MarketComponentPane extends TilePane{

	public MarketComponentPane() {
		
		setStyle("-fx-background-color: #fff0f5" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		setPrefColumns(4);
		setHgap(20);
		setVgap(20);
		setPadding(new Insets(10));
	}
}
