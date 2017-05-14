package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.MarketScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class MagicMarketScene extends MarketScene{

	public MagicMarketScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MagicMarketScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MagicMarketScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicMarketScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MagicMarketScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicMarketScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getMarketScene() {
		// TODO Auto-generated method stub
		((Pane)getRoot()).setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				Config.getMain().setScene(Config.getTheme().getSynScene());
			}
		});
		getStylesheets().add(MarketScene.class.getResource("initialView.css").toExternalForm());
		createBackground();
		createMoney();
		createKejinButton();
		createContentPane();
		createProps();
		return this;
	}

	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		
	}

}
