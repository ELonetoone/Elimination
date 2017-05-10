package el.onetoone.ui.shop;

import javafx.scene.layout.Pane;

/**
 * 商城界面的小版块，如背包板块，商店板块
 * @author liao
 *
 */
public class MarketComponentPane extends Pane{

	public MarketComponentPane(int width, int height) {
		
		setPrefSize(width, height);
		setStyle("-fx-background-color: #fff0f5" + ";-fx-background-radius: 10" + ";-fx-border-radius: 10"
				+ ";-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1)");
		
	}
}
