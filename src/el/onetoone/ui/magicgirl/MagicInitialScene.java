package el.onetoone.ui.magicgirl;


import el.onetoone.back.Config;
import el.onetoone.ui.InitialScene;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class MagicInitialScene extends InitialScene {

	public MagicInitialScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MagicInitialScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MagicInitialScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicInitialScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MagicInitialScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicInitialScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	private ImageView background;

	private VBox buttonBox;

	@Override
	public Scene getInitialScene() {
		// TODO Auto-generated method stub
		setBackground();
		putButton();
		this.getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		return this;
	}

	public void setBackground() {
		background = new ImageView(Config.getTheme().getBG_START());
		background.setFitWidth(Config.SCREEN_WIDTH);
		background.setPreserveRatio(true);
		((Pane) getRoot()).getChildren().add(background);
	}

	public void putButton() {
		super.createButton(60);
		buttonBox = new VBox();
		buttonBox.setSpacing(-5);
		buttonBox.setAlignment(Pos.BASELINE_RIGHT);
		buttonBox.getChildren().addAll(loginButton, registerButton, trialButton, exitButton);
		buttonBox.setLayoutX(750);
		buttonBox.setLayoutY(350);

		((Pane) getRoot()).getChildren().add(buttonBox);

		registerButtonEventHandler();
	}
}
