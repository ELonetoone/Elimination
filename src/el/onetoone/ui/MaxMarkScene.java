package el.onetoone.ui;

import el.onetoone.back.Config;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public abstract class MaxMarkScene extends Scene {

	protected Button backBtn;
	private ImageView background;

	public MaxMarkScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MaxMarkScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MaxMarkScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MaxMarkScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MaxMarkScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MaxMarkScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public abstract Scene getMaxMarkScene();

	public abstract void createButton();

	protected void createBackground() {

		background = new ImageView(Config.getTheme().getBG_HEIGHEST_SCORE());
		((Pane) getRoot()).getChildren().add(background);
	}

	protected void addButtonListener() {

		backBtn.setOnAction(e -> {
			Config.getMain().setScene(Config.getTheme().getSynScene());
		});
	}
}
