package el.onetoone.ui.naruto;

import el.onetoone.ui.MarketScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class NarutoMarketScene extends MarketScene{

	public NarutoMarketScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public NarutoMarketScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public NarutoMarketScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoMarketScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public NarutoMarketScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoMarketScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getMarketScene() {
		// TODO Auto-generated method stub
		createBackground();
		createButton();
		createContentPane();
		addButtonListener();
		return this;
	}

	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		ImageView kejinImg = new ImageView();
		kejinBtn = new Button();
	}

}
