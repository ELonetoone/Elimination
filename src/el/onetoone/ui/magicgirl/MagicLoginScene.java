package el.onetoone.ui.magicgirl;



import el.onetoone.back.Config;
import el.onetoone.ui.LoginScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class MagicLoginScene extends LoginScene {

	public MagicLoginScene(Parent root, double width, double height, boolean depthBuffer,
			SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}

	public MagicLoginScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}

	public MagicLoginScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicLoginScene(Parent root, double width, double height) {
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}

	public MagicLoginScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}

	public MagicLoginScene(Parent root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getLoginScene() {
		// TODO Auto-generated method stub
		setBackground();
		putButton();
		this.getStylesheets().add(MagicInitialScene.class.getResource("textField.css").toExternalForm());
		return this;
	}
	
	
	public void putButton() {
		
		wrongMessage = new Text();
		
		super.createItems();
		ImageView pane = new ImageView(new Image("/image/lg_pane.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, true, false, false));
		pane.setLayoutX(295);
		pane.setLayoutY(130);
		((Pane) getRoot()).getChildren().add(pane);
		userNameField.setId("userName");
		passwordField.setId("password");
		userNameField.setPrefSize(350, 45);
		passwordField.setPrefSize(350, 45);
		userNameField.setLayoutX(550);
		userNameField.setLayoutY(375);
		passwordField.setLayoutX(550);
		passwordField.setLayoutY(445);
		((Pane) getRoot()).getChildren().add(userNameField);
		((Pane) getRoot()).getChildren().add(passwordField);
		sureButton.setPrefSize(190, 90);
		sureButton.setLayoutX(520);
		sureButton.setLayoutY(550);
		((Pane) getRoot()).getChildren().add(sureButton);
		returnButton.setPrefSize(190, 90);
		returnButton.setLayoutY(550);
		returnButton.setLayoutX(750);
		((Pane) getRoot()).getChildren().add(returnButton);
		wrongMessage.setLayoutX(650);
		wrongMessage.setLayoutY(510);
		((Pane) getRoot()).getChildren().add(wrongMessage);
		registerListener();
	}

}
