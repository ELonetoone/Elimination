package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.DynamicNumber;
import el.onetoone.ui.MagicGirlTheme;
import el.onetoone.ui.MaxMarkScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class MagicMaxMarkScene extends MaxMarkScene {

	private static final int SCORE_LAYOUT_Y = Config.SCREEN_HEIGHT - 120;

	public MagicMaxMarkScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MagicMaxMarkScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MagicMaxMarkScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicMaxMarkScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MagicMaxMarkScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicMaxMarkScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getMaxMarkScene() {
		// TODO Auto-generated method stub
		createBackground();
		createButton();
		addButtonListener();
		createScore();
		this.getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		return this;
	}

	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		ImageView backImg = new ImageView(MagicGirlTheme.MAX_SCORE_BACK_BUTTON);
		backImg.setFitHeight(150);
		backImg.setPreserveRatio(true);

		backBtn = new Button();
		backBtn.setGraphic(backImg);
		backBtn.setLayoutX(Config.SCREEN_WIDTH - 300);
		backBtn.setLayoutY(Config.SCREEN_HEIGHT - 200);
		((Pane) getRoot()).getChildren().add(backBtn);
	}

	private void createScore() {

		DynamicNumber stepScore = new DynamicNumber(5);
		stepScore.initStepScore();
		stepScore.setLayoutX(850);
		stepScore.setLayoutY(SCORE_LAYOUT_Y);

		DynamicNumber endlessScore = new DynamicNumber(5);
		endlessScore.initUnlimitedScore();
		endlessScore.setLayoutX(350);
		endlessScore.setLayoutY(SCORE_LAYOUT_Y);

		DynamicNumber timeScore = new DynamicNumber(5);
		timeScore.initTimeScore();
		timeScore.setLayoutX(600);
		timeScore.setLayoutY(SCORE_LAYOUT_Y);

		((Pane) getRoot()).getChildren().addAll(stepScore, timeScore, endlessScore);
	}
}
