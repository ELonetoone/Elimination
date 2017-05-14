package el.onetoone.ui.magicgirl;

import el.onetoone.back.Config;
import el.onetoone.ui.Main;
import el.onetoone.ui.RegisterScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class MagicRegisterScene extends RegisterScene {

	public MagicRegisterScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public MagicRegisterScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicRegisterScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MagicRegisterScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicRegisterScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MagicRegisterScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getRegisterScene() {
		setBackground();
		putButton();
		this.getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		return this;
	}
	
	public void putButton() {
		
		super.createItems();
		ImageView pane = new ImageView(new Image("/image/rg_pane.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, true, false, false));
		pane.setLayoutX(295);
		pane.setLayoutY(130);
		((Pane) getRoot()).getChildren().add(pane);
		
		userName.setId("userName");
		passwordField.setId("password");
		userName.setPrefSize(350, 45);
		passwordField.setPrefSize(350, 45);
		confirmField.setId("confirmField");
		confirmField.setPrefSize(350, 45);
		
		sureButton.setPrefSize(190, 90);
		returnButton.setPrefSize(190, 90);
		
		userName.setLayoutX(550);
		userName.setLayoutY(340);
		passwordField.setLayoutX(550);
		passwordField.setLayoutY(410);
		confirmField.setLayoutX(550);
		confirmField.setLayoutY(475);
		
		sureButton.setLayoutX(530);
		sureButton.setLayoutY(570);
		
		returnButton.setLayoutX(755);
		returnButton.setLayoutY(570);
		
	//	wrongMessage.setText("DASDAS");
		
		wrongMessage.setLayoutX(690);
		wrongMessage.setLayoutY(545);
		
		Font font = Font.loadFont(Main.class.getResource("font/magic.ttf").toExternalForm(), 25);
		wrongMessage.setFont(font);
		userName.setFont(font);
		
		((Pane) getRoot()).getChildren().add(userName);
		((Pane) getRoot()).getChildren().add(passwordField);
		((Pane) getRoot()).getChildren().add(confirmField);
		((Pane) getRoot()).getChildren().add(wrongMessage);
		((Pane) getRoot()).getChildren().add(returnButton);
		((Pane) getRoot()).getChildren().add(sureButton);
		super.registerListener();
	}

}
