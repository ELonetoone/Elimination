package el.onetoone.ui.naruto;

import el.onetoone.back.Config;
import el.onetoone.ui.DynamicNumber;
import el.onetoone.ui.MaxMarkScene;
import el.onetoone.ui.magicgirl.MagicInitialScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class NarutoMaxMarkScene extends MaxMarkScene{

	private static final int SCORE_LAYOUT_X = 300;
	public NarutoMaxMarkScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public NarutoMaxMarkScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public NarutoMaxMarkScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoMaxMarkScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public NarutoMaxMarkScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public NarutoMaxMarkScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getMaxMarkScene() {
		// TODO Auto-generated method stub
		createBackground();
		createButton();
		createScore();
		((Pane) getRoot()).setOnMouseClicked(e -> {
			
			if (e.getClickCount() == 2) {
				Config.getMain().setScene(Config.getTheme().getSynScene());
			}
		});
		this.getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		return this;
	}

	private void createScore() {
		// TODO Auto-generated method stub
		DynamicNumber stepScore = new DynamicNumber(5);
		stepScore.initStepScore();

		DynamicNumber endlessScore = new DynamicNumber(5);
		endlessScore.initUnlimitedScore();

		DynamicNumber timeScore = new DynamicNumber(5);
		timeScore.initTimeScore();
		
		VBox scoreBox = new VBox(endlessScore, stepScore, timeScore);
		scoreBox.setSpacing(70);
		scoreBox.setLayoutX(SCORE_LAYOUT_X);
		scoreBox.setLayoutY(280);
		
		((Pane) getRoot()).getChildren().add(scoreBox);
	}

	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		
	}

}
