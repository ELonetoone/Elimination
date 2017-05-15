package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.ui.InitialScene;
import el.onetoone.ui.InitialView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class NarutoInitialScene extends InitialScene {

	public NarutoInitialScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public NarutoInitialScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public NarutoInitialScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoInitialScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public NarutoInitialScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoInitialScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getInitialScene() {
		
		getStylesheets().add(InitialView.class.getResource("initialView.css").toExternalForm());
		createButton(60);
		registerButtonEventHandler();
		layUpButton();
		createBackgroud();
		addNode();
		return this;
	}

	private void createBackgroud() {
		// TODO Auto-generated method stub
		backgroud = new ImageView(Config.getTheme().getBG_START());
		backgroud.setFitWidth(Config.SCREEN_WIDTH);
		backgroud.setPreserveRatio(true);
	}

	private void addNode() {

		((Pane) getRoot()).getChildren().addAll(backgroud, loginButton, trialButton, registerButton, exitButton);
	}

	private void layUpButton() {
		// TODO Auto-generated method stub
		loginButton.setLayoutX(50);
		loginButton.setLayoutY(200);

		trialButton.setLayoutX(100);
		trialButton.setLayoutY(250);

		registerButton.setLayoutX(50);
		registerButton.setLayoutY(300);

		exitButton.setLayoutX(100);
		exitButton.setLayoutY(350);

	}

}
